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
}
