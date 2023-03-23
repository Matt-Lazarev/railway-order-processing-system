package com.lazarev.documentsservice.service;

import com.lazarev.model.ClientOrderCardDto;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class DocumentService {
    private static final Path DEFAULT_TEMPLATE_PATH = Path.of("Template.docx");

    public byte[] generateDocument(ClientOrderCardDto orderCard) {
        try {
            File file = new File(DEFAULT_TEMPLATE_PATH.toString());
            XWPFDocument docx = new XWPFDocument();
            if (file.exists()) {
                docx = new  XWPFDocument(new FileInputStream(file));
            }

            createParagraph(docx, "Исполнитель: ООО «Транспортная компания»");
            createParagraph(docx,"Заказчик: " + orderCard.getClientFullName());
            createParagraph(docx,"Информация по договору: ");

            XWPFTable table = docx.createTable();
            table.setWidth("100%");

            createTableHeaderRow(table, "Параметр перевозки", "Значение параметра перевозки");

            Map<String, String> rows = new LinkedHashMap<>();
            rows.put("Плановая дата начала", orderCard.getOrderBegin().toString());
            rows.put("Плановая дата завершения", orderCard.getOrderEnd().toString());
            rows.put("Статус", orderCard.getStatus());
            rows.put("Станция отправления", orderCard.getSourceStation());
            rows.put("Станция назначения", orderCard.getDestStation());
            rows.put("Груз", orderCard.getCargo());
            rows.put("Общий объем", Integer.toString(orderCard.getTotalVolume()));
            rows.put("Объем одного вагона", Integer.toString(orderCard.getWagonVolume()));
            rows.put("Количество вагонов", Integer.toString(orderCard.getWagonAmount()));
            rows.put("Ставка", String.valueOf(orderCard.getRate()));

            for(Map.Entry<String, String> row : rows.entrySet()){
                createTableRow(table, row);
            }

            Path resultFilePath = Path.of(UUID.randomUUID().toString());
            FileOutputStream out = new FileOutputStream(resultFilePath.toString());
            docx.write(out);
            out.close();
            docx.close();

            byte[] data = Files.readAllBytes(resultFilePath);
            Files.delete(resultFilePath);

            return data;
        }
        catch (Exception exception){
            throw new RuntimeException(exception);
        }
    }

    private void createParagraph(XWPFDocument docx, String text){
        XWPFParagraph par = docx.createParagraph();
        XWPFRun run = par.createRun();
        run.setFontSize(14);
        run.setFontFamily("Times New Roman");
        run.setText(text);
    }

    private void createTableHeaderRow(XWPFTable table, String col1Text, String col2Text){
        XWPFTableRow headerRow = table.getRow(0);

        XWPFRun col1 = createTableParagraph(headerRow, 0, true, false);
        XWPFRun col2 = createTableParagraph(headerRow, 1, true, true);

        col1.setText(col1Text);
        col2.setText(col2Text);
    }

    private void createTableRow(XWPFTable table, Map.Entry<String, String> rowData){
        XWPFTableRow row = table.createRow();
        XWPFRun col1 = createTableParagraph(row, 0, false, false);
        XWPFRun col2 = createTableParagraph(row, 1, false, false);

        col1.setText(rowData.getKey());
        col2.setText(rowData.getValue());
    }

    private XWPFRun createTableParagraph(XWPFTableRow row, Integer position, boolean bold, boolean addNewCell){
        XWPFRun run = addNewCell
                ? row.addNewTableCell().addParagraph().createRun()
                : row.getCell(position).addParagraph().createRun();
        run.setFontSize(12);
        run.setBold(bold);
        run.setFontFamily("Times New Roman");
        row.getCell(position).removeParagraph(0);
        return run;
    }
}
