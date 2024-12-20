/*Criado por Oliver Almeida*/

package model;

import entidade.Professor;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProfessorDAO {
    public void inserir(Professor Professor) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement stmt = conexao.getConexao().prepareStatement(
                "INSERT INTO professores (nome, email, cpf, senha)" + "VALUES (?, ?, ?, ?)"
            );
            stmt.setString(1, Professor.getNome());
            stmt.setString(2, Professor.getEmail());
            stmt.setString(3, Professor.getCpf());
            stmt.setString(4, Professor.getSenha());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }
}