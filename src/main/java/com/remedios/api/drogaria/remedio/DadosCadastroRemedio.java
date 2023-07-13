package com.remedios.api.drogaria.remedio;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosCadastroRemedio(
        @NotBlank(message = "O campo nao pode ser nulo nem em branco")
        String nome,
        @Enumerated
        Via via,
        @NotBlank
        String lote,
        @NotNull
        int quantidade,
        @Future
        LocalDate validade,
        @Enumerated
        Laboratorio laboratorio) {
}
