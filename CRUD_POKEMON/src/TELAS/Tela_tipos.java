package TELAS;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class Tela_tipos extends JFrame {

    private JLabel labelTipoAtual;
    private JLabel[] labelFraquezas;
    private JLabel[] labelVantagens;
    private JLabel tituloTipoAtual;
    private JLabel tituloFraquezas;
    private JLabel tituloVantagens;
    
    private static Tela_tipos instanciaAtual = null;
    private static final Map<String, String[]> fraquezasPorTipo = new HashMap<>();
    private static final Map<String, String[]> vantagensPorTipo = new HashMap<>();

    static {
        // Mapeamento de fraquezas
        fraquezasPorTipo.put("Normal", new String[]{"Fighting (Lutador)"});
        fraquezasPorTipo.put("Fire (Fogo)", new String[]{"Water (Água)", "Rock (Pedra)", "Ground (Terra)"});
        fraquezasPorTipo.put("Water (Água)", new String[]{"Electric (Elétrico)", "Grass (Grama)"});
        fraquezasPorTipo.put("Grass (Grama)", new String[]{"Fire (Fogo)", "Ice (Gelo)", "Poison (Veneno)", "Flying (Voador)", "Bug (Inseto)"});
        fraquezasPorTipo.put("Flying (Voador)", new String[]{"Electric (Elétrico)", "Rock (Pedra)", "Ice (Gelo)"});
        fraquezasPorTipo.put("Fighting (Lutador)", new String[]{"Flying (Voador)", "Psychic (Psíquico)", "Fairy (Fada)"});
        fraquezasPorTipo.put("Poison (Veneno)", new String[]{"Ground (Terra)", "Psychic (Psíquico)"});
        fraquezasPorTipo.put("Electric (Elétrico)", new String[]{"Ground (Terra)"});
        fraquezasPorTipo.put("Ground (Terra)", new String[]{"Water (Água)", "Grass (Grama)", "Ice (Gelo)"});
        fraquezasPorTipo.put("Rock (Pedra)", new String[]{"Water (Água)", "Grass (Grama)", "Fighting (Lutador)", "Ground (Terra)", "Steel (Ferro)"});
        fraquezasPorTipo.put("Psychic (Psíquico)", new String[]{"Bug (Inseto)", "Ghost (Fantasma)", "Dark (Sombrio)"});
        fraquezasPorTipo.put("Ice (Gelo)", new String[]{"Fire (Fogo)", "Fighting (Lutador)", "Rock (Pedra)", "Steel (Ferro)"});
        fraquezasPorTipo.put("Bug (Inseto)", new String[]{"Fire (Fogo)", "Flying (Voador)", "Rock (Pedra)"});
        fraquezasPorTipo.put("Ghost (Fantasma)", new String[]{"Ghost (Fantasma)", "Dark (Sombrio)"});
        fraquezasPorTipo.put("Steel (Ferro)", new String[]{"Fire (Fogo)", "Fighting (Lutador)", "Ground (Terra)"});
        fraquezasPorTipo.put("Dragon (Dragão)", new String[]{"Ice (Gelo)", "Dragon (Dragão)", "Fairy (Fada)"});
        fraquezasPorTipo.put("Dark (Sombrio)", new String[]{"Fighting (Lutador)", "Bug (Inseto)", "Fairy (Fada)"});
        fraquezasPorTipo.put("Fairy (Fada)", new String[]{"Poison (Veneno)", "Steel (Ferro)"});

        // Mapeamento de vantagens
        vantagensPorTipo.put("Normal", new String[]{});
        vantagensPorTipo.put("Fire (Fogo)", new String[]{"Grass (Grama)", "Bug (Inseto)", "Ice (Gelo)", "Steel (Ferro)"});
        vantagensPorTipo.put("Water (Água)", new String[]{"Fire (Fogo)", "Ground (Terra)", "Rock (Pedra)"});
        vantagensPorTipo.put("Grass (Grama)", new String[]{"Water (Água)", "Ground (Terra)", "Rock (Pedra)"});
        vantagensPorTipo.put("Flying (Voador)", new String[]{"Grass (Grama)", "Fighting (Lutador)", "Bug (Inseto)"});
        vantagensPorTipo.put("Fighting (Lutador)", new String[]{"Normal", "Rock (Pedra)", "Steel (Ferro)", "Ice (Gelo)", "Dark (Sombrio)"});
        vantagensPorTipo.put("Poison (Veneno)", new String[]{"Grass (Grama)", "Fairy (Fada)"});
        vantagensPorTipo.put("Electric (Elétrico)", new String[]{"Water (Água)", "Flying (Voador)"});
        vantagensPorTipo.put("Ground (Terra)", new String[]{"Fire (Fogo)", "Electric (Elétrico)", "Poison (Veneno)", "Rock (Pedra)", "Steel (Ferro)"});
        vantagensPorTipo.put("Rock (Pedra)", new String[]{"Fire (Fogo)", "Ice (Gelo)", "Flying (Voador)", "Bug (Inseto)"});
        vantagensPorTipo.put("Psychic (Psíquico)", new String[]{"Fighting (Lutador)", "Poison (Veneno)"});
        vantagensPorTipo.put("Ice (Gelo)", new String[]{"Grass (Grama)", "Ground (Terra)", "Flying (Voador)", "Dragon (Dragão)"});
        vantagensPorTipo.put("Bug (Inseto)", new String[]{"Grass (Grama)", "Psychic (Psíquico)", "Dark (Sombrio)"});
        vantagensPorTipo.put("Ghost (Fantasma)", new String[]{"Psychic (Psíquico)", "Ghost (Fantasma)"});
        vantagensPorTipo.put("Steel (Ferro)", new String[]{"Ice (Gelo)", "Rock (Pedra)", "Fairy (Fada)"});
        vantagensPorTipo.put("Dragon (Dragão)", new String[]{"Dragon (Dragão)"});
        vantagensPorTipo.put("Dark (Sombrio)", new String[]{"Psychic (Psíquico)", "Ghost (Fantasma)"});
        vantagensPorTipo.put("Fairy (Fada)", new String[]{"Fighting (Lutador)", "Dragon (Dragão)", "Dark (Sombrio)"});
    }

public Tela_tipos(String tipo) {
        
        if (!tipo.equals("")) {
            if (instanciaAtual != null) {
                instanciaAtual.dispose();
            }
            instanciaAtual = this; 
           
        // Configurar o JFrame
        setTitle("Vantagens e Fraquezas");
        setSize(480, 280);
        setLayout(null);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null); 
        getContentPane().setBackground(Color.WHITE); // Define o fundo branco
        
        ImageIcon icon = new ImageIcon("C:\\Users\\Pichau\\Documents\\NetBeansProjects\\CRUD_POKEMON\\CRUD\\pokebola.png");
        setIconImage(icon.getImage());

        ImageIcon close = new ImageIcon("C:\\Users\\Pichau\\Documents\\NetBeansProjects\\CRUD_POKEMON\\CRUD\\X.png");
        Image image = close.getImage();
        Image newImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon closeIcon = new ImageIcon(newImage);
        
        // Adiciona botão personalizado para fechar a tela
        JButton closeButton = new JButton();
        closeButton.setIcon(closeIcon);
        closeButton.setBounds(420, 12, 50, 50);
        closeButton.setBorderPainted(false);
        closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        closeButton.setFocusPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.addActionListener((ActionEvent e) -> {
            dispose(); // Fecha a janela
        });
        add(closeButton);

        // Adiciona uma borda vermelha
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 472, 270);
        panel.setBorder(BorderFactory.createLineBorder(Color.RED, 5));
        panel.setLayout(null);
        add(panel);

        // Inicializar as labels
        labelTipoAtual = new JLabel();
        labelFraquezas = new JLabel[fraquezasPorTipo.getOrDefault(tipo, new String[]{}).length];
        labelVantagens = new JLabel[vantagensPorTipo.getOrDefault(tipo, new String[]{}).length];

        tituloTipoAtual = new JLabel("Tipo:");
        tituloTipoAtual.setFont(new Font("Monospaced", Font.BOLD, 20));
        tituloTipoAtual.setBounds(20, 10, 150, 30);
        panel.add(tituloTipoAtual);

        labelTipoAtual.setBounds(10, 10, 100, 100);
        panel.add(labelTipoAtual);

        tituloFraquezas = new JLabel("Fraquezas:");
        tituloFraquezas.setFont(new Font("Monospaced", Font.BOLD, 20));
        tituloFraquezas.setHorizontalAlignment(SwingConstants.CENTER);
        tituloFraquezas.setBounds(5, 80, 150, 30);
        panel.add(tituloFraquezas);

        for (int i = 0; i < labelFraquezas.length; i++) {
            labelFraquezas[i] = new JLabel();
            labelFraquezas[i].setBounds(12 + i * 90, 90, 100, 100);
            panel.add(labelFraquezas[i]);
        }

        tituloVantagens = new JLabel("Vantagens:");
        tituloVantagens.setFont(new Font("Monospaced", Font.BOLD, 20));
        tituloVantagens.setHorizontalAlignment(SwingConstants.CENTER);
        tituloVantagens.setBounds(5, 170, 150, 30);
        panel.add(tituloVantagens);

        for (int i = 0; i < labelVantagens.length; i++) {
            labelVantagens[i] = new JLabel();
            labelVantagens[i].setBounds(12 + i * 90, 180, 100, 100);
            panel.add(labelVantagens[i]);
        }

        this.setResizable(false);
        this.setUndecorated(true);
        this.setVisible(true);

        // Exibir o tipo atual, fraquezas e vantagens com base no tipo fornecido
        exibirTipoAtual(tipo);
        exibirFraquezas(tipo);
        exibirVantagens(tipo);
   } else {
            dispose(); // Fecha esta instância se o tipo for uma string vazia
        }
    }

    // Método para exibir o tipo atual
    private void exibirTipoAtual(String tipo) {
        exibirImagemTipo(tipo, labelTipoAtual);
    }

    // Método para exibir as fraquezas com base no tipo fornecido
    public void exibirFraquezas(String tipo) {
        String[] fraquezas = fraquezasPorTipo.getOrDefault(tipo, new String[]{});
        for (int i = 0; i < fraquezas.length; i++) {
            exibirImagemTipo(fraquezas[i], labelFraquezas[i]);
        }
    }

    // Método para exibir as vantagens com base no tipo fornecido
    public void exibirVantagens(String tipo) {
        String[] vantagens = vantagensPorTipo.getOrDefault(tipo, new String[]{});
        for (int i = 0; i < vantagens.length; i++) {
            exibirImagemTipo(vantagens[i], labelVantagens[i]);
        }
    }

    // Método para exibir a imagem correspondente ao tipo
    private void exibirImagemTipo(String tipo, JLabel label) {
        // Caminho para a imagem do tipo
        String caminho = "C:\\Users\\Pichau\\Documents\\NetBeansProjects\\CRUD_POKEMON\\CRUD\\Tipagem_Pokemons\\" + tipo + ".png";
        // Carregar a imagem e configurar a JLabel
        ImageIcon imagemIcon = new ImageIcon(caminho);
        Image imagem = imagemIcon.getImage().getScaledInstance(80, 30, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(imagem));
    }
}