package br.com.vroom.model;

public enum CategoriaProblema {
    MECANICO("Vermelho"),
    ELETRICO("Azul"),
    DOCUMENTACAO("Verde"),
    ESTETICO("Amarelo"),
    SEGURANCA("Laranja"),
    CONFORME("Branco");

    private final String corAssociada;

    CategoriaProblema(String cor) {
        this.corAssociada = cor;
    }

    public String getCorAssociada() {
        return corAssociada;
    }
}
