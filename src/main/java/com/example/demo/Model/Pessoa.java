package com.example.demo.Model;

import java.time.LocalDate;

import org.hibernate.annotations.ColumnDefault;

import com.example.demo.Enum.Sexo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "pessoa")
public class Pessoa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "nome", nullable = false, length = 100, unique = false)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate data_nascimento;
    
    @Column(name = "sexo", nullable = false)
    @ColumnDefault("2")
    @Enumerated(EnumType.ORDINAL)
    private Sexo sexo = Sexo.NAO_INFORMADO;

    @Column(name = "ativo", nullable = false)
    @ColumnDefault("true")
    private boolean ativo = true;

    public Pessoa(String nome, LocalDate data_nascimento, Sexo sexo){
        this.nome = nome;
        this.data_nascimento = data_nascimento;
        this.sexo = sexo;
    }
}