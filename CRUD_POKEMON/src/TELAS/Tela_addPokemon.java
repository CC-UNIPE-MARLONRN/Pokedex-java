package TELAS;

import IMPLEMENTACOES.Capitura_IMG;
import IMPLEMENTACOES.Metodos_CRUD;
import IMPLEMENTACOES.Conexao_BD;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.font.TextAttribute;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Tela_addPokemon extends JFrame {
    private final JPanel painel;
    private final JLabel Logo;
    private final JLabel fundo_branco;
    private final JLabel Nome;
    private final JLabel Peso;
    private final JLabel Altura;
    private final JLabel Tipo1;
    private final JLabel Tipo2;
    private final JLabel foto;
    private final JLabel descricao;
    private final JTextField txtNome;
    private final JTextField txtPeso;
    private final JTextField txtAltura;
    private final JTextArea txtDescricao;
    private final JComboBox<String> comboTipo1;
    private final JComboBox<String> comboTipo2;
    private final JButton btnAdicionar;
    private final JButton btnExibirDescricao;
    private final JButton btnExibirDados;
    private JButton add_foto;
    private final JScrollPane scrollPanetxtDescricao;
    private final Conexao_BD conexao;

    private final Metodos_CRUD metodosCrud;
    private byte[] imagemSelecionada; // Variável para armazenar o arquivo de imagem selecionado
    
    public Tela_addPokemon() {
        super("Registrar Pokemon");
        conexao = new Conexao_BD();
        conexao.conectar();

        metodosCrud = new Metodos_CRUD();

        ImageIcon icon = new ImageIcon("C:\\Users\\Pichau\\Documents\\NetBeansProjects\\CRUD_POKEMON\\CRUD\\pokebola.png");
        setIconImage(icon.getImage());
        
        painel = new JPanel();
        this.add(painel);
        painel.setLayout(null);
        this.setSize(600, 400);
        painel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(Color.LIGHT_GRAY, 2),
                        new LineBorder(Color.RED, 6)
                ),
                new LineBorder(Color.lightGray, 4)
        ));
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        ImageIcon wall = new ImageIcon("C:\\Users\\Pichau\\Documents\\NetBeansProjects\\CRUD_POKEMON\\CRUD\\wallpaper_poke.jpg");
        Image wallpaper = wall.getImage();
        Image newwallpaper = wallpaper.getScaledInstance(565, 350, Image.SCALE_SMOOTH);
        ImageIcon newwall = new ImageIcon(newwallpaper);

        JLabel wallpaper_poke = new JLabel();
        wallpaper_poke.setIcon(newwall);
        wallpaper_poke.setBounds(10, 10, 572, 341);

        Logo = new JLabel("Registro de Pokemon");
        Logo.setBounds(160, 30, 250, 50);
        Logo.setFont(new Font("Monospaced", Font.BOLD, 20));
        Logo.setHorizontalAlignment(SwingConstants.CENTER);
        Logo.setForeground(Color.BLACK);
        Logo.setOpaque(false);

        fundo_branco = new JLabel();
        fundo_branco.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(Color.white, 1),
                        new LineBorder(Color.LIGHT_GRAY, 3)
                ),
                new LineBorder(Color.white, 1)
        ));
        fundo_branco.setBounds(274, 120, 300, 230);
        fundo_branco.setBackground(new Color(255, 255, 255, 180));
        fundo_branco.setOpaque(true);

        Nome = new JLabel("Nome:");
        Nome.setBounds(195, 120, 250, 50);
        Nome.setFont(new Font("Monospaced", Font.BOLD, 16));
        Nome.setHorizontalAlignment(SwingConstants.CENTER);
        Nome.setForeground(Color.BLACK);
        Nome.setOpaque(false);
        painel.add(Nome);

        txtNome = new JTextField();
        txtNome.setBounds(350, 137, 200, 20);
        txtNome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        txtNome.setFont(new Font("Monospaced", Font.BOLD, 12));
        txtNome.setForeground(Color.BLACK);
        txtNome.setOpaque(true);
        painel.add(txtNome);

        Peso = new JLabel("Peso:");
        Peso.setBounds(195, 150, 250, 50);
        Peso.setFont(new Font("Monospaced", Font.BOLD, 16));
        Peso.setHorizontalAlignment(SwingConstants.CENTER);
        Peso.setForeground(Color.BLACK);
        Peso.setOpaque(false);
        painel.add(Peso);

        txtPeso = new JTextField();
        txtPeso.setBounds(350, 167, 200, 20);
        txtPeso.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        txtPeso.setFont(new Font("Monospaced", Font.BOLD, 12));
        txtPeso.setForeground(Color.BLACK);
        txtPeso.setOpaque(true);
        painel.add(txtPeso);

        Altura = new JLabel("Altura:");
        Altura.setBounds(194, 180, 250, 50);
        Altura.setFont(new Font("Monospaced", Font.BOLD, 14));
        Altura.setHorizontalAlignment(SwingConstants.CENTER);
        Altura.setForeground(Color.BLACK);
        Altura.setOpaque(false);
        painel.add(Altura);

        txtAltura = new JTextField();
        txtAltura.setBounds(350, 197, 200, 20);
        txtAltura.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        txtAltura.setFont(new Font("Monospaced", Font.BOLD, 12));
        txtAltura.setForeground(Color.BLACK);
        txtAltura.setOpaque(true);
        painel.add(txtAltura);

        Tipo1 = new JLabel("Tipo 1:");
        Tipo1.setBounds(195, 210, 250, 50);
        Tipo1.setFont(new Font("Monospaced", Font.BOLD, 14));
        Tipo1.setHorizontalAlignment(SwingConstants.CENTER);
        Tipo1.setForeground(Color.BLACK);
        Tipo1.setOpaque(false);
        painel.add(Tipo1);

        String[] tiposPokemon = {"","Normal", "Fire (Fogo)", "Water (Água)", "Grass (Grama)", "Flying (Voador)",
                "Fighting (Lutador)", "Poison (Veneno)", "Electric (Elétrico)", "Ground (Terra)", "Rock (Pedra)",
                "Psychic (Psíquico)", "Ice (Gelo)", "Bug (Inseto)", "Ghost (Fantasma)", "Steel (Ferro)",
                "Dragon (Dragão)", "Dark (Sombrio)", "Fairy (Fada)"};
        comboTipo1 = new JComboBox<>(tiposPokemon);
        comboTipo1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        comboTipo1.setBounds(350, 227, 200, 20);
        comboTipo1.setBackground(Color.WHITE);
        painel.add(comboTipo1);

        Tipo2 = new JLabel("Tipo 2:");
        Tipo2.setBounds(195, 240, 250, 50);
        Tipo2.setFont(new Font("Monospaced", Font.BOLD, 14));
        Tipo2.setHorizontalAlignment(SwingConstants.CENTER);
        Tipo2.setForeground(Color.BLACK);
        Tipo2.setOpaque(false);
        painel.add(Tipo2);

        comboTipo2 = new JComboBox<>(tiposPokemon);
        comboTipo2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        comboTipo2.setBounds(350, 257, 200, 20);
        comboTipo2.setBackground(Color.WHITE);
        painel.add(comboTipo2);

        foto = new JLabel("Adicionar Imagem");
        foto.setFont(new Font("", Font.BOLD, 15));
        foto.setForeground(Color.BLACK);
        foto.setHorizontalAlignment(SwingConstants.CENTER);
        foto.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(Color.white, 1),
                        new LineBorder(Color.LIGHT_GRAY, 3)
                ),
                new LineBorder(Color.white, 1)
        ));
        foto.setBounds(60, 125, 160, 30);
        foto.setOpaque(true);
        painel.add(foto);

        ImageIcon foto_desconhecida = new ImageIcon("C:\\Users\\Pichau\\Documents\\NetBeansProjects\\CRUD_POKEMON\\CRUD\\quem.jpg");
        Image img = foto_desconhecida.getImage();
        Image newImg = img.getScaledInstance(150, 180, Image.SCALE_SMOOTH);
        ImageIcon newfoto_desconhecida = new ImageIcon(newImg);

        add_foto = new JButton();
        add_foto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add_foto.setIcon(newfoto_desconhecida);
        add_foto.setBounds(60, 155, 160, 190);
        painel.add(add_foto);

        // Adiciona o action listener para o botão de adicionar foto
        add_foto.addActionListener(e -> {
            File imagem = Capitura_IMG.capturarImagem();
            if (imagem != null) {
                try {
                    // Converte a imagem para um array de bytes
                    byte[] imagemBytes = Files.readAllBytes(imagem.toPath());
                    // Atualiza a imagem selecionada e exibe-a no botão
                    imagemSelecionada = imagemBytes;
                    ImageIcon imgIcon = new ImageIcon(new ImageIcon(imagem.getAbsolutePath()).getImage().getScaledInstance(150, 180, Image.SCALE_SMOOTH));
                    add_foto.setIcon(imgIcon);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        descricao = new JLabel("Descrição: ");
        descricao.setBounds(220, 125, 250, 50);
        descricao.setFont(new Font("Monospaced", Font.BOLD, 14));
        descricao.setHorizontalAlignment(SwingConstants.CENTER);
        descricao.setForeground(Color.BLACK);
        descricao.setOpaque(false);
        descricao.setVisible(false);
        painel.add(descricao);
        
        txtDescricao = new JTextArea();
        txtDescricao.setBounds(300, 165, 250, 130);
        txtDescricao.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        txtDescricao.setFont(new Font("Monospaced", Font.BOLD, 11));
        txtDescricao.setLineWrap(true);
        txtDescricao.setWrapStyleWord(true);
        txtDescricao.setForeground(Color.BLACK);
        txtDescricao.setVisible(false);
        
        scrollPanetxtDescricao = new JScrollPane(txtDescricao);
        scrollPanetxtDescricao.setOpaque(false);
        scrollPanetxtDescricao.setAlignmentX(TextAttribute.JUSTIFICATION_FULL);
        scrollPanetxtDescricao.setFont(new Font("Monospaced", Font.PLAIN, 12));
        scrollPanetxtDescricao.setForeground(Color.WHITE);
        scrollPanetxtDescricao.getViewport().setOpaque(false); 
        scrollPanetxtDescricao.setBorder(null);
        scrollPanetxtDescricao.setBackground(new Color(0, 0, 0, 0));
        scrollPanetxtDescricao.setBounds(300, 165, 250, 130);
        scrollPanetxtDescricao.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
   
        painel.add(scrollPanetxtDescricao);
        
        btnExibirDescricao = new JButton("→");
        btnExibirDescricao.setFont(new Font("Monospaced", Font.BOLD, 28));
        btnExibirDescricao.setBorderPainted(false);
        btnExibirDescricao.setContentAreaFilled(false); 
        btnExibirDescricao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnExibirDescricao.addActionListener(e -> exibirDescricao());
        btnExibirDescricao.setBackground(Color.WHITE);
        btnExibirDescricao.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        btnExibirDescricao.setBounds(228, 220, 60, 30);
        painel.add(btnExibirDescricao);
        
        btnExibirDados = new JButton("←");
        btnExibirDados.setFont(new Font("Monospaced", Font.BOLD, 28));
        btnExibirDados.setBorderPainted(false);
        btnExibirDados.setContentAreaFilled(false); 
        btnExibirDados.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnExibirDados.addActionListener(e -> exibirDados());
        btnExibirDados.setBackground(Color.WHITE);
        btnExibirDados.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        btnExibirDados.setBounds(228, 220, 60, 30);
        btnExibirDados.setVisible(false);
        painel.add(btnExibirDados);
        
       btnAdicionar = new JButton("Adicionar");
       btnAdicionar.addActionListener(e -> {
            try {
                adicionarPokemon();
            } catch (ParseException ex) {
                Logger.getLogger(Tela_addPokemon.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnAdicionar.setFont(new Font("Monospaced", Font.BOLD, 14));
        btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnAdicionar.setBackground(Color.WHITE);
        btnAdicionar.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        btnAdicionar.setBounds(350, 300, 200, 30);
        painel.add(btnAdicionar);

        painel.add(Logo);
        painel.add(fundo_branco);
        painel.add(wallpaper_poke);

        this.setVisible(true);
    }
    
        private void exibirDescricao() {
        // Torna os campos de nome, peso, tipo1 e tipo2 invisíveis
        Nome.setVisible(false);
        txtNome.setVisible(false);
        Peso.setVisible(false);
        txtPeso.setVisible(false);
        Altura.setVisible(false);
        txtAltura.setVisible(false);
        Tipo1.setVisible(false);
        comboTipo1.setVisible(false);
        Tipo2.setVisible(false);
        comboTipo2.setVisible(false);
        btnExibirDescricao.setVisible(false);
        descricao.setVisible(true);
        txtDescricao.setVisible(true);
        btnExibirDados.setVisible(true);
    }
        
    private void exibirDados() {
        // Torna os campos de nome, peso, tipo1 e tipo2 visiveis
        Nome.setVisible(true);
        txtNome.setVisible(true);
        Peso.setVisible(true);
        txtPeso.setVisible(true);
        Altura.setVisible(true);
        txtAltura.setVisible(true);
        Tipo1.setVisible(true);
        comboTipo1.setVisible(true);
        Tipo2.setVisible(true);
        comboTipo2.setVisible(true);
        btnExibirDescricao .setVisible(true);
        descricao.setVisible(false);
        txtDescricao.setVisible(false);
        btnExibirDados.setVisible(false);
    }

        private void adicionarPokemon() throws ParseException {
        try {
            String nome = txtNome.getText();
            String pesoStr = txtPeso.getText();
            String alturaStr = txtAltura.getText();
            String tipo1 = (String) comboTipo1.getSelectedItem();
            String tipo2 = null; //

            if (!comboTipo2.getSelectedItem().equals("")) { 
                tipo2 = (String) comboTipo2.getSelectedItem();
            }
            
            ImageIcon pokebola = new ImageIcon("C:\\Users\\Pichau\\Documents\\NetBeansProjects\\CRUD_POKEMON\\CRUD\\pokebola.png");
            Image image = pokebola.getImage();
            Image newImage = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            ImageIcon newpokebola = new ImageIcon(newImage);
     
            
            String descricao = txtDescricao.getText();

            // Verifica se algum campo obrigatório está vazio
            if (nome.isEmpty() || pesoStr.isEmpty() || alturaStr.isEmpty() || tipo1.isEmpty() || imagemSelecionada == null) {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos obrigatórios e adicione uma imagem.", "Erro",  JOptionPane.ERROR_MESSAGE, newpokebola);
                return; // Retorna sem adicionar o Pokémon se algum campo estiver vazio
            }

            // Usando Locale para o Brasil
            Locale localeBrasil = new Locale("pt", "BR");
            NumberFormat formatador = NumberFormat.getInstance(localeBrasil);

            // Formatando peso e altura usando o Locale do Brasil
            double peso = formatador.parse(pesoStr).doubleValue();
            double altura = formatador.parse(alturaStr).doubleValue();
            
            //Verifica se o usuario colocou a descrição ou não e pergunta se deseja
            if(descricao.isEmpty()){
             Object[] options = {"Sim", "Não"};
             int confirmacao = JOptionPane.showOptionDialog(this, "Tem que não quer adicionar descrição? \n É só clicar na seta para adicionar!", "Lembrar descrição", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, newpokebola, options, options[0]);
            if (confirmacao == JOptionPane.YES_OPTION) {
                return;
            } else if (confirmacao == JOptionPane.NO_OPTION) {
                // Adiciona o Pokémon ao banco de dados
                metodosCrud.adicionarPokemon(nome, peso, altura, tipo1, tipo2, descricao, imagemSelecionada);
                limparCampos();
                JOptionPane.showMessageDialog(this, "Pokémon adicionado com sucesso!", "Aviso", JOptionPane.INFORMATION_MESSAGE, newpokebola);
            }
            }
            else{
                // Adiciona o Pokémon ao banco de dados
                metodosCrud.adicionarPokemon(nome, peso, altura, tipo1, tipo2, descricao, imagemSelecionada);
                JOptionPane.showMessageDialog(this, "Pokémon adicionado com sucesso!", "Aviso", JOptionPane.INFORMATION_MESSAGE, newpokebola);
                limparCampos();
            }
 
        } catch (NumberFormatException | ParseException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar Pokémon: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void limparCampos() {
        txtNome.setText("");
        txtPeso.setText("");
        txtAltura.setText("");
        comboTipo1.setSelectedIndex(0);
        comboTipo2.setSelectedIndex(0);
        txtDescricao.setText("");
        exibirDados();
        // Reseta a imagem para a imagem desconhecida
        ImageIcon foto_desconhecida = new ImageIcon("C:\\Users\\Pichau\\Documents\\NetBeansProjects\\CRUD_POKEMON\\CRUD\\quem.jpg");
        Image img = foto_desconhecida.getImage();
        Image newImg = img.getScaledInstance(150, 180, Image.SCALE_SMOOTH);
        ImageIcon newfoto_desconhecida = new ImageIcon(newImg);
        add_foto.setIcon(newfoto_desconhecida);
        imagemSelecionada = null;
    }
}