/*Criado por Oliver Almeida*/
package entidade;

public class Disciplina {
    private int id;
    private String nome;
    private String requisito;
    private String ementa;
    private Short carga_horaria;

    public Disciplina(String nome, String requisito, String ementa, Short carga_horaria) {
        this.nome = nome;
        this.requisito = requisito;
        this.ementa = ementa;
        this.carga_horaria = carga_horaria;
    }
    
    public Disciplina() {
        this.id = 0;
        this.nome = "";
        this.requisito = "";
        this.ementa = "";
        this.carga_horaria = 0;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRequisito() {
        return requisito;
    }

    public void setRequisito(String requisito) {
        this.requisito = requisito;
    }

    public String getEmenta() {
        return ementa;
    }

    public void setEmenta(String ementa) {
        this.ementa = ementa;
    }

    public Short getCarga_horaria() {
        return carga_horaria;
    }

    public void setCarga_horaria(Short carga_horaria) {
        this.carga_horaria = carga_horaria;
    }
}
