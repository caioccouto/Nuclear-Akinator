package br.com.nuclear_akinator.dto;

import java.time.LocalDateTime;

public record ErrorCustomResponse(
        LocalDateTime timestamp, int status, String error, String message) {
}
