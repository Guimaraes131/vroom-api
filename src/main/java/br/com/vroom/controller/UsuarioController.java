package br.com.vroom.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.vroom.model.Usuario;
import br.com.vroom.model.dto.UsuarioResponse;
import br.com.vroom.repository.UsuarioRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @GetMapping
    public List<UsuarioResponse> index() {
        List<Usuario> usuarios = repository.findAll();
        
        return usuarios.stream()
            .map(usuario -> new UsuarioResponse(usuario.getId(), usuario.getEmail(), usuario.getRole()))
            .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public UsuarioResponse get(@PathVariable Long id) {
        Usuario usuario = getUsuarioById(id);
        
        return new UsuarioResponse(usuario.getId(), usuario.getEmail(), usuario.getRole());
    }
    
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public UsuarioResponse create(@RequestBody @Valid Usuario usuario){
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        
        var usuarioSalvo = repository.save(usuario);
        
        return new UsuarioResponse(usuarioSalvo.getId(), usuarioSalvo.getEmail(), usuarioSalvo.getRole());
    }

    @DeleteMapping("{id}")
    public void destroy(@PathVariable Long id) {
        repository.delete(getUsuarioById(id));
    }

    private Usuario getUsuarioById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
                );
            
    }
}
