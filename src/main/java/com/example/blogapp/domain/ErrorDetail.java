package com.example.blogapp.domain;

import java.util.Date;

public record ErrorDetail(Date timestamp, String message, String details) {
}
