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
import com.example.demo.Repository.DeficienciaRepository;
import com.example.demo.Repository.PessoaRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private DeficienciaRepository deficienciaRepository;

    @GetMapping("/pessoa")
    public String index(Model model, @RequestParam("display") Optional<String> display){
        
        String finalDisplay = display.orElse("true");

        List<Pessoa> pessoas = pessoaRepository.findByAtivo(Boolean.valueOf(finalDisplay));

        model.addAttribute("pessoas", pessoas);
        
        return "pessoa/listar";
    }
    
    @GetMapping("/pessoa/create")
    public String create(Model model) {
        PessoaForm pessoaForm = new PessoaForm();
        pessoaForm.setDeficiencias(deficienciaRepository);
        
        model.addAttribute("pessoaForm", pessoaForm);

        return "pessoa/create";
    }
    
    @PostMapping("/pessoa/create")
    public String create(@Valid PessoaForm pessoaForm, BindingResult bindingResult, Model model, 
    RedirectAttributes redirectAttributes) {
        
        pessoaForm.setDeficiencias(deficienciaRepository);

        model.addAttribute("pessoaForm", pessoaForm);

        if(bindingResult.hasErrors()){
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "pessoa/create";
        }

        redirectAttributes.addFlashAttribute("successMessage", "Salvo com sucesso!");
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

        redirectAttributes.addFlashAttribute("successMessage", "Nome alterado com sucesso!");
        this.pessoaRepository.save(pessoa);

        return "redirect:/pessoa";
    }

    @GetMapping("/pessoa/visualizar/{id}")
    public String view(@PathVariable Long id, Model model){
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);

        PessoaForm pessoaForm = new PessoaForm(pessoa.get());
        
        model.addAttribute("pessoaForm", pessoaForm);
        model.addAttribute("id", pessoa.get().getId());

        return "pessoa/view";
    }

    @GetMapping("/pessoa/delete/{id}")
    public String remover(@PathVariable Long id, RedirectAttributes redirectAttributes){
        Optional<Pessoa> pessoa = this.pessoaRepository.findById(id);
        Pessoa pessoaModel = pessoa.get();

        if (pessoaModel.isAtivo()){
            pessoaModel.setAtivo(false);
            redirectAttributes.addFlashAttribute("successMessage", "Usuário excluído com sucesso!");
        }else{
            pessoaModel.setAtivo(true);
            redirectAttributes.addFlashAttribute("successMessage", "Usuário recuperado com sucesso!");
        }

        this.pessoaRepository.save(pessoaModel);
        
        return "redirect:/pessoa";
    }

}