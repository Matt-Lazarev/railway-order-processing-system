package com.lazarev.model;

import java.time.LocalDateTime;

public record ErrorResponse (LocalDateTime timestamp,
                             Integer status,
                             Boolean success,
                             String message) { }
