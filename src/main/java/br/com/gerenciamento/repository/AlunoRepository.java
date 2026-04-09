package br.com.gerenciamento.repository;

import br.com.gerenciamento.enums.Status;
import br.com.gerenciamento.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    @Query("SELECT a FROM Aluno a WHERE a.status = 'ATIVO' ")
    public List<Aluno> findByStatusAtivo();

    @Query("SELECT i FROM Aluno i WHERE i.status = 'INATIVO' ")
    public List<Aluno> findByStatusInativo();

    @Query("SELECT a FROM Aluno a WHERE LOWER(a.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Aluno> encontrarPorNome(@Param("nome") String nome);

    @Query("SELECT a FROM Aluno a WHERE a.status = 'ATIVO' AND a.notaEnade >= :media")
    List<Aluno> findByNotaMaiorOuIgualMedia(@Param("media") Double media);

    List<Aluno> findByStatus(Status status);

    Aluno findByEmail(String email);

}
