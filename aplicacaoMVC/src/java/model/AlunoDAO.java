/*Criado por João Mainoth*/
package model;

import entidade.Aluno;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

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
    
    public ArrayList<Aluno> listarAlunos() throws SQLException {
    ArrayList<Aluno> alunos = new ArrayList<>();
    Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM alunos ORDER BY nome";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Aluno aluno = new Aluno(
                            resultado.getString("NOME"),
                            resultado.getString("EMAIL"),
                            resultado.getString("CELULAR"),
                            resultado.getString("CPF"),
                            resultado.getString("SENHA"),
                            resultado.getString("ENDERECO"),
                            resultado.getString("CIDADE"),
                            resultado.getString("BAIRRO"),
                            resultado.getString("CEP"));
                    aluno.setId(Integer.parseInt(resultado.getString("ID")));
                    alunos.add(aluno);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (ListaDeDisciplinas) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return alunos;
    }
}