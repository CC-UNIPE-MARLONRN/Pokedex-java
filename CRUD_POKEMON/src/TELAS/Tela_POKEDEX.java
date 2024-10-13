package TELAS;

import IMPLEMENTACOES.Conexao_BD;
import IMPLEMENTACOES.Metodos_CRUD;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.TextAttribute;
import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Tela_POKEDEX extends JFrame {

    private final JPanel tela;
    private final JLabel labelNome;
    private final JLabel labelPeso;
    private final JLabel labelAltura;
    private final JLabel labelTipo1;
    private final JLabel labelTipo2;
    private final JButton btnAvancar;
    private final JButton btnRetroceder;
    private final JButton btnAdicionar;
    private final JButton btnEvoluir;
    private final JButton btnRegredir;
    private final JButton btnDeletar;
    private final JButton btnEditar;
    private final JButton btnEvolucao;
    private final JButton btnbuscar;
    private JButton btnTutorial;
    private final JButton btn_creditos;
    private JLabel labelImagem;
    private final JPanel panelImagem;
    private final ImageIcon pokedex;
    private final ImageIcon fundo_pokedex;
    private final Conexao_BD conexao;
    private final JTextArea labelDescricao;
    private final JScrollPane scrollPaneDescricao;
    private int idAtual;
    private Tela_tipos instanciaTelaTipos = null;
    
    private final Metodos_CRUD metodosCrud;
    
    public Tela_POKEDEX() {
        super("POKEDEX - JAVA");

        conexao = new Conexao_BD();
        conexao.conectar();
        
        metodosCrud = new Metodos_CRUD();
        
        ImageIcon icon = new ImageIcon("C:\\Users\\Pichau\\Documents\\NetBeansProjects\\CRUD_POKEMON\\CRUD\\pokebola.png");
        setIconImage(icon.getImage());
        
        tela = new JPanel();
        this.add(tela);
        this.setSize(1146, 872);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setLayout(null);

        labelNome = new JLabel();
        labelNome.setFont(new Font("Monospaced", Font.BOLD, 16));
        labelNome.setHorizontalAlignment(SwingConstants.CENTER);
        labelNome.setForeground(Color.WHITE);
        labelNome.setBounds(140, 675, 200, 30);
        tela.add(labelNome);

        labelPeso = new JLabel();
        labelPeso.setFont(new Font("Monospaced", Font.BOLD, 14));
        labelPeso.setHorizontalAlignment(SwingConstants.CENTER);
        labelPeso.setForeground(Color.WHITE);
        labelPeso.setBounds(635, 280, 200, 30);
        tela.add(labelPeso);

        labelAltura = new JLabel();
        labelAltura.setFont(new Font("Monospaced", Font.BOLD, 14));
        labelAltura.setHorizontalAlignment(SwingConstants.CENTER);
        labelAltura.setForeground(Color.WHITE);
        labelAltura.setBounds(830, 280, 200, 30);
        tela.add(labelAltura);

        labelTipo1 = new JLabel();
        labelTipo1.setBounds(674, 585, 67, 20);
        tela.add(labelTipo1);

        labelTipo2 = new JLabel();
        labelTipo2.setBounds(744, 585, 67, 20);
        tela.add(labelTipo2);
      
        // Configuração da JTextArea
        labelDescricao = new JTextArea();
        labelDescricao.setForeground(Color.WHITE);
        labelDescricao.setAlignmentX(TextAttribute.JUSTIFICATION_FULL);
        labelDescricao.setFont(new Font("Monospaced", Font.PLAIN, 12));
        labelDescricao.setLineWrap(true);
        labelDescricao.setOpaque(false); 
        labelDescricao.setWrapStyleWord(true);
        labelDescricao.setBackground(new Color(0, 0, 0, 0)); 
        labelDescricao.setEditable(false);
        
        // Criação do JScrollPane que conterá a JTextArea
        scrollPaneDescricao = new JScrollPane(labelDescricao);
        scrollPaneDescricao.setOpaque(false);
        scrollPaneDescricao.setAlignmentX(TextAttribute.JUSTIFICATION_FULL);
        scrollPaneDescricao.setFont(new Font("Monospaced", Font.PLAIN, 12));
        scrollPaneDescricao.setForeground(Color.WHITE);
        scrollPaneDescricao.getViewport().setOpaque(false); 
        scrollPaneDescricao.setBorder(null);
        scrollPaneDescricao.setBackground(new Color(0, 0, 0, 0));
        scrollPaneDescricao.setBounds(680, 310, 330, 70);
        scrollPaneDescricao.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        tela.add(scrollPaneDescricao);
        
        btnAvancar = new JButton();
        btnAvancar.setBorderPainted(false);
        btnAvancar.setContentAreaFilled(false); 
        btnAvancar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnAvancar.addActionListener(e -> exibirProximoPokemon());
        btnAvancar.setBounds(455, 625, 45, 30);
        tela.add(btnAvancar);

        btnRetroceder = new JButton();
        btnRetroceder.setBorderPainted(false);
        btnRetroceder.setContentAreaFilled(false); 
        btnRetroceder.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnRetroceder.addActionListener(e -> exibirPokemonAnterior());
        btnRetroceder.setBounds(375, 625, 45, 30);
        tela.add(btnRetroceder);

        idAtual = obterMenorID();
        exibirDetalhesPokemon(idAtual);
        
        btnEvoluir = new JButton();
        btnEvoluir.setBorderPainted(false);
        btnEvoluir.setContentAreaFilled(false); 
        btnEvoluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnEvoluir.addActionListener(e -> exibirPokemonEvoluido());
        btnEvoluir.setBounds(420, 665, 40, 40);
        tela.add(btnEvoluir);
        
        btnRegredir = new JButton();
        btnRegredir.setBorderPainted(false);
        btnRegredir.setContentAreaFilled(false); 
        btnRegredir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnRegredir.addActionListener(e -> exibirPokemonRegredido());
        btnRegredir.setBounds(420, 585, 40, 40);
        tela.add(btnRegredir);

        btnAdicionar = new JButton("Adicionar Pokemon");
        btnAdicionar.addActionListener(e -> AdicionarPokemon());
        btnAdicionar.setBounds(658,670, 180, 50);
        btnAdicionar.setFont(new Font("Monospaced", Font.BOLD, 11));
        btnAdicionar.setHorizontalAlignment(SwingConstants.CENTER);
        btnAdicionar.setForeground(Color.WHITE);
        btnAdicionar.setBorderPainted(false);
        btnAdicionar.setContentAreaFilled(false); 
        btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        tela.add(btnAdicionar);
        btnEditar = new JButton("Editar Pokemon");
        btnEditar.setBounds(845,670, 180, 50);
        btnEditar.setFont(new Font("Monospaced", Font.BOLD, 11));
        btnEditar.setHorizontalAlignment(SwingConstants.CENTER);
        btnEditar.setForeground(Color.WHITE);
        btnEditar.setBorderPainted(false);
        btnEditar.setContentAreaFilled(false); 
        btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnEditar.addActionListener(e -> editarPokemon(idAtual));
        tela.add(btnEditar);
        
        btnDeletar = new JButton();
        btnDeletar.setBounds(172,588, 77, 15);
        btnDeletar.setFont(new Font("Monospaced", Font.BOLD, 11));
        btnDeletar.setHorizontalAlignment(SwingConstants.CENTER);
        btnDeletar.setForeground(Color.WHITE);
        btnDeletar.setBorderPainted(false);
        btnDeletar.setContentAreaFilled(false); 
        btnDeletar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnDeletar.addActionListener(e -> deletarPokemon(idAtual));
        tela.add(btnDeletar);
        
        btnEvolucao = new JButton();
        btnEvolucao.setBounds(275,588, 77, 15);
        btnEvolucao.setFont(new Font("Monospaced", Font.BOLD, 11));
        btnEvolucao.setHorizontalAlignment(SwingConstants.CENTER);
        btnEvolucao.setForeground(Color.WHITE);
        btnEvolucao.setBorderPainted(false);
        btnEvolucao.setContentAreaFilled(false); 
        btnEvolucao.addActionListener(e -> adicionarEvolucao(idAtual));
        btnEvolucao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        tela.add(btnEvolucao);
        
        btnbuscar = new JButton();
        btnbuscar.setBounds(90,570, 53, 48);
        btnbuscar.setFont(new Font("Monospaced", Font.BOLD, 11));
        btnbuscar.setHorizontalAlignment(SwingConstants.CENTER);
        btnbuscar.setForeground(Color.WHITE);
        btnbuscar.setBorderPainted(false);
        btnbuscar.setContentAreaFilled(false); 
        btnbuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnbuscar.addActionListener(e -> buscarPokemon());
        tela.add(btnbuscar);
        
        btn_creditos = new JButton("Créditos");
        btn_creditos.setBounds(1000, 15, 100, 40);
        btn_creditos.setFont(new Font("verdana", Font.BOLD, 14));
        btn_creditos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn_creditos.setForeground(Color.BLACK);
        tela.add( btn_creditos);
        
         // ActionListener créditos
        btn_creditos.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                 JPanel panel = new JPanel();
                 panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                 
                JLabel namegit = new JLabel("CC-UNIPE-MARLONRN");
                namegit.setAlignmentX(Component.CENTER_ALIGNMENT);
                namegit.setHorizontalAlignment(SwingConstants.CENTER);
                namegit.setFont(new Font("Arial", Font.ITALIC, 20));
                namegit.setForeground(Color.BLACK);
                namegit.setCursor(new Cursor(Cursor.HAND_CURSOR));

                // Adiciona uma ação ao clicar no nome do git para ser enviado a minha página
                namegit.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (Desktop.isDesktopSupported()) {
                            try {
                                Desktop.getDesktop().browse(new java.net.URI("https://github.com/CC-UNIPE-MARLONRN"));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                });

                panel.add(namegit);
 
                 ImageIcon githubImage = new ImageIcon("C:\\Users\\Pichau\\Documents\\NetBeansProjects\\CRUD_POKEMON\\CRUD\\github.png");
                 Image img = githubImage.getImage(); 
                 Image newImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                 ImageIcon scaledGithubImage = new ImageIcon(newImg);
                 JLabel githubLabel = new JLabel(scaledGithubImage);
                 githubLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                 panel.add(githubLabel);

                 // JOptionPane créditos
                 JOptionPane.showMessageDialog(
                     null, 
                     panel, 
                     "Créditos", 
                     JOptionPane.PLAIN_MESSAGE 
                 );
             }
         });
        
        btnTutorial = new JButton("Tutorial");
        btnTutorial.setBounds(890, 15, 100, 40);
        btnTutorial.addActionListener(e -> ExibirTutorial());
        btnTutorial.setFont(new Font("verdana", Font.BOLD, 14));
        btnTutorial.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnTutorial.setForeground(Color.BLACK);
        tela.add( btnTutorial);
        
        fundo_pokedex = new ImageIcon("C:\\Users\\Pichau\\Documents\\NetBeansProjects\\CRUD_POKEMON\\CRUD\\fundo_pokemon.jpg");
        Image fundo_p = fundo_pokedex.getImage();
        Image newfundo = fundo_p.getScaledInstance(400, 300, Image.SCALE_SMOOTH);
        ImageIcon newfundo_p = new ImageIcon(newfundo);
        JLabel fundo = new JLabel(newfundo_p);
        fundo.setBounds(100,250,400,300);

        pokedex = new ImageIcon("C:\\Users\\Pichau\\Documents\\NetBeansProjects\\CRUD_POKEMON\\CRUD\\pokedex.png");
        JLabel wallpaper = new JLabel(pokedex);
        wallpaper.setBounds(-20, -40, 1146, 872);
        tela.add(wallpaper);
        tela.add(fundo);

        // Adicione o JPanel para a imagem
        panelImagem = new JPanel();
        panelImagem.setBounds(220, 260, 200, 200);
        panelImagem.setOpaque(false); // Torna o JPanel transparente para que o wallpaper seja visível atrás
        tela.add(panelImagem);
        tela.setComponentZOrder(panelImagem, 0); // Garante que o JPanel da imagem esteja na frente do wallpaper

        this.setVisible(true);
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


