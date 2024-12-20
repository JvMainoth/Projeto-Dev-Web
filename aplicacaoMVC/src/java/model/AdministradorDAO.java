package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidade.Administrador;

/*
-- Estrutura da tabela `Administradors`

CREATE TABLE IF NOT EXISTS `Administrador` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(40) NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `senha` varchar(8) NOT NULL,
  `endereco` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

 */
public class AdministradorDAO {

    public void Inserir(Administrador Administrador) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO Administrador (nome, cpf, endereco, senha)"
                    + " VALUES (?,?,?,?)");
            sql.setString(1, Administrador.getNome());
            sql.setString(2, Administrador.getCpf());
            sql.setString(3, Administrador.getEndereco());
            sql.setString(4, Administrador.getSenha());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }

    public Administrador getAdministrador(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            Administrador Administrador = new Administrador();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM Administrador WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Administrador.setId(Integer.parseInt(resultado.getString("ID")));
                    Administrador.setNome(resultado.getString("NOME"));
                    Administrador.setCpf(resultado.getString("CPF"));
                    Administrador.setEndereco(resultado.getString("ENDERECO"));
                    Administrador.setSenha(resultado.getString("SENHA"));
                }
            }
            return Administrador;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Alterar(Administrador Administrador) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE Administrador SET nome = ?, cpf = ?, endereco = ?, senha = ?  WHERE ID = ? ");
            sql.setString(1, Administrador.getNome());
            sql.setString(2, Administrador.getCpf());
            sql.setString(3, Administrador.getEndereco());
            sql.setString(4, Administrador.getSenha());
            sql.setInt(5, Administrador.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Excluir(Administrador Administrador) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM Administrador WHERE ID = ? ");
            sql.setInt(1, Administrador.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public ArrayList<Administrador> ListaDeAdministrador() {
        ArrayList<Administrador> meusAdministradores = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM Administrador order by nome";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Administrador Administrador = new Administrador(resultado.getString("NOME"),
                            resultado.getString("CPF"),
                            resultado.getString("ENDERECO"),
                            resultado.getString("SENHA"));
                    Administrador.setId(Integer.parseInt(resultado.getString("id")));
                    meusAdministradores.add(Administrador);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (ListaDeAdministradores) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return meusAdministradores;
    }

    public Administrador Logar(Administrador Administrador) throws Exception {
        Conexao conexao = new Conexao();
        try {
            //editado para pegar a aprovação - Funcionou
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM Administrador WHERE cpf=? and senha =? and aprovado = 's' LIMIT 1");
            sql.setString(1, Administrador.getCpf());
            sql.setString(2, Administrador.getSenha());
            ResultSet resultado = sql.executeQuery();
            Administrador AdministradorObtido = new Administrador();
            if (resultado != null) {
                while (resultado.next()) {
                    AdministradorObtido.setId(Integer.parseInt(resultado.getString("ID")));
                    AdministradorObtido.setNome(resultado.getString("NOME"));
                    AdministradorObtido.setCpf(resultado.getString("CPF"));
                    AdministradorObtido.setEndereco(resultado.getString("ENDERECO"));
                    AdministradorObtido.setSenha(resultado.getString("SENHA"));
                }
            }
            return AdministradorObtido;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
    /*Criado por Oliver */
    public void criarAdminDefault() throws Exception {
        Conexao conexao = new Conexao();
        try {
            // Verifica se o adm já existe no sistema
            String queryCheck = "SELECT COUNT(*) AS total FROM administrador WHERE cpf = ?";
            PreparedStatement checkStmt = conexao.getConexao().prepareStatement(queryCheck);
            checkStmt.setString(1, "249.252.810-38");
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            System.out.println("Conectado ao banco: " + conexao.getConexao().getCatalog());
            System.out.println(rs.getInt("total"));
            
            if (rs.getInt("total") == 0) {
                // Insere o adm no bd
                System.out.println("Inserindo administrador padrão...");
                String queryInsert = "INSERT INTO administrador (nome, cpf, senha, aprovado, endereco) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement insertStmt = conexao.getConexao().prepareStatement(queryInsert);
                insertStmt.setString(1, "Adm padrão");
                insertStmt.setString(2, "249.252.810-38");
                insertStmt.setString(3, "111");
                insertStmt.setString(4, "s");
                insertStmt.setString(5, "Rua");
                System.out.println("Administrador cadastrado.");
                int rowsAffected = insertStmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Administrador cadastrado com sucesso.");
                } else {
                    System.err.println("Nenhuma linha foi inserida.");
                }
            } else {
            System.out.println("Administrador já cadastrado no sistema.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao criar administrador padrão: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Erro ao executar consulta SQL.");
        } finally {
            conexao.closeConexao();
        }
    }
}
