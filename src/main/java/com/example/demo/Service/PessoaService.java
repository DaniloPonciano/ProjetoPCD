package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Enum.Sexo;
import com.example.demo.Form.Pessoa.PessoaForm;
import com.example.demo.Model.Pessoa;
import com.example.demo.Repository.BairroRepository;
import com.example.demo.Repository.CidadeRepository;
import com.example.demo.Repository.EnderecoRepository;
import com.example.demo.Repository.PessoaRepository;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private BairroRepository bairroRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;


    public List<Pessoa> FindAll(){
        return pessoaRepository.findAll();
    }

    public Pessoa create(PessoaForm pessoaForm){
        Pessoa pessoa = new Pessoa();

        pessoa.setNome(pessoaForm.getNome());
        pessoa.setData_nascimento(pessoaForm.getData_nascimento());
        pessoa.setDeficiencia(pessoaForm.getDeficiencia());
        
        Sexo sexo = Sexo.fromCodigo(pessoaForm.getSexo());
        pessoa.setSexo(sexo);

    }

}
