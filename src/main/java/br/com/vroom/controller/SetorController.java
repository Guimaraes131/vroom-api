package br.com.vroom.controller;

import br.com.vroom.model.Setor;
import br.com.vroom.repository.MotoRepository;
import br.com.vroom.repository.SetorRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@RestController
@RequestMapping("/setores")
public class SetorController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private SetorRepository repository;

    @Autowired
    private MotoRepository motoRepository;

    @GetMapping
    @Operation(tags = "Setor", summary = "Listar setores paginados", description = "Devolve a lista de setores paginados")
    @Cacheable("setores")
    public Page<Setor> index(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        
        return repository.findAll(pageable);
    }

    @GetMapping("{id}")
    public Setor get(@PathVariable Long id) {
        return getSetorById(id);
    }

    @PostMapping
    @Operation(responses = @ApiResponse(responseCode = "400", description = "Validação falhou"))
    @CacheEvict(value = "setores", allEntries = true)
    public ResponseEntity<Setor> create(@RequestBody @Valid Setor setor) {
        log.info("Cadastrando setor " + setor.getNome());
        repository.save(setor);

        return ResponseEntity.status(201).body(setor);
    }

    @DeleteMapping("{id}")
    @CacheEvict(value = "setores", allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("Apagando setor " + id + "e sua lista de motos");

        getSetorById(id).getMotos().forEach(moto -> moto.setSetor(null));
        
        motoRepository.deleteAll(getSetorById(id).getMotos());
        repository.delete(getSetorById(id));
    }

    private Setor getSetorById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
                );
    }
}
