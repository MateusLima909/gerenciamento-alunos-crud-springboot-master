package br.com.gerenciamento.service;

import br.com.gerenciamento.exception.EmailExistsException;
import br.com.gerenciamento.model.Aluno;
import br.com.gerenciamento.repository.AlunoRepository;
import br.com.gerenciamento.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ServiceAluno {

    @Autowired
    private AlunoRepository alunoRepository;

    public void salvar(Aluno aluno) throws EmailExistsException { 
        Objects.requireNonNull(aluno, "O objeto aluno não pode ser nulo");
        
        validarEmailUnico(aluno);
        alunoRepository.save(aluno);
    }

    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    public Aluno buscarPorId(Long id) {
        Objects.requireNonNull(id, "O ID não pode ser nulo");
        
        return alunoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado com o ID: " + id));
    }

    public void excluir(Long id) {
        Objects.requireNonNull(id, "O ID não pode ser nulo para exclusão");
        
        alunoRepository.deleteById(id);
    }

    public List<Aluno> buscarAtivos() {
        return alunoRepository.findByStatus(Status.ATIVO);
    }

    public List<Aluno> buscarInativos() {
        return alunoRepository.findByStatus(Status.INATIVO);
    }

    public List<Aluno> pesquisarPorNome(String nome) {
        return alunoRepository.findByNomeContainingIgnoreCase(nome);
    }

    private void validarEmailUnico(Aluno aluno) throws EmailExistsException {
        String email = Objects.requireNonNull(aluno.getEmail(), "O e-mail do aluno é obrigatório");
        
        Aluno alunoExistente = alunoRepository.findByEmail(email);
        
        if (alunoExistente != null && !alunoExistente.getId().equals(aluno.getId())) {
            throw new EmailExistsException("Já existe um aluno cadastrado com este e-mail: " + email);
        }
    }
}