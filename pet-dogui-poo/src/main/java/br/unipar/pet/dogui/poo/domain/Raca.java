package br.unipar.pet.dogui.poo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Raca {
    private int id;
    private String descricao;

    public Raca(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }
}
