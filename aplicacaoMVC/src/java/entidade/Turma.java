/*Criado por Oliver Almeida*/
package entidade;

public class Turma {
    private int id;
    private String codigo;
    private int professor_id;
    private int disciplina_id;
    private int aluno_id;
    private int nota;

    public Turma(String codigo, int professor_id, int disciplina_id, int aluno_id, int nota) {
        this.codigo = codigo;
        this.professor_id = professor_id;
        this.disciplina_id = disciplina_id;
        this.aluno_id = aluno_id;
        this.nota = nota;
    }
    
    public Turma() {
        this.id = 0;
        this.codigo = "";
        this.professor_id = 0;
        this.disciplina_id = 0;
        this.aluno_id = 0;
        this.nota = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getProfessor_id() {
        return professor_id;
    }

    public void setProfessor_id(int professor_id) {
        this.professor_id = professor_id;
    }

    public int getDisciplina_id() {
        return disciplina_id;
    }

    public void setDisciplina_id(int disciplina_id) {
        this.disciplina_id = disciplina_id;
    }

    public int getAluno_id() {
        return aluno_id;
    }

    public void setAluno_id(int aluno_id) {
        this.aluno_id = aluno_id;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
    
}
