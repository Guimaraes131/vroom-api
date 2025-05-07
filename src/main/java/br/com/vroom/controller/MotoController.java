package br.com.vroom.controller;

import br.com.vroom.model.Moto;
import br.com.vroom.model.Setor;
import br.com.vroom.model.dto.MotoRequest;
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
@RequestMapping("/motos")
public class MotoController {

    @Autowired
    private MotoRepository repository;

    @Autowired
    private SetorRepository setorRepository;

    @GetMapping
    public List<Moto> index() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Moto get(@PathVariable Long id) {
        return getMotoById(id);
    }

    @PostMapping
    public ResponseEntity<Moto> create(@RequestBody @Valid MotoRequest motoRequest) {
        
        Setor setor = getSetorById(motoRequest.getSetorId());

        var moto = Moto.builder()
                    .chassi(motoRequest.getChassi())
                    .id(motoRequest.getId())
                    .modelo(motoRequest.getModelo())
                    .placa(motoRequest.getPlaca())
                    .problema(motoRequest.getProblema())
                    .setor(setor)
                    .build();

        repository.save(moto);

        return ResponseEntity.status(201).body(moto);
    }

    @DeleteMapping("{id}")
    public void destroy(@PathVariable Long id) {
        repository.delete(getMotoById(id));
    }

    @PutMapping("{id}")
    public Moto update(@PathVariable Long id, @RequestBody @Valid MotoRequest motoRequest) {
        motoRequest.setId(id);

        Setor setor = getSetorById(motoRequest.getSetorId());

        var moto = Moto.builder()
                    .chassi(motoRequest.getChassi())
                    .id(motoRequest.getId())
                    .modelo(motoRequest.getModelo())
                    .placa(motoRequest.getPlaca())
                    .problema(motoRequest.getProblema())
                    .setor(setor)
                    .build();

        return repository.save(moto);
    }

    private Moto getMotoById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
                );
    }

    private Setor getSetorById(Long id) {
        return setorRepository
                .findById(id)
                .orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
                );
    }
}
