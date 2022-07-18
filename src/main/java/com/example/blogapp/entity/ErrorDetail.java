package com.example.blogapp.entity;

import java.util.Date;

public record ErrorDetail(Date timestamp, String message, String details) {
}
