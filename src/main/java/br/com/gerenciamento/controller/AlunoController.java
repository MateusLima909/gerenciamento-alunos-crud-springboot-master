package br.com.gerenciamento.controller;

import br.com.gerenciamento.service.ServiceAluno;
import br.com.gerenciamento.model.Aluno;
import br.com.gerenciamento.exception.EmailExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AlunoController {

    @Autowired
    private ServiceAluno serviceAluno;

    private static final String PATH_ALUNO = "Aluno/"; 
    private static final String VIEW_FORM  = PATH_ALUNO + "formAluno";
    private static final String VIEW_LISTA = PATH_ALUNO + "listAlunos";
    private static final String VIEW_EDIT  = PATH_ALUNO + "editar";
    private static final String VIEW_PESQ  = PATH_ALUNO + "pesquisa-resultado";
    
    private static final String REDIRECT_LISTA = "redirect:/alunos-adicionados";

    @GetMapping("/inserirAlunos")
    public ModelAndView formCadastro(Aluno aluno) {
        return new ModelAndView(VIEW_FORM).addObject("aluno", new Aluno());
    }

    @PostMapping("/InsertAlunos")
    public ModelAndView salvar(@Valid Aluno aluno, BindingResult br, RedirectAttributes attr) {
        if(br.hasErrors()) {
            return new ModelAndView(VIEW_FORM).addObject("aluno", aluno);
        }

        try {
            serviceAluno.salvar(aluno);
            attr.addFlashAttribute("message_success", "Aluno salvo com sucesso!");
            return new ModelAndView(REDIRECT_LISTA);

        } catch (EmailExistsException e) { 
            ModelAndView mv = new ModelAndView(VIEW_FORM);
            mv.addObject("aluno", aluno);
            mv.addObject("message_error", e.getMessage()); 
            return mv;
        }
    }    

    @GetMapping("/alunos-adicionados")
    public ModelAndView listar() {
        return new ModelAndView(VIEW_LISTA).addObject("alunosList", serviceAluno.listarTodos());
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        return new ModelAndView(VIEW_EDIT).addObject("aluno", serviceAluno.buscarPorId(id));
    }

    @PostMapping("/editar-aluno")
    public ModelAndView atualizar(@Valid Aluno aluno, BindingResult br, RedirectAttributes attr) {
        if(br.hasErrors()) {
            ModelAndView mv = new ModelAndView("Aluno/editar");
            mv.addObject("aluno", aluno);
            mv.addObject("message_error", "Verifique os dados informados.");
            return mv;
        }

        try {
            serviceAluno.salvar(aluno);
            attr.addFlashAttribute("message_success", "Dados do aluno atualizados com sucesso!");
            return new ModelAndView(REDIRECT_LISTA);

        } catch (EmailExistsException e) {
            ModelAndView mv = new ModelAndView("Aluno/editar");
            mv.addObject("aluno", aluno);
            mv.addObject("message_error", "Erro ao atualizar: " + e.getMessage());
            return mv;
        }
    }

    @GetMapping("/remover/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
        serviceAluno.excluir(id);
        attr.addFlashAttribute("message_success", "Aluno removido com sucesso.");
        return REDIRECT_LISTA;
    }

    @GetMapping("/filtros-alunos")
    public ModelAndView filtrosAlunos() {
        return new ModelAndView("Aluno/filtroAlunos");      
    }

    @GetMapping("/alunos-ativos")
    public ModelAndView ativos() {
        return new ModelAndView(PATH_ALUNO + "alunos-ativos")
                .addObject("alunosAtivos", serviceAluno.buscarAtivos());
    }

    @GetMapping("/alunos-inativos")
    public ModelAndView inativos() {
        return new ModelAndView(PATH_ALUNO + "alunos-inativos")
                .addObject("alunosInativos", serviceAluno.buscarInativos());
    }

    @PostMapping("/pesquisar-aluno")
    public ModelAndView pesquisar(@RequestParam(required = false) String nome) {
        List<Aluno> lista;
        if (nome == null || nome.trim().isEmpty()) {
            lista = serviceAluno.listarTodos();
        } else {
            lista = serviceAluno.pesquisarPorNome(nome);
        }
        
        return new ModelAndView(VIEW_PESQ).addObject("ListaDeAlunos", lista);
    }

    @GetMapping("/dashboard")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("aluno", new Aluno());
        return mv;
    }
}