package br.com.vroom.controller;

import br.com.vroom.model.Setor;
import br.com.vroom.repository.MotoRepository;
import br.com.vroom.repository.SetorRepository;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/setores")
public class SetorController {
    
    @Autowired
    private SetorRepository repository;

    @Autowired
    private MotoRepository motoRepository;

    @GetMapping
    public List<Setor> index() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Setor get(@PathVariable Long id) {
        return getSetorById(id);
    }

    @PostMapping
    public ResponseEntity<Setor> create(@RequestBody @Valid Setor setor) {
        repository.save(setor);

        return ResponseEntity.status(201).body(setor);
    }

    @DeleteMapping("{id}")
    public void destroy(@PathVariable Long id) {
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
