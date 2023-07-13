package com.remedios.api.drogaria.controllers;


import com.remedios.api.drogaria.remedio.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/remedio")
public class RemedioController {

    @Autowired
    private RemedioRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroRemedio dados, UriComponentsBuilder uriComponentsBuilder){
        var novoRemedio= new Remedio(dados);
        repository.save(novoRemedio);

        var uri= uriComponentsBuilder.path("/remedio/{id}").buildAndExpand(novoRemedio.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoRemedio(novoRemedio));
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var remedId = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoRemedio(remedId));
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemRemedio>> listar(){

        var lista=repository.findAllByAtivoTrue().stream().map(DadosListagemRemedio :: new).toList();
        return ResponseEntity.ok(lista);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoRemedio> atualizar(@RequestBody @Valid DadosAtualizarRemedio dados){

        var remedId=repository.getReferenceById(dados.id());
        remedId.atualizarInformacoes(dados);
        repository.save(remedId);

        return ResponseEntity.ok(new DadosDetalhamentoRemedio(remedId));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable  Long id){
        var remedio=repository.getReferenceById(id);
        remedio.inativar();

        return ResponseEntity.noContent().build();
    }

}
