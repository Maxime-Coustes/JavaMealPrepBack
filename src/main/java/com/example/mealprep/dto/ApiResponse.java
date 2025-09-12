package com.example.mealprep.dto;

import java.util.List;

public class ApiResponse<T> {
    private boolean success;
    private String message;
    private List<String> errors;
    private T data;

    public ApiResponse() {}

    public ApiResponse(boolean success, String message, T data, List<String> errors) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.errors = errors;
    }

    // ✅ Builders statiques pratiques
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, message, data, null);
    }

    public static <T> ApiResponse<T> error(String message, List<String> errors) {
        return new ApiResponse<>(false, message, null, errors);
    }

    // Getters/Setters
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public List<String> getErrors() { return errors; }
    public void setErrors(List<String> errors) { this.errors = errors; }

    public T getData() { return data; }
    public void setData(T data) { this.data = data; }
}
