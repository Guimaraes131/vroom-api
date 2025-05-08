package br.com.vroom.model.dto;

import br.com.vroom.model.CargoUsuario;

public record UsuarioResponse(Long id, String email, CargoUsuario role) {}
