package IMPLEMENTACOES;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Metodos_CRUD {
    private final Conexao_BD conexao;

    public Metodos_CRUD() {
        conexao = new Conexao_BD();
        conexao.conectar();
    }
    
    public void adicionarPokemon(String nome, double peso, double altura, String tipo1, String tipo2, String descricao, byte[] imagem) {
     String sql = "INSERT INTO Pokemon (Nome_pokemon, Peso, Altura, Tipo1, Tipo2, Descricao, imagem) VALUES (?, ?, ?, ?, ?, ?, ?)";
     try (Connection conn = conexao.getConnection();
          PreparedStatement pstmt = conn.prepareStatement(sql)) {
         pstmt.setString(1, nome);
         pstmt.setDouble(2, peso);
         pstmt.setDouble(3, altura);
         pstmt.setString(4, tipo1);
         pstmt.setString(5, tipo2);
         pstmt.setString(6, descricao);
         pstmt.setBytes(7, imagem);
         pstmt.executeUpdate();
     } catch (SQLException e) {
         e.printStackTrace();
     }
 }
            public void adicionarImagemPokemon(String nome, byte[] imagem) {
          String sql = "UPDATE Pokemon SET imagem = ? WHERE Nome_pokemon = ?";
          try (Connection conn = conexao.getConnection();
               PreparedStatement pstmt = conn.prepareStatement(sql)) {
              pstmt.setBytes(1, imagem);
              pstmt.setString(2, nome);
              pstmt.executeUpdate();
          } catch (SQLException e) {
              e.printStackTrace();
          }
      }

    // Método para editar um Pokémon existente
    public void editarPokemon(String nomeAntigo, String nomeNovo, double peso, double altura, String tipo1, String tipo2, String descricao, byte[] imagem) {
        try {
            String sql = "UPDATE pokemon SET Nome_pokemon=?, Peso=?, Altura=?, Tipo1=?, Tipo2=?, Descricao =?, Imagem=? WHERE Nome_pokemon=?";
            PreparedStatement stmt = conexao.getConnection().prepareStatement(sql);
            stmt.setString(1, nomeNovo);
            stmt.setDouble(2, peso);
            stmt.setDouble(3, altura);
            stmt.setString(4, tipo1);
            stmt.setString(5, tipo2);
            stmt.setString(6, descricao);
            stmt.setBytes(7, imagem);
            stmt.setString(8, nomeAntigo);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Método para deletar um Pokémon
    public void deletarPokemon(String nome) {
        try {
            String sql = "DELETE FROM pokemon WHERE Nome_pokemon=?";
            PreparedStatement stmt = conexao.getConnection().prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Método para exibir os detalhes de um Pokémon
    public void exibirDetalhesPokemon(String nomePokemon) {
        try {
            String query = "SELECT * FROM pokemon WHERE Nome_pokemon=?";
            PreparedStatement stmt = conexao.getConnection().prepareStatement(query);
            stmt.setString(1, nomePokemon);
            ResultSet rs = stmt.executeQuery();

            // Exibir os detalhes do Pokémon nos campos de texto
            if (rs.next()) {
                System.out.println("Nome: " + rs.getString("Nome_pokemon"));
                System.out.println("Peso: " + rs.getDouble("Peso"));
                System.out.println("Altura: " + rs.getDouble("Altura"));
                System.out.println("Tipo1: " + rs.getString("Tipo1"));
                System.out.println("Tipo2: " + rs.getString("Tipo2"));
                // Exibir a imagem
                byte[] imgBytes = rs.getBytes("Imagem");
                Exibir_IMG showExibir_IMG = new Exibir_IMG(imgBytes);
                showExibir_IMG.exibirImagemRedimensionada();
            }

            // Fechar os recursos
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
