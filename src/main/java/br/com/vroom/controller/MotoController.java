package br.com.vroom.controller;

import br.com.vroom.model.Moto;
import br.com.vroom.model.Setor;
import br.com.vroom.model.Tag;
import br.com.vroom.model.dto.MotoRequest;
import br.com.vroom.repository.MotoRepository;
import br.com.vroom.repository.SetorRepository;
import br.com.vroom.repository.TagRepository;
import br.com.vroom.service.MotoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/motos")
public class MotoController {
	private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MotoRepository repository;

    @Autowired
    private MotoService motoService;

    @Autowired
    private SetorRepository setorRepository;

    @Autowired
    private TagRepository tagRepository;

    @Operation(tags = "Moto", summary = "Listar motos", description = "Devolve a lista de motos")
    @Cacheable("motos")
    @GetMapping
    public List<Moto> index(@RequestParam(required = false) String ordem) {
        return motoService.listarMotos(ordem);
    }

    @GetMapping("{id}")
    public Moto get(@PathVariable Long id) {
        return getMotoById(id);
    }

    @PostMapping
    @Operation(responses = @ApiResponse(responseCode = "400", description = "Validação falhou"))
    public ResponseEntity<Moto> create(@RequestBody @Valid MotoRequest motoRequest) {
        log.info("Cadastrando moto " + motoRequest.getPlaca());

        Setor setor = getSetorById(motoRequest.getSetorId());
        Tag tag = getTagByCoordenada(motoRequest.getTagCoordenada());

        tag.setDisponivel(false);
        
        var moto = Moto.builder()
                    .chassi(motoRequest.getChassi())
                    .id(motoRequest.getId())
                    .modelo(motoRequest.getModelo())
                    .placa(motoRequest.getPlaca())
                    .problema(motoRequest.getProblema())
                    .setor(setor)
                    .tag(tag)
                    .build();

        repository.save(moto);

        return ResponseEntity.status(201).body(moto);
    }

    @DeleteMapping("{id}")
    @CacheEvict(value = "motos", allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("Apagando moto " + id);
        repository.delete(getMotoById(id));
    }

    @PutMapping("{id}")
    @CacheEvict(value = "motos", allEntries = true)
    public Moto update(@PathVariable Long id, @RequestBody @Valid MotoRequest motoRequest) {
        log.info("Atualizando moto " + id + " para " + motoRequest);

        Setor setor = getSetorById(motoRequest.getSetorId());
        Tag tag = getTagByCoordenada(motoRequest.getTagCoordenada());

        var moto = Moto.builder()
                    .chassi(motoRequest.getChassi())
                    .id(id)
                    .modelo(motoRequest.getModelo())
                    .placa(motoRequest.getPlaca())
                    .problema(motoRequest.getProblema())
                    .setor(setor)
                    .tag(tag)
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

    private Tag getTagByCoordenada(String coordenada) {
        return tagRepository
                .findByCoordenada(coordenada)
                .orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
                );
    }
}
