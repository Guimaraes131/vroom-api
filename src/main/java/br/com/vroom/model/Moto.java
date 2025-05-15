package br.com.vroom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Moto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 7, max = 7)
    private String placa;

    @Size(min = 17, max = 17)
    private String chassi;

    @Size(min = 5, max = 255)
    @Builder.Default
    private String descricaoProblema = "";

    @NotNull
    @Enumerated(EnumType.STRING)
    private ModeloMoto modelo;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CategoriaProblema problema;

    @ManyToOne
    @JoinColumn(name = "setor_id")
    @JsonIgnore
    private Setor setor;

    @OneToOne
    private Tag tag;
}
