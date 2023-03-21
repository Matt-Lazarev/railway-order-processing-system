ymaps.ready(init);

function init() {
    let myMap = new ymaps.Map("map", {
        center: [55.73, 37.75],
        zoom: 8
    }, {
        searchControlProvider: 'yandex#search'
    });

    let clusterer = getCluster()

    myMap.geoObjects.add(clusterer);
    myMap.setBounds(clusterer.getBounds(), {
        checkZoomRange: true
    });
}

function getCluster() {
    let clusterer = new ymaps.Clusterer({
        clusterIconShape: {
            type: 'Rectangle',
            coordinates: [[0, 0], [50, 50]]
        },
        clusterDisableClickZoom: true,
        clusterHideIconOnBalloonOpen: false,
        geoObjectHideIconOnBalloonOpen: false,
    });

    let getPointData = function (station) {
        // let rate = planfact.rateFact !== "null" ? 'Ставка согласована: ' + planfact.rateFact : 'Ставка не задана';
        return {
            balloonContentBody:
                '<p>Станция: ' + station.name + '</p>' +
                '<p>Код: ' + station.code + '</p>'
            // ,
            // clusterCaption: '<strong>' + planfact.station + "(" + planfact.departureStation + ")" + '</strong>',
            // iconContent: planfact.planQuantity
        };
    }

    let getPointOptions = function () {
        return {
            preset: 'islands#violetIcon'
        };
    }

    let points = getMapObjects()

    let geoObjects = [];
    for (let i = 0, len = points.length; i < len; i++) {
        let coordinates = [points[i].latitude, points[i].longitude]
        geoObjects[i] = new ymaps.Placemark(coordinates, getPointData(points[i]), getPointOptions());
        geoObjects[i].orders = points[i].planQuantity
    }
    clusterer.add(geoObjects);
    return clusterer
}

function getMapObjects() {
    let clientOrderId = document.getElementById("client-order-id").textContent
    let wagonsRequestUrl = new URL('http:/localhost:8081/api/wagons?clientOrderId='+clientOrderId);

    let wagonsRequest = new XMLHttpRequest();
    wagonsRequest.open('GET', wagonsRequestUrl, false);
    wagonsRequest.send(null);

    if (wagonsRequest.status === 200) {
        let wagons = JSON.parse(wagonsRequest.responseText)

        wagons.forEach(wagon => {
            if(wagon){
                wagon.name = wagon.name ? wagon.name : ""
                wagon.latitude = wagon.latitude ? wagon.latitude : ""
                wagon.longitude = wagon.longitude ? wagon.longitude : ""
            }
        })

        return wagons.filter(wagon => wagon.latitude !== "" && wagon.longitude !== "")
    }

    alert("Ошибка загрузки данных. Повторите запрос позже")
}
