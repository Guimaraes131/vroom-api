package br.com.vroom.model.dto;

import br.com.vroom.model.CategoriaProblema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TagRequest {

    private Long id;

    @Size(min = 2, max = 2, message = "a coordenada deve ter 2 caracteres")
    private String coordenada;

    @Enumerated(EnumType.STRING)
    private CategoriaProblema cor;

    @Builder.Default
    private Boolean disponivel = false;
}
