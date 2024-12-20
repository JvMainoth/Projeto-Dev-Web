/*Criado por João Mainoth*/
package model;

import entidade.Administrador;
import entidade.Aluno;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


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
            System.err.println("Query de insert (nome, email, celular, cpf, senha, endereco, cidade, bairro, cep) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    //teste
    public void alterar(Aluno Aluno) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE Alunos SET nome = ?, email = ?, celular = ?,"
                    + " cpf = ?, senha = ?,  endereco = ?, cidade = ?, bairro= ?, cep = ?  WHERE ID = ? ");
            sql.setString(1, Aluno.getNome());
            sql.setString(2, Aluno.getEmail());
            sql.setString(3, Aluno.getCelular());
            sql.setString(4, Aluno.getCpf());
            sql.setString(5, Aluno.getSenha());
            sql.setString(6, Aluno.getEndereco());
            sql.setString(7, Aluno.getCidade());
            sql.setString(8, Aluno.getBairro());
            sql.setString(9, Aluno.getCep());
            sql.setInt(10, Aluno.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
    public void excluir(Aluno Aluno) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM Alunos WHERE ID = ? ");
            sql.setInt(1, Aluno.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    

//teste para listar
    public Aluno getAluno(int id) {
        Conexao conexao = new Conexao();
        Aluno Aluno = new Aluno();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM Alunos WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();

            if (resultado != null) {
                while (resultado.next()) {
                    Aluno.setId(Integer.parseInt(resultado.getString("ID")));
                    Aluno.setNome(resultado.getString("NOME"));
                    Aluno.setCpf(resultado.getString("CPF"));
                    Aluno.setEndereco(resultado.getString("ENDERECO"));
                    Aluno.setSenha(resultado.getString("SENHA"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (get categoria) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return Aluno;
    }
    
    
    
    //Teste para listar
    public ArrayList<Aluno> ListaDeAlunos() {
        ArrayList<Aluno> meusAlunos = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM alunos order by nome";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Aluno Aluno = new Aluno(resultado.getString("NOME"),
                            resultado.getString("EMAIL"),
                            resultado.getString("CELULAR"),
                            resultado.getString("CPF"),
                            resultado.getString("SENHA"),
                            resultado.getString("ENDERECO"),
                            resultado.getString("CIDADE"),
                            resultado.getString("BAIRRO"),
                            resultado.getString("CEP")               
                    );
                            
                    Aluno.setId(Integer.parseInt(resultado.getString("id")));
                    meusAlunos.add(Aluno);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (ListaDeAdministradores) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return meusAlunos;
    }
}