package br.com.gerenciamento.controller;

import br.com.gerenciamento.model.Aluno;
import br.com.gerenciamento.model.Usuario;
import br.com.gerenciamento.service.ServiceUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UsuarioController {

    @Autowired
    private ServiceUsuario serviceUsuario;

    private static final String VIEW_LOGIN = "login/login";
    private static final String VIEW_CADASTRO = "login/cadastro";
    private static final String REDIRECT_INDEX = "redirect:/index";
    private static final String REDIRECT_HOME = "redirect:/";

    @GetMapping("/")
    public ModelAndView login() {
        return new ModelAndView(VIEW_LOGIN).addObject("usuario", new Usuario());
    }

    @GetMapping("/cadastro")
    public ModelAndView cadastrar() {
        return new ModelAndView(VIEW_CADASTRO).addObject("usuario", new Usuario());
    }

    @GetMapping("/index")
    public ModelAndView index(HttpSession session) {
        if (session.getAttribute("usuarioLogado") == null) return new ModelAndView(REDIRECT_HOME);
        
        return new ModelAndView("home/index").addObject("aluno", new Aluno());
    }

    @PostMapping("/salvarUsuario")
    public ModelAndView salvar(@Valid Usuario usuario, BindingResult br, RedirectAttributes attr) {
        if (br.hasErrors()) return retornarParaCadastro(usuario);

        try {
            serviceUsuario.salvarUsuario(usuario);
            attr.addFlashAttribute("message", "Cadastro realizado com sucesso!");
            return new ModelAndView(REDIRECT_HOME);
        } catch (Exception e) {
            return retornarParaCadastro(usuario).addObject("message", e.getMessage());
        }
    }

    @PostMapping("/login")
    public ModelAndView realizarLogin(Usuario usuario, HttpSession session) {
        try {
            // A mágica do MD5 foi para dentro do Service!
            Usuario userLogin = serviceUsuario.loginUser(usuario.getUser(), usuario.getSenha());
            
            if (userLogin == null) {
                return new ModelAndView(VIEW_LOGIN).addObject("message", "Usuário ou senha inválidos.");
            }

            session.setAttribute("usuarioLogado", userLogin);
            return new ModelAndView(REDIRECT_INDEX);
        } catch (Exception e) {
            return new ModelAndView(VIEW_LOGIN).addObject("message", "Erro ao processar login.");
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return REDIRECT_HOME;
    }

    private ModelAndView retornarParaCadastro(Usuario usuario) {
        return new ModelAndView(VIEW_CADASTRO).addObject("usuario", usuario);
    }
}
