package br.com.vroom.model;

public enum CategoriaProblema {
    MECANICO("rgb(255, 0, 0)"),
    ELETRICO("rgb(0, 0, 255)"),
    DOCUMENTACAO("rgb(0, 255, 0)"),
    ESTETICO("rgb(255, 255, 0)"),
    SEGURANCA("rgb(255, 165, 0)"),
    MULTIPLO("rgb(255, 192, 203)"),
    CONFORME("rgb(255, 255, 255)");

    // Vamos utilizar o RGB posteriormente para alterar a cor do LED da TAG.

    private final String corAssociada;

    CategoriaProblema(String cor) {
        this.corAssociada = cor;
    }

    public String getCorAssociada() {
        return corAssociada;
    }
}
