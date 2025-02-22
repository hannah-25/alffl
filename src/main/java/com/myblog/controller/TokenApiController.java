package com.myblog.controller;

import com.myblog.dto.CreateAcessTokenRequest;
import com.myblog.dto.CreateAcessTokenResponse;
import com.myblog.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TokenApiController {
    private final TokenService tokenService;

    @PostMapping("/api/token")
    public ResponseEntity<CreateAcessTokenResponse> createNewAcessToken(@RequestBody CreateAcessTokenRequest request){
        String newAccessToken = tokenService.createNewAcessToken(request.getRefreshToken());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateAcessTokenResponse(newAccessToken));

    }


}
