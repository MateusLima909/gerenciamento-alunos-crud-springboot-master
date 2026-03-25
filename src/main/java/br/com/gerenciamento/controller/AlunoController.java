package br.com.gerenciamento.controller;

import br.com.gerenciamento.model.Aluno;
import br.com.gerenciamento.service.ServiceAluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AlunoController {

    @Autowired
    private ServiceAluno serviceAluno;

    @GetMapping("/dashboard")
    public ModelAndView dashboard() {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("mediaAtivos", serviceAluno.calcularMediaAtivos());
        mv.addObject("quantidadeAtivos", serviceAluno.buscarAtivos().size());
        return mv;
    }

    @GetMapping("/notas-enade")
    public ModelAndView notasEnade() {
        ModelAndView mv = new ModelAndView("Aluno/notas-enade");
        
        List<Aluno> ativos = serviceAluno.buscarAtivos();
        Double mediaAtivos = serviceAluno.calcularMediaAtivos();

        mv.addObject("alunosAtivos", ativos); 
        mv.addObject("mediaAtivos", mediaAtivos);
        mv.addObject("quantidadeAtivos", ativos.size());
        return mv;
    }

    @GetMapping("/inserirAlunos")
    public ModelAndView inserirAlunos() {
        ModelAndView mv = new ModelAndView("Aluno/formAluno");
        mv.addObject("aluno", new Aluno());
        return mv;
    }

    @PostMapping("/salvarAluno")
    public String salvarAluno(@ModelAttribute Aluno aluno) throws Exception {
        serviceAluno.salvar(aluno);
        return "redirect:/dashboard";
    }

    @GetMapping("/alunos-ativos")
    public ModelAndView listarAtivos() {
        ModelAndView mv = new ModelAndView("Aluno/alunosAtivos");
        mv.addObject("alunosAtivos", serviceAluno.buscarAtivos());
        return mv;
    }

    @GetMapping("/alunos-inativos")
    public ModelAndView listarInativos() {
        ModelAndView mv = new ModelAndView("Aluno/alunosInativos");
        mv.addObject("alunosInativos", serviceAluno.buscarInativos());
        return mv;
    }

    @GetMapping("/remover/{id}")
    public String removerAluno(@PathVariable("id") Long id) {
        serviceAluno.excluir(id);
        return "redirect:/dashboard";
    }
    
    @PostMapping("/pesquisar-aluno")
    public ModelAndView pesquisarAluno(@RequestParam("nome") String nome) {
        ModelAndView mv = new ModelAndView("Aluno/resultadoPesquisa");
        mv.addObject("alunos", serviceAluno.pesquisarPorNome(nome));
        return mv;
    }
}