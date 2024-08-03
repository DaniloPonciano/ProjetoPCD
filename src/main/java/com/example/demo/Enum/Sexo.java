package com.example.demo.Enum;

import lombok.Getter;

@Getter
public enum Sexo {

    FEMININO(0, 'F', "Feminino"),
    MASCULINO(1, 'M', "Masculino"),
    NAO_INFORMADO(2, 'N',"Não informado");

    private final int codigo;
    private final char abreviacao;
    private final String descricao;

    Sexo(int codigo, char abreviacao, String descricao){
        this.codigo = codigo;
        this.abreviacao = abreviacao;
        this.descricao = descricao;
    }

    public static Sexo fromCodigo(int codigo){
        for(Sexo sexo : values()){
            if(sexo.getCodigo() == codigo){
                return sexo;
            }
        }

        return Sexo.NAO_INFORMADO;
    }
}
