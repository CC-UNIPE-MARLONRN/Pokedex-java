package TELAS;

import IMPLEMENTACOES.Conexao_BD;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Tela_evolucoes extends JFrame {
    private JPanel Tela_evo;
    private JLabel labelTitulo;
    private int largura = 0;
    private int altura = 0;
    private final Conexao_BD conexao;
    private int x;
    private int y;
    private int id;

    public Tela_evolucoes() {
        super("Tela de evoluções");
        int qtd_evo = 10; //Alterar quando for necessário
        
        conexao = new Conexao_BD();
        conexao.conectar();
                
        Tela_evo = new JPanel();
        this.add(Tela_evo);
        
        id = obterMenorID();
        
        int posicao_i_x = 15;
        int posicao_i_y = 15;
        int largura_poke = 100;
        int altura_poke = 100;
        int margem = 15;
        
        for (int id_atual = id; id_atual <= obterMaiorID(); id_atual++) {
            if (verificaEvolucaoAnterior(id_atual)) {
                JPanel poke = new JPanel();
                poke.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                poke.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                poke.setBounds(posicao_i_x, posicao_i_y, largura_poke, altura_poke);
                
                Tela_evo.add(poke);

                posicao_i_x += largura_poke + margem;
                if (posicao_i_x + largura_poke > largura) {
                    posicao_i_x = 15;
                    posicao_i_y += altura_poke + margem;
                }
            } else {
                System.out.printf("O pokemon de id %d não tem evolução anterior!\n", id_atual);
            }
        }
        
        largura = 400;
        altura = 400;
        
        while (qtd_evo > 8) {
            largura += 200;
            altura += 50;
            qtd_evo -= 8;
        }
        
        
        labelTitulo = new JLabel("Tela de Evoluções");
        labelTitulo.setFont(new Font("Monospaced", Font.BOLD, 20)); 
        labelTitulo.setBounds(x, y, largura, altura); 

        Tela_evo.add(labelTitulo);

        this.setSize(largura, altura);
        this.setVisible(true); 
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setResizable(false);   
        Tela_evo.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        
        this.getContentPane().setBackground(Color.WHITE);
        this.setLayout(null);
    }

    private boolean verificaEvolucaoAnterior(int id) {
    String query = "SELECT COUNT(*) FROM pokemon WHERE Evolucao_anterior IS NOT NULL AND ID_pokemon = ?";
    try (PreparedStatement stmt = conexao.getConnection().prepareStatement(query)) {
        stmt.setInt(1, id);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return false;
}
     private int obterMenorID() {
            try {
                String query = "SELECT MIN(id_pokemon) AS menor_id FROM pokemon";
                PreparedStatement stmt = conexao.getConnection().prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return rs.getInt("menor_id");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return 1;
        }
    
        private int obterMaiorID() {
        try {
            String query = "SELECT MAX(id_pokemon) AS maior_id FROM pokemon";
            PreparedStatement stmt = conexao.getConnection().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("maior_id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Integer.MAX_VALUE; // Valor máximo para indicar que não há limite superior
    }
        
//     private void exibirImagem(byte[] imgBytes) throws IOException {
//        if (labelImagem != null) {
//            tela.remove(labelImagem);
//        }
//
//        ImageIcon icon = new ImageIcon(imgBytes);
//        Image image = icon.getImage();
//        Image newImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
//        ImageIcon newIcon = new ImageIcon(newImage);
//
//        labelImagem = new JLabel(newIcon);
//        labelImagem.setBounds(220, 260, 200, 200);
//        tela.add(labelImagem);
//        tela.repaint();
//         // Adicione a imagem ao JPanel
//    if (panelImagem != null) {
//        panelImagem.removeAll(); // Limpe qualquer imagem anterior
//        panelImagem.add(labelImagem); // Adicione a nova imagem ao JPanel
//        panelImagem.repaint(); // Repinte o JPanel para exibir a nova imagem
//    }
//}    
         
    
//    public static void main(String[] args) {
//        Tela_evolucoes tela = new Tela_evolucoes();
//
//    }
}