private void exibirProximoPokemon() {
    int novoId = idAtual + 1;
    int maiorId = obterMaiorID();
    
    // Busca o próximo ID disponível
    while (novoId <= maiorId && !idDisponivel(novoId)) {
        novoId++;
    }
    
    if (novoId <= maiorId) {
        idAtual = novoId;
        exibirDetalhesPokemon(idAtual);
    }
}

private void exibirPokemonAnterior() {
    int novoId = idAtual - 1;
    int menorId = obterMenorID();
    
    // Busca o ID anterior disponível
    while (novoId >= menorId && !idDisponivel(novoId)) {
        novoId--;
    }
    
    if (novoId >= menorId) {
        idAtual = novoId;
        exibirDetalhesPokemon(idAtual);
    } 
}

private int obterIDPeloNome(String nomePokemon) throws SQLException {
    int idPokemon = 0;
    String query = "SELECT ID_pokemon FROM pokemon WHERE Nome_pokemon = ?";

    try (PreparedStatement stmt = conexao.getConnection().prepareStatement(query)) {
        stmt.setString(1, nomePokemon);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                idPokemon = rs.getInt("ID_pokemon");
            }
        }
    }

    return idPokemon;
}

private void exibirPokemonEvoluido() {
    int pokemonAtual = idAtual;
    ResultSet rs = null;
    PreparedStatement stmt = null;

    try {
        String query = "SELECT Evolucao_posterior FROM pokemon WHERE id_pokemon = ?";
        stmt = conexao.getConnection().prepareStatement(query);
        stmt.setInt(1, pokemonAtual);
        rs = stmt.executeQuery();

        if (rs.next()) {
            String evolucaoPosterior = rs.getString("Evolucao_posterior");
            if (evolucaoPosterior != null && !evolucaoPosterior.isEmpty()) {
                int idEvolucao = obterIDPeloNome(evolucaoPosterior);
                if (idEvolucao != 0) {
                    idAtual = idEvolucao;
                    exibirDetalhesPokemon(idAtual);
                } else {
                      return;
                }
            } else {
                return;
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

private void exibirPokemonRegredido() {
    int pokemonAtual = idAtual;
    ResultSet rs = null;
    PreparedStatement stmt = null;

    try {
        String query = "SELECT Evolucao_anterior FROM pokemon WHERE id_pokemon = ?";
        stmt = conexao.getConnection().prepareStatement(query);
        stmt.setInt(1, pokemonAtual);
        rs = stmt.executeQuery();

        if (rs.next()) {
            String evolucaoAnterior = rs.getString("Evolucao_anterior");
            if (evolucaoAnterior != null && !evolucaoAnterior.isEmpty()) {
                int idRegrecao = obterIDPeloNome(evolucaoAnterior);
                if (idRegrecao != 0) {
                    idAtual = idRegrecao;
                    exibirDetalhesPokemon(idAtual);
                } else {
                    return;
                }
            } else {
                return;
            }
        } else {
           return;
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

// Método para verificar se um ID está disponível
private boolean idDisponivel(int id) {
    try {
        String query = "SELECT COUNT(*) AS count FROM pokemon WHERE id_pokemon = ?";
        PreparedStatement stmt = conexao.getConnection().prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            int count = rs.getInt("count");
            return count > 0; // Retorna true se o ID estiver disponível
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return false; // Retorna false se houver algum erro ou se o ID não estiver disponível
}

    private void exibirDetalhesPokemon(int idPokemon) {
        try {
            String query = "SELECT * FROM pokemon WHERE id_pokemon=?";
            PreparedStatement stmt = conexao.getConnection().prepareStatement(query);
            stmt.setInt(1, idPokemon);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                labelNome.setText("" + rs.getString("Nome_pokemon"));
                labelPeso.setText("Peso: " + rs.getDouble("Peso") + " kg");
                labelAltura.setText("Altura: " + rs.getDouble("Altura") + " m");
                if(rs.getString("Descricao") == null || rs.getString("Descricao").equals("")){
                    labelDescricao.setText("");
                }
                else
                labelDescricao.setText("Descrição: " + rs.getString("Descricao"));
                String tipo1 = rs.getString("Tipo1");
                String tipo2 = rs.getString("Tipo2");
                
                exibirImagemTipo(tipo1, labelTipo1);
                exibirImagemTipo(tipo2, labelTipo2);

                byte[] imgBytes = rs.getBytes("Imagem");
                exibirImagem(imgBytes);
                
                exibirVantagensFraquesas(tipo1, labelTipo1);
               if (tipo2 == null) {
                    labelTipo2.setVisible(false);
                } else {
                    // Configuramos a visibilidade da label como true
                    labelTipo2.setVisible(true);

                    // Exibimos as vantagens e fraquezas apenas se o tipo não for nulo
                    exibirVantagensFraquesas(tipo2, labelTipo2);
                }
                
            }

            rs.close();
            stmt.close();
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
    }
    
        private void exibirImagemTipo(String tipo, JLabel label) {
      // Mostra a imagem correspondente ao tipo
      String caminho = "C:\\Users\\Pichau\\Documents\\NetBeansProjects\\CRUD_POKEMON\\CRUD\\Tipagem_Pokemons\\" + tipo + ".png";
      ImageIcon imagemIcon = new ImageIcon(caminho);
      Image imagem = imagemIcon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
      label.setIcon(new ImageIcon(imagem));
      label.setVisible(true);
  }

private void exibirVantagensFraquesas(String tipo, JLabel label){
    // Se o tipo for nulo ou vazio, configuramos o cursor para o padrão e retornamos
    if (tipo == null) {
        label.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        return;
    }

    // Configuramos o cursor para mão se o tipo for válido
    label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    // Adicionamos o mouse listener para exibir as vantagens e fraquezas
    label.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
                exibirTelaTipos(tipo);
        }
    });
}

   private void exibirTelaTipos(String tipo) {
        // Cria uma nova instância da tela de tipos
        instanciaTelaTipos = new Tela_tipos(tipo);
        instanciaTelaTipos.setVisible(true);
    }
        
   private void ExibirTutorial() {
        setEnabled(false);
        Tela_Tutorial tela_tutorial = new Tela_Tutorial();
        tela_tutorial.setVisible(true);
        tela_tutorial.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            setEnabled(true);
        }
});
    }  
   
    private void AdicionarPokemon() {
        setEnabled(false);
        Tela_addPokemon telaAddPokemon = new Tela_addPokemon();
        telaAddPokemon.setVisible(true);
        telaAddPokemon.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            exibirProximoPokemon();
            setEnabled(true);
        }
});
    }   
    
private void deletarPokemon(int id) {
    ImageIcon pokebola = new ImageIcon("C:\\Users\\Pichau\\Documents\\NetBeansProjects\\CRUD_POKEMON\\CRUD\\pokebola.png");
    Image image = pokebola.getImage();
    Image newImage = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
    ImageIcon newpokebola = new ImageIcon(newImage);
    
    String query = "SELECT * FROM pokemon WHERE id_pokemon=?";
    try (PreparedStatement stmt = conexao.getConnection().prepareStatement(query)) {
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            // Confirmação para deletar o Pokémon
            Object[] options = {"Sim", "Não"};
            int confirmacao = JOptionPane.showOptionDialog(
                    this, 
                    "Tem certeza que deseja deletar o Pokémon?", 
                    "Confirmar Deleção", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.QUESTION_MESSAGE, 
                    newpokebola, 
                    options, 
                    options[0]);

            if (confirmacao == JOptionPane.YES_OPTION) {
                try {
                    // Consulta ao banco de dados para obter o nome do Pokémon com base no ID atual
                    String nomePokemon = obterNomePokemonPorID(id);
                    // Deleta o Pokémon usando o nome obtido
                    metodosCrud.deletarPokemon(nomePokemon);
                    JOptionPane.showMessageDialog(this, "Pokémon deletado com sucesso!", "Aviso", JOptionPane.INFORMATION_MESSAGE, newpokebola);

                    // Verifica se há mais pokémons no banco após a exclusão
                    if (verificarExistenciaPokemons()) {
                        exibirProximoPokemon();
                        exibirPokemonAnterior();
                    } else {
                        // Se não houver mais pokémons, limpa os dados da tela
                        limparTela();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao deletar Pokémon: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, newpokebola);
                    ex.printStackTrace();
                }
            } else if (confirmacao == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(this, "Requisição Abortada!!", "Aviso", JOptionPane.INFORMATION_MESSAGE, newpokebola);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nenhum Pokémon encontrado!", "Erro", JOptionPane.ERROR_MESSAGE, newpokebola);
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Erro ao consultar Pokémon: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, newpokebola);
        ex.printStackTrace();
    }
}

