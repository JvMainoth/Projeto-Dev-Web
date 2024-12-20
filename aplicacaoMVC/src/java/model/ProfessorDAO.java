/*Criado por Oliver Almeida*/

package model;

import entidade.Professor;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

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
    public ArrayList<Professor> listarProfessores() throws SQLException {
    ArrayList<Professor> professores = new ArrayList<>();
    Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT id, nome, email, cpf, senha FROM professores ORDER BY nome";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Professor professor = new Professor(resultado.getString("NOME"),
                            resultado.getString("EMAIL"),
                            resultado.getString("CPF"),
                            resultado.getString("SENHA"));
                    professor.setId(Integer.parseInt(resultado.getString("ID")));
                    professores.add(professor);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (ListaDeProfessores) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return professores;
    }
    
}