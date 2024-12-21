/*Criado por Oliver Almeida e João Mainoth*/
package model;

import entidade.Turma;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

public class TurmaDAO {
    public void inserir(Turma Turma) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement stmt = conexao.getConexao().prepareStatement(
                "INSERT INTO turmas (professor_id, disciplina_id, aluno_id, codigo_turma, nota)" + "VALUES (?, ?, ?, ?, ?)"
            );
            stmt.setInt(1, Turma.getProfessor_id());
            stmt.setInt(2, Turma.getDisciplina_id());
            stmt.setInt(3, Turma.getAluno_id());
            stmt.setString(4, Turma.getCodigo());
            stmt.setInt(5, Turma.getNota());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }
    public Turma getTurma(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            Turma Turma = new Turma();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM turmas WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Turma.setId(Integer.parseInt(resultado.getString("ID")));
                    Turma.setProfessor_id(Integer.parseInt(resultado.getString("PROFESSOR_ID")));
                    Turma.setDisciplina_id(Integer.parseInt(resultado.getString("DISCIPLINA_ID")));
                    Turma.setAluno_id(Integer.parseInt(resultado.getString("ALUNO_ID")));
                    Turma.setCodigo(resultado.getString("CODIGO_TURMA"));
                    Turma.setNota(Integer.parseInt(resultado.getString("NOTA")));
                    

                }
            }
            return Turma;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    public ArrayList<Turma> listarTurmas() throws SQLException {
    ArrayList<Turma> turmas = new ArrayList<>();
    Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM turmas ORDER BY codigo_turma";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Turma turma = new Turma(
                            resultado.getInt("professor_id"),
                            resultado.getInt("disciplina_id"),
                            resultado.getInt("aluno_id"),
                            resultado.getString("codigo_turma"),
                            resultado.getInt("nota"));
                    turma.setId(Integer.parseInt(resultado.getString("ID")));
                    turmas.add(turma);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (ListaDeTurmas) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return turmas;
    }
    /*Criado por Oliver Almeida*/
    public void alterar(Turma Turma) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE turmas SET professor_id = ?, disciplina_id = ?, aluno_id = ?, codigo_turma = ?, nota = ? WHERE ID = ? ");
            sql.setInt(1, Turma.getProfessor_id());
            sql.setInt(2, Turma.getDisciplina_id());
            sql.setInt(3, Turma.getAluno_id());
            sql.setString(4, Turma.getCodigo());
            sql.setFloat(5, Turma.getNota());
            sql.setInt(6, Turma.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
    public void excluir(Turma Turma) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM turmas WHERE ID = ? ");
            sql.setInt(1, Turma.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
}
