/*Criado por João Mainoth*/
package model;

import entidade.Aluno;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AlunoDAO {
    public void inserir(Aluno Aluno) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement stmt = conexao.getConexao().prepareStatement(
                "INSERT INTO alunos (nome, email, celular, cpf, senha, endereco, cidade, bairro, cep)" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );
            stmt.setString(1, Aluno.getNome());
            stmt.setString(2, Aluno.getEmail());
            stmt.setString(3, Aluno.getCelular());
            stmt.setString(4, Aluno.getCpf());
            stmt.setString(5, Aluno.getSenha());
            stmt.setString(6, Aluno.getEndereco());
            stmt.setString(7, Aluno.getCidade());
            stmt.setString(8, Aluno.getBairro());
            stmt.setString(9, Aluno.getCep());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }
}