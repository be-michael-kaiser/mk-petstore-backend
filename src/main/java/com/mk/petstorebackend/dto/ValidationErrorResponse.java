package com.mk.petstorebackend.dto;

import java.util.List;

public record ValidationErrorResponse(String message, List<FieldErrorResponse> errors) {
}
