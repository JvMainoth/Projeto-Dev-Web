/*Criado por Oliver Almeida*/

package model;

import entidade.Disciplina;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

public class DisciplinaDAO {
    public void inserir(Disciplina Disciplina) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement stmt = conexao.getConexao().prepareStatement(
                "INSERT INTO disciplina (nome, requisito, ementa, carga_horaria)" + "VALUES (?, ?, ?, ?)"
            );
            stmt.setString(1, Disciplina.getNome());
            stmt.setString(2, Disciplina.getRequisito());
            stmt.setString(3, Disciplina.getEmenta());
            stmt.setShort(4, Disciplina.getCarga_horaria());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }
    public Disciplina getDisciplina(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            Disciplina Disciplina = new Disciplina();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM disciplina WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Disciplina.setId(Integer.parseInt(resultado.getString("ID")));
                    Disciplina.setNome(resultado.getString("NOME"));
                    Disciplina.setRequisito(resultado.getString("REQUISITO"));
                    Disciplina.setEmenta(resultado.getString("EMENTA"));
                    Disciplina.setCarga_horaria(resultado.getShort("CARGA_HORARIA"));
                }
            }
            return Disciplina;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
    public ArrayList<Disciplina> listarDisciplinas() throws SQLException {
    ArrayList<Disciplina> disciplinas = new ArrayList<>();
    Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM disciplina ORDER BY nome";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Disciplina disciplina = new Disciplina(resultado.getString("NOME"),
                            resultado.getString("REQUISITO"),
                            resultado.getString("EMENTA"),
                            resultado.getShort("CARGA_HORARIA"));
                    disciplina.setId(Integer.parseInt(resultado.getString("ID")));
                    disciplinas.add(disciplina);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (ListaDeDisciplinas) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return disciplinas;
    }
    /*Criado por Oliver Almeida*/
    public void alterar(Disciplina Disciplina) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE disciplina SET nome = ?, requisito = ?, ementa = ?, carga_horaria = ? WHERE ID = ? ");
            sql.setString(1, Disciplina.getNome());
            sql.setString(2, Disciplina.getRequisito());
            sql.setString(3, Disciplina.getEmenta());
            sql.setShort(4, Disciplina.getCarga_horaria());
            sql.setInt(5, Disciplina.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
    public void excluir(Disciplina Disciplina) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM disciplina WHERE ID = ? ");
            sql.setInt(1, Disciplina.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
} 

