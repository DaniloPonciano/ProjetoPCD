package com.example.demo.Form.Pessoa;

import java.time.LocalDate;

import com.example.demo.Enum.Sexo;
import com.example.demo.Model.Pessoa;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PessoaForm {

    @NotBlank(message = "Preencha o campo nome.")
    private String nome;

    @NotNull(message = "Preencha a data de nascimento.")
    @Past(message = "A data de nascimento tem que ser anterior a atual.")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate data_nascimento;

    @NotNull(message = "Preencha o campo sexo.")
    private int sexo;

    public Pessoa toEntity(){
        Sexo sexo = Sexo.fromCodigo(this.sexo);
        return new Pessoa(nome, data_nascimento, sexo);
    }

    public PessoaForm(Pessoa pessoa){
        this.nome = pessoa.getNome();
    }
    

}