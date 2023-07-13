package com.remedios.api.drogaria.controllers;

import com.remedios.api.drogaria.infra.DadosTokenJWT;
import com.remedios.api.drogaria.infra.TokenService;
import com.remedios.api.drogaria.usuario.AuthService;
import com.remedios.api.drogaria.usuario.DadosAuth;
import com.remedios.api.drogaria.usuario.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> efetuarLogin(@RequestBody @Valid DadosAuth dados){
        var token = new UsernamePasswordAuthenticationToken(dados.login(),dados.senha());
        var authentication=authenticationManager.authenticate(token);
        var tokenJWT=tokenService.gerarToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

}
