package br.com.vroom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.vroom.model.Tag;
import br.com.vroom.model.dto.TagRequest;
import br.com.vroom.repository.TagRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tags")
public class TagController {
    
    @Autowired
    private TagRepository repository;

    @Operation(tags = "Tag", summary = "Listar tags paginadas", description = "Devolve a lista de tags paginadas")
    @Cacheable("tags")
    @GetMapping
    public Page<Tag> index(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        
        return repository.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Tag get(@PathVariable("id") Long id) {
        return getTagById(id);
    }

    @PostMapping
    @Operation(responses = @ApiResponse(responseCode = "400", description = "Validação falhou"))
    public ResponseEntity<Tag> create(@RequestBody @Valid TagRequest tagRequest) {

        var tag = Tag.builder()
                    .id(tagRequest.getId())
                    .coordenada(tagRequest.getCoordenada())
                    .cor(tagRequest.getCor().getCorAssociada())
                    .disponivel(tagRequest.getDisponivel())
                    .build();

        repository.save(tag);

        return ResponseEntity.status(201).body(tag);
    }

    @PutMapping("/{id}")
    @CacheEvict(value = "tags", allEntries = true)
    public ResponseEntity<Tag> update(@PathVariable("id") Long id, @RequestBody @Valid TagRequest tagRequest) {

        var tag = Tag.builder()
                    .id(id)
                    .cor(tagRequest.getCor().getCorAssociada())
                    .disponivel(tagRequest.getDisponivel())
                    .coordenada(tagRequest.getCoordenada())
                    .build();

        return ResponseEntity.status(201).body(tag);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "tags", allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable("id") Long id) {
        repository.delete(getTagById(id));
    }

    private Tag getTagById(Long id) {
        return repository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

}
