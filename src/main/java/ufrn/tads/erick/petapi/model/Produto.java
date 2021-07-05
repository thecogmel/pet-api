package ufrn.tads.erick.petapi.model;

import lombok.Getter;
import lombok.Setter;

public class Produto {
    @Getter @Setter private int id;
    @Getter @Setter private String nome;
    @Getter @Setter private String raca;
    @Getter @Setter private String especie;
    @Getter @Setter private String marca;
    @Getter @Setter private float preco;
    @Getter @Setter private String categoria;
}
