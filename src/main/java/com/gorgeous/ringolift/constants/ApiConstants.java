package com.gorgeous.ringolift.constants;

import java.util.List;

public class ApiConstants {
    public static final List<String> PUBLIC_ENDPOINTS = List.of(
            "/auth/register",
            "/auth/login",
            "/auth/validate-token",
            "/auth/logout",
            "/users/genders",
            "/users/genders/**"
    );
}
