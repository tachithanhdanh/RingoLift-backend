package com.gorgeous.ringolift.controllers;

import com.gorgeous.ringolift.services.AuthenticationService;
import com.gorgeous.ringolift.components.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// The AuthController class is responsible for handling the authentication requests.
// It is annotated with @RestController, which indicates that this class will handle
// HTTP requests and return the response as JSON data.
@RequestMapping("${api.prefix}/auth")
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
}
