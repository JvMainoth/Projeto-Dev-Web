package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    /* Banco de dados: `dbjava` */
    private Connection conexao;

    public Conexao() {
                
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //load driver  
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/escola", "root", "123456");
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            throw new RuntimeException("Nao foi possivel efetuar uma conexao com o BD!"+ e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Driver do banco de dados não encontrado: " + e.getMessage());
            throw new RuntimeException("Nao foi possivel encontrar a classe referente! Verifique o driver!"+ e.getMessage());
        }
    }

    public Connection getConexao() {
        return this.conexao;
    }

    public void closeConexao() {
        try {
            this.conexao.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