private boolean verificarExistenciaPokemons() {
    try {
        String query = "SELECT COUNT(*) FROM pokemon";
        PreparedStatement stmt = conexao.getConnection().prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            int count = rs.getInt(1);
            return count > 0;
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return false; // Retorna false por padrão em caso de erro ou nenhum resultado
}


private void limparTela() {
    labelAltura.setText("");
    labelDescricao.setText("");
    labelNome.setText("");
    labelTipo1.setVisible(false);
    labelTipo2.setVisible(false);
    labelPeso.setText("");
    labelImagem.setIcon(null);
    labelTipo1.setIcon(null);
    labelTipo2.setIcon(null);
}

private String obterNomePokemonPorID(int id) throws SQLException {
    String nomePokemon = null;
    String query = "SELECT Nome_pokemon FROM pokemon WHERE id_pokemon = ?";
    PreparedStatement stmt = conexao.getConnection().prepareStatement(query);
    stmt.setInt(1, id);
    ResultSet rs = stmt.executeQuery();
    if (rs.next()) {
        nomePokemon = rs.getString("Nome_pokemon");
    }
    rs.close();
    stmt.close();
    return nomePokemon;
}

private void editarPokemon(int idPokemon) {
    try {
        ImageIcon pokebola = new ImageIcon("C:\\Users\\Pichau\\Documents\\NetBeansProjects\\CRUD_POKEMON\\CRUD\\pokebola.png");
        Image image = pokebola.getImage();
        Image newImage = image.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        ImageIcon newpokebola = new ImageIcon(newImage);
        
        String query = "SELECT * FROM pokemon WHERE id_pokemon=?";
        PreparedStatement stmt = conexao.getConnection().prepareStatement(query);
        stmt.setInt(1, idPokemon);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Object[] opcoes = {"Nome", "Altura", "Peso", "Tipo 1", "Tipo 2", "Descrição", "Imagem"};
            String opcaoSelecionada = (String) JOptionPane.showInputDialog(
                    this,
                    "Selecione o que deseja editar:",
                    "Editar Pokémon",
                    JOptionPane.PLAIN_MESSAGE,
                    newpokebola,
                    opcoes,
                    opcoes[0]);
            if (opcaoSelecionada != null) {
                switch (opcaoSelecionada) {
                    case "Nome":
                        String novoNome = JOptionPane.showInputDialog(this, "Digite o novo nome:");
                        if (novoNome != null) {
                            try {
                                String nomeAntigo = rs.getString("Nome_pokemon");
                                Double peso = rs.getDouble("Peso");
                                Double altura = rs.getDouble("Altura");
                                String descricao = rs.getString("Descricao");
                                String tipo1 = rs.getString("Tipo1");
                                String tipo2 = rs.getString("Tipo2");
                                byte[] imgBytes = rs.getBytes("Imagem");
                                
                                // Chama o método editarPokemon com os novos valores
                                metodosCrud.editarPokemon(nomeAntigo, novoNome, peso, altura, tipo1, tipo2, descricao, imgBytes);
                                
                                exibirDetalhesPokemon(idPokemon);
                                JOptionPane.showMessageDialog(this, "Nome do Pokémon editado com sucesso!");
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(this, "Erro ao editar nome do Pokémon: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                                ex.printStackTrace();
                            }
                        }
                        break;
                      case "Altura":
                        Double novaAltura = Double.parseDouble(JOptionPane.showInputDialog(this, "Digite a nova altura:"));
                        if (novaAltura != null) {
                            try {
                                String nome = rs.getString("Nome_pokemon");
                                Double peso = rs.getDouble("Peso");
                                String descricao = rs.getString("Descricao");
                                String tipo1 = rs.getString("Tipo1");
                                String tipo2 = rs.getString("Tipo2");
                                byte[] imgBytes = rs.getBytes("Imagem");

                                // Chama o método editarPokemon com os novos valores
                                metodosCrud.editarPokemon(nome, nome, peso, novaAltura, tipo1, tipo2, descricao, imgBytes);
                                exibirDetalhesPokemon(idAtual);
                                JOptionPane.showMessageDialog(this, "Altura do Pokémon editada com sucesso!");
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(this, "Erro ao editar altura do Pokémon: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                                ex.printStackTrace();
                            }
                        }
                        break;
                      case "Peso":
                            Double novoPeso = Double.parseDouble(JOptionPane.showInputDialog(this, "Digite o novo peso:"));
                        if (novoPeso != null) {
                            try {
                                String nome = rs.getString("Nome_pokemon");
                                Double altura = rs.getDouble("Altura");
                                String descricao = rs.getString("Descricao");
                                String tipo1 = rs.getString("Tipo1");
                                String tipo2 = rs.getString("Tipo2");
                                byte[] imgBytes = rs.getBytes("Imagem");

                                // Chama o método editarPokemon com os novos valores
                                metodosCrud.editarPokemon(nome, nome, novoPeso, altura, tipo1, tipo2, descricao, imgBytes);
                                exibirDetalhesPokemon(idPokemon);
                                JOptionPane.showMessageDialog(this, "Peso do Pokémon editado com sucesso!");
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(this, "Erro ao editar peso do Pokémon: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                                ex.printStackTrace();
                            }
                        }
                        break;
                        case "Tipo 1":    
                            case "Tipo 2":
                                String[] tiposDisponiveis;
                                if (opcaoSelecionada.equals("Tipo 2")) {
                                    tiposDisponiveis = new String[]{"", "Normal", "Fire (Fogo)", "Water (Água)", "Grass (Grama)", "Flying (Voador)",
                                            "Fighting (Lutador)", "Poison (Veneno)", "Electric (Elétrico)", "Ground (Terra)", "Rock (Pedra)",
                                            "Psychic (Psíquico)", "Ice (Gelo)", "Bug (Inseto)", "Ghost (Fantasma)", "Steel (Ferro)",
                                            "Dragon (Dragão)", "Dark (Sombrio)", "Fairy (Fada)"};
                                } else {
                                    tiposDisponiveis = new String[]{"Normal", "Fire (Fogo)", "Water (Água)", "Grass (Grama)", "Flying (Voador)",
                                            "Fighting (Lutador)", "Poison (Veneno)", "Electric (Elétrico)", "Ground (Terra)", "Rock (Pedra)",
                                            "Psychic (Psíquico)", "Ice (Gelo)", "Bug (Inseto)", "Ghost (Fantasma)", "Steel (Ferro)",
                                            "Dragon (Dragão)", "Dark (Sombrio)", "Fairy (Fada)"};
                                }

                                String novoTipo = (String) JOptionPane.showInputDialog(
                                        this,
                                        "Selecione o novo tipo:",
                                        "Editar Tipo",
                                        JOptionPane.PLAIN_MESSAGE,
                                        newpokebola,
                                        tiposDisponiveis,
                                        tiposDisponiveis[0]
                                );

                                if (novoTipo != null) {
                                    try {
                                        String nome = rs.getString("Nome_pokemon");
                                        Double peso = rs.getDouble("Peso");
                                        Double altura = rs.getDouble("Altura");
                                        String descricao = rs.getString("Descricao");
                                        String tipo1 = rs.getString("Tipo1");
                                        String tipo2 = rs.getString("Tipo2");
                                        byte[] imgBytes = rs.getBytes("Imagem");

                                        if (opcaoSelecionada.equals("Tipo 1")) {
                                            tipo1 = novoTipo;
                                        } else if (opcaoSelecionada.equals("Tipo 2")) {
                                            if (novoTipo.equals("")) {
                                                novoTipo = null;
                                            }
                                            tipo2 = novoTipo;
                                        }

                                        // Chama o método editarPokemon com os novos valores
                                        metodosCrud.editarPokemon(nome, nome, peso, altura, tipo1, tipo2, descricao, imgBytes);
                                        exibirDetalhesPokemon(idPokemon);
                                        exibirProximoPokemon();
                                        exibirPokemonAnterior();
                                        JOptionPane.showMessageDialog(this, "Tipo do Pokémon editado com sucesso!");
                                    } catch (SQLException ex) {
                                        JOptionPane.showMessageDialog(this, "Erro ao editar tipo do Pokémon: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                                        ex.printStackTrace();
                                    }
                                }
                                break;
                           case "Descrição":
                                String nova_descricao = JOptionPane.showInputDialog(this, "Digite a descfição:");
                                   try {
                                        String nome = rs.getString("Nome_pokemon");
                                        Double peso = rs.getDouble("Peso");
                                        Double altura = rs.getDouble("Altura");
                                        String tipo1 = rs.getString("Tipo1");
                                        String tipo2 = rs.getString("Tipo2");
                                        byte[] imgBytes = rs.getBytes("Imagem");
                                         // Chama o método editarPokemon com os novos valores
                                        metodosCrud.editarPokemon(nome, nome, peso, altura, tipo1, tipo2, nova_descricao, imgBytes);
                                        exibirDetalhesPokemon(idPokemon);
                                        JOptionPane.showMessageDialog(this, "Descrição do Pokémon editado com sucesso!");
                                    } catch (SQLException ex) {
                                        JOptionPane.showMessageDialog(this, "Erro ao editar descricao do Pokémon: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                                        ex.printStackTrace();
                                    }                      
                                break;
                         case "Imagem":
                             JFileChooser fileChooser = new JFileChooser();
                             int escolha = fileChooser.showOpenDialog(this);
                             if (escolha == JFileChooser.APPROVE_OPTION) {
                                 try {
                                     File arquivoImagem = fileChooser.getSelectedFile();
                                     FileInputStream fis = new FileInputStream(arquivoImagem);
                                     ByteArrayOutputStream bos = new ByteArrayOutputStream();
                                     byte[] buffer = new byte[1024];
                                     int comprimento;
                                     while ((comprimento = fis.read(buffer)) != -1) {
                                         bos.write(buffer, 0, comprimento);
                                     }
                                     byte[] novaImagem = bos.toByteArray();
                                     fis.close();
                                     bos.close();

                                     String nome = rs.getString("Nome_pokemon");
                                     Double peso = rs.getDouble("Peso");
                                     Double altura = rs.getDouble("Altura");
                                     String tipo1 = rs.getString("Tipo1");
                                     String tipo2 = rs.getString("Tipo2");
                                     String descricao = rs.getString("Descricao");

                                     metodosCrud.editarPokemon(nome, nome, peso, altura, tipo1, tipo2, descricao, novaImagem);
                                        exibirProximoPokemon();
                                        exibirPokemonAnterior();
                                     JOptionPane.showMessageDialog(this, "Imagem do Pokémon editada com sucesso!");
                                 } catch (IOException | SQLException ex) {
                                     JOptionPane.showMessageDialog(this, "Erro ao editar imagem do Pokémon: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                                     ex.printStackTrace();
                                 }
                             }
                             break;
                    default:
                        JOptionPane.showMessageDialog(this, "Opção inválida!");
                }
            }
        }
          else{
                  JOptionPane.showMessageDialog(this, "Nenhum Pokemon encontrado!" , "Erro", JOptionPane.ERROR_MESSAGE, newpokebola);
            }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Erro ao buscar informações do Pokémon: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    }
}

    
    private void exibirImagem(byte[] imgBytes) throws IOException {
        if (labelImagem != null) {
            tela.remove(labelImagem);
        }

        ImageIcon icon = new ImageIcon(imgBytes);
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newImage);

        labelImagem = new JLabel(newIcon);
        labelImagem.setBounds(220, 260, 200, 200);
        tela.add(labelImagem);
        tela.repaint();
         // Adicione a imagem ao JPanel
    if (panelImagem != null) {
        panelImagem.removeAll(); // Limpe qualquer imagem anterior
        panelImagem.add(labelImagem); // Adicione a nova imagem ao JPanel
        panelImagem.repaint(); // Repinte o JPanel para exibir a nova imagem
    }
}

    private void buscarPokemon() {
        ImageIcon pokebola = new ImageIcon("C:\\Users\\Pichau\\Documents\\NetBeansProjects\\CRUD_POKEMON\\CRUD\\pokebola.png");
        Image image = pokebola.getImage();
        Image newImage = image.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        ImageIcon newpokebola = new ImageIcon(newImage);
        
    if(verificarExistenciaPokemons() != false){
        
    // Solicita ao usuário que insira o nome do Pokémon a ser buscado
    String nomePokemon = JOptionPane.showInputDialog(null, "Digite o nome do Pokémon:", "Buscar Pokémon", JOptionPane.PLAIN_MESSAGE);

    // Verifica se o usuário inseriu um nome de Pokémon
    if (nomePokemon != null && !nomePokemon.isEmpty()) {
        // Lista para armazenar os nomes dos Pokémon encontrados
        java.util.List<String> pokemonsEncontrados = new java.util.ArrayList<>();
        
        // Percorre o banco de dados em busca de Pokémon cujo nome contenha a substring inserida pelo usuário
        try {
            String query = "SELECT Nome_pokemon FROM pokemon WHERE Nome_pokemon LIKE ?";
            PreparedStatement stmt = conexao.getConnection().prepareStatement(query);
            stmt.setString(1, "%" + nomePokemon + "%");
            ResultSet rs = stmt.executeQuery();

            // Adiciona os nomes dos Pokémon encontrados à lista
            while (rs.next()) {
                pokemonsEncontrados.add(rs.getString("Nome_pokemon"));
            }

            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // Verifica se foram encontrados Pokémon
        if (!pokemonsEncontrados.isEmpty()) {
            // Exibe os Pokémon encontrados em uma lista de opções
            Object[] options = pokemonsEncontrados.toArray();
            String pokemonSelecionado = (String) JOptionPane.showInputDialog(null, "Pokémon encontrados:", "Escolha um Pokémon", JOptionPane.PLAIN_MESSAGE, newpokebola, options, options[0]);

            // Verifica se o usuário selecionou um Pokémon na lista
            if (pokemonSelecionado != null) {
                                try {
                       String query = "SELECT ID_pokemon FROM pokemon WHERE Nome_pokemon = ?";
                       PreparedStatement stmt = conexao.getConnection().prepareStatement(query);
                       stmt.setString(1, pokemonSelecionado);
                       ResultSet rs = stmt.executeQuery();

                       // Verifica se o Pokémon foi encontrado no banco de dados
                       if (rs.next()) {
                           // Recupera as informações completas do Pokémon
                           int ID_pokemon = rs.getInt("ID_pokemon");;

                           // Exibe as informações do Pokémon na tela
                           exibirDetalhesPokemon(ID_pokemon);
                       }

                       rs.close();
                       stmt.close();
                   } catch (SQLException ex) {
                       ex.printStackTrace();
                   }

            }
        } else {
            // Caso nenhum Pokémon seja encontrado, exibe uma mensagem informando ao usuário
            JOptionPane.showMessageDialog(null, "Nenhum Pokémon encontrado com o nome \"" + nomePokemon + "\".", "Pokémon não encontrado", JOptionPane.WARNING_MESSAGE, newpokebola);
        }
    } else {
        // Caso o usuário cancele a operação ou não insira um nome, exibe uma mensagem informando ao usuário
        JOptionPane.showMessageDialog(null, "Nenhum nome de Pokémon inserido.", "Busca cancelada", JOptionPane.INFORMATION_MESSAGE, newpokebola);
    }
    }  else{
                  JOptionPane.showMessageDialog(this, "Nenhum Pokemon encontrado!" , "Erro", JOptionPane.ERROR_MESSAGE, newpokebola);
            }
}
    
    private void adicionarEvolucao(int idAtual) {
    try {
        ImageIcon pokebola = new ImageIcon("C:\\Users\\Pichau\\Documents\\NetBeansProjects\\CRUD_POKEMON\\CRUD\\pokebola.png");
        Image image = pokebola.getImage();
        Image newImage = image.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        ImageIcon newpokebola = new ImageIcon(newImage);
        
        // Obter o nome do Pokémon atualmente exibido
        String nomePokemonAtual = obterNomePokemonPorID(idAtual);
        
        // Exibir uma lista de Pokémon para escolha da evolução
        String evolucaoSelecionada = exibirListaPokemons();
        
        // Atualizar o banco de dados com a evolução do Pokémon
        if (evolucaoSelecionada != null && nomePokemonAtual != null) {
            try {
                String query1 = "UPDATE pokemon SET evolucao_posterior = ? WHERE Nome_pokemon = ?";
                PreparedStatement stmt1 = conexao.getConnection().prepareStatement(query1);
                stmt1.setString(1, evolucaoSelecionada);
                stmt1.setString(2, nomePokemonAtual);
                stmt1.executeUpdate();
                stmt1.close();

                String query2 = "UPDATE pokemon SET evolucao_anterior = ? WHERE Nome_pokemon = ?";
                PreparedStatement stmt2 = conexao.getConnection().prepareStatement(query2);
                stmt2.setString(1, nomePokemonAtual);
                stmt2.setString(2, evolucaoSelecionada);
                stmt2.executeUpdate();
                stmt2.close();

                // Exibir uma mensagem de sucesso
                JOptionPane.showMessageDialog(null, "Evolução do Pokémon atualizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE, newpokebola);
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
else {
        JOptionPane.showMessageDialog(null, "Nenhum Pokemon selecionado.", "Evolucação cancelada", JOptionPane.INFORMATION_MESSAGE, newpokebola);
    }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}

    private String exibirListaPokemons() {
    try {
        
        ImageIcon pokebola = new ImageIcon("C:\\Users\\Pichau\\Documents\\NetBeansProjects\\CRUD_POKEMON\\CRUD\\pokebola.png");
        Image image = pokebola.getImage();
        Image newImage = image.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        ImageIcon newpokebola = new ImageIcon(newImage);
        
        // Lista para armazenar os nomes dos Pokémon encontrados
         java.util.List<String> pokemonsEncontrados = new ArrayList<>();

        // Consultar o banco de dados para obter todos os nomes dos Pokémon
        String query = "SELECT Nome_pokemon FROM pokemon";
        PreparedStatement stmt = conexao.getConnection().prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        // Adicionar os nomes dos Pokémon encontrados à lista
        while (rs.next()) {
            pokemonsEncontrados.add(rs.getString("Nome_pokemon"));
        }

        rs.close();
        stmt.close();

        // Verificar se foram encontrados Pokémon
        if (!pokemonsEncontrados.isEmpty()) {
            // Exibir os Pokémon encontrados em uma lista de opções
            Object[] options = pokemonsEncontrados.toArray();
            return (String) JOptionPane.showInputDialog(null, "Selecione a evolução do Pokemon atual:", "Evolução do Pokémon", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        } else {
            // Caso nenhum Pokémon seja encontrado, exibir uma mensagem informando ao usuário
            JOptionPane.showMessageDialog(null, "Nenhum Pokémon encontrado.", "Erro", JOptionPane.ERROR_MESSAGE, newpokebola);
            return null;
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        return null;
       }
    }
}