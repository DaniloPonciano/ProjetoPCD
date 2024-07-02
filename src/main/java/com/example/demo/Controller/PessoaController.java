package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.Form.Pessoa.PessoaForm;
import com.example.demo.Model.Pessoa;
import com.example.demo.Repository.PessoaRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping("/pessoa")
    public String index(Model model){
        
        List<Pessoa> pessoas = pessoaRepository.findAll();

        model.addAttribute("pessoas", pessoas);
        
        return "pessoa/listar";
    }
    
    @GetMapping("/pessoa/create")
    public String create(Model model) {
        model.addAttribute("pessoaForm", new PessoaForm());

        return "pessoa/create";
    }
    
    @PostMapping("/pessoa/create")
    public String create(@Valid PessoaForm pessoaForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        
        if(bindingResult.hasErrors()){
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "pessoa/create";
        }

        redirectAttributes.addFlashAttribute("mensagemSucesso", "Salvo com sucesso!");
        pessoaRepository.save(pessoaForm.toEntity());
        
        return "redirect:/pessoa";
    }

    @GetMapping("/pessoa/update/{id}")
    public String update(@PathVariable Long id, Model model){
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);

        PessoaForm pessoaForm = new PessoaForm(pessoa.get());
        
        model.addAttribute("pessoaForm", pessoaForm);
        model.addAttribute("id", pessoa.get().getId());

        return "pessoa/update";
    }

    @PostMapping("/pessoa/update/{id}")
    public String update(@PathVariable Long id, @Valid PessoaForm pessoaForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "pessoa/update";
        }

        Pessoa pessoa = pessoaForm.toEntity();
        pessoa.setId(id);

        redirectAttributes.addFlashAttribute("menssagemSucesso", "Nome alterado com sucesso!");
        this.pessoaRepository.save(pessoa);

        return "redirect:/pessoa/create";
    }

}