/*Criado por Oliver Almeida*/
package model;

import entidade.Turma;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TurmaDAO {
    public void inserir(Turma Turma) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement stmt = conexao.getConexao().prepareStatement(
                "INSERT INTO turmas (codigo_turma, professor_id, disciplina_id, aluno_id, nota)" + "VALUES (?, ?, ?, ?, ?)"
            );
            stmt.setString(1, Turma.getCodigo());
            stmt.setInt(2, Turma.getProfessor_id());
            stmt.setInt(3, Turma.getDisciplina_id());
            stmt.setInt(4, Turma.getAluno_id());
            stmt.setInt(5, Turma.getNota());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }
}
