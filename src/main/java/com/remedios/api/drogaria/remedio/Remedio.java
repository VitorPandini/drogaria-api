package com.remedios.api.drogaria.remedio;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

@Entity(name="Remedio")
@Table(name = "remedio")
public class Remedio {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private Via via;

    private String lote;

    private int quantidade;

    private LocalDate validade;

    @Enumerated(EnumType.STRING)
    private Laboratorio laboratorio;

    private Boolean ativo;

    public Remedio(DadosCadastroRemedio dados){
        this.ativo= true;
        this.nome=dados.nome();
        this.via=dados.via();
        this.lote=dados.lote();
        this.quantidade=dados.quantidade();
        this.validade=dados.validade();
        this.laboratorio=dados.laboratorio();
    }

    public void atualizarInformacoes(DadosAtualizarRemedio dados) {

        if(dados.nome() != null){
            this.nome= dados.nome();
        }if(dados.via() != null){
            this.via=dados.via();
        }if (dados.laboratorio() != null){
            this.laboratorio= dados.laboratorio();
        }


    }

    public void inativar() {
        this.ativo=false;
    }
}
