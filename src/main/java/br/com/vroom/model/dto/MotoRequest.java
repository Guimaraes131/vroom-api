package br.com.vroom.model.dto;
import br.com.vroom.model.CategoriaProblema;
import br.com.vroom.model.ModeloMoto;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MotoRequest {
    private Long id;

    @Size(min = 7, max = 7, message = "A placa deve ter 7 caracteres")
    private String placa;

    @Size(min = 17, max = 17, message = "O chassi deve ter 17 caracteres")
    private String chassi;

    @NotNull(message = "o modelo da moto é obrigatório")
    @Enumerated(EnumType.STRING)
    private ModeloMoto modelo;

    @NotNull(message = "o problema da moto é obrigatório")
    @Enumerated(EnumType.STRING)
    private CategoriaProblema problema;

    @NotNull(message = "o setor é obrigatório")
    private Long setorId;
}
