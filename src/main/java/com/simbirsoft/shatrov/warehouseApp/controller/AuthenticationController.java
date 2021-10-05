package com.simbirsoft.shatrov.warehouseApp.controller;


import com.simbirsoft.shatrov.warehouseApp.security.AuthenticationRequestDTO;
import com.simbirsoft.shatrov.warehouseApp.security.JwtTokenProvider;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Tag(name = "Authentication page")
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Operation(summary = "JWT login URL")
    @ApiResponse(description = "Login success.", responseCode = "200",
            content = @Content(mediaType = "application/json", examples = @ExampleObject("{\n" +
                    "    \"login\": \"admin\",\n" +
                    "    \"token\": \"token value\"\n" +
                    "}")))
    @ApiResponse(description = "login:password pair invalid or banned!", responseCode = "403", content = @Content)
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDTO authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getLogin(),
                    authRequest.getPassword()));
            String token = jwtTokenProvider.createToken(authRequest.getLogin());
            Map<Object, Object> response = new HashMap<>();
            response.put("login", authRequest.getLogin());
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("login:password pair invalid or banned!", HttpStatus.FORBIDDEN);
        }
    }

}
