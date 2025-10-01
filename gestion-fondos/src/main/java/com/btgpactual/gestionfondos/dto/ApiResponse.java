package com.btgpactual.gestionfondos.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class ApiResponse<T> {

    private T data;
    private int httpCode;
    private String message;

    public ApiResponse(T data, HttpStatus status, String message) {
        this.data = data;
        this.httpCode = status.value();
        this.message = message;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(data, HttpStatus.OK, "Operación exitosa");
    }

    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(data, HttpStatus.OK, message);
    }

    public static <T> ApiResponse<T> error(HttpStatus status, String message) {
        return new ApiResponse<>(null, status, message);
    }
}