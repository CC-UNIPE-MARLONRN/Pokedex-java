package IMPLEMENTACOES;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao_BD {
    private String servidor;
    private String banco;
    private String usuario;
    private Connection conexao;
    
    public Conexao_BD(){
        this.servidor = "localhost";
        this.banco = "pokedex";
        this.usuario = "root";
    }
    
    public boolean conectar(){
        try{
            this.conexao = DriverManager.getConnection("jdbc:mysql://" + this.servidor + "/" + this.banco, this.usuario, "");
            return true;
        }
        catch(SQLException ex){
            throw new RuntimeException(ex);
        }
    }
    
    public Connection getConnection(){
        return conexao;
    }
}
