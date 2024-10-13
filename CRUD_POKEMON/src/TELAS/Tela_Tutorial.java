package TELAS;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;

public class Tela_Tutorial extends JFrame {
    
     JPanel painel_tutorial;
     JLabel lbl_Titulo;
     JLabel lbl_scroll;
     JLabel lbl_search;
     JLabel lbl_add;
     JLabel lbl_edit;
     JLabel lbl_delete;
     JLabel lbl_type;
     JLabel lbl_paineltype1;
     JLabel lbl_paineltype2;
     JLabel lbl_evo;
     JLabel lbl_seta1;
     JLabel lbl_seta2;
     JLabel lbltxt1;
     JLabel lbltxt2;
     JLabel lbltxt3;
     JLabel lbltxt4;
     JLabel lbltxt5;
     JLabel lbltxt6;
     JLabel lbltxt7;
     JLabel lbltxt8;
     JLabel lbltxt9;
     JLabel lbltxt10;
     JLabel lbltxt11;
     JLabel lbltxt12;
     JLabel lbltxt13;
  
    
 public Tela_Tutorial() {
    super("Tela de tutorial");
    painel_tutorial = new JPanel();
    painel_tutorial.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
    painel_tutorial.setLayout(null);
    
     ImageIcon icon = new ImageIcon("C:\\Users\\Pichau\\Documents\\NetBeansProjects\\CRUD_POKEMON\\CRUD\\pokebola.png");
     setIconImage(icon.getImage());

    ImageIcon Scroll = new ImageIcon("C:\\Users\\Pichau\\Documents\\NetBeansProjects\\CRUD_POKEMON\\CRUD\\Scroll.png");
    Image IMG = Scroll.getImage();
    Image newIMG = IMG.getScaledInstance(70, 70, IMG.SCALE_SMOOTH); // Ajusta o tamanho da imagem
    ImageIcon IMGScroll = new ImageIcon(newIMG);
    
    ImageIcon Sbutton = new ImageIcon("C:\\Users\\Pichau\\Documents\\NetBeansProjects\\CRUD_POKEMON\\CRUD\\search_button.png");
    Image SIMG = Sbutton.getImage();
    Image newSIMG = SIMG.getScaledInstance(30, 30, SIMG.SCALE_SMOOTH); // Ajusta o tamanho da imagem
    ImageIcon IMGSearch = new ImageIcon(newSIMG);
    
    ImageIcon AddButton = new ImageIcon("C:\\Users\\Pichau\\Documents\\NetBeansProjects\\CRUD_POKEMON\\CRUD\\add_button.png");
    Image ADD_IMG = AddButton.getImage();
    Image newADD_IMG = ADD_IMG.getScaledInstance(70, 25, ADD_IMG.SCALE_SMOOTH); // Ajusta o tamanho da imagem
    ImageIcon IMGADD = new ImageIcon(newADD_IMG);
    
    ImageIcon EditButton = new ImageIcon("C:\\Users\\Pichau\\Documents\\NetBeansProjects\\CRUD_POKEMON\\CRUD\\edit_button.png");
    Image Edit_IMG = EditButton.getImage();
    Image newEdit_IMG = Edit_IMG.getScaledInstance(70, 28, Edit_IMG.SCALE_SMOOTH); // Ajusta o tamanho da imagem
    ImageIcon IMGEDIT = new ImageIcon(newEdit_IMG);
    
    ImageIcon DeleteButton = new ImageIcon("C:\\Users\\Pichau\\Documents\\NetBeansProjects\\CRUD_POKEMON\\CRUD\\delete_button.png");
    Image DeleteIMG = DeleteButton.getImage();
    Image newDeleteIMG = DeleteIMG.getScaledInstance(50, 10, DeleteIMG.SCALE_SMOOTH); // Ajusta o tamanho da imagem
    ImageIcon IMGDelete = new ImageIcon(newDeleteIMG);
    
    ImageIcon EvoButton = new ImageIcon("C:\\Users\\Pichau\\Documents\\NetBeansProjects\\CRUD_POKEMON\\CRUD\\evolution_button.png");
    Image EvoIMG = EvoButton.getImage();
    Image newEvoIMG = EvoIMG.getScaledInstance(50, 10, EvoIMG.SCALE_SMOOTH); // Ajusta o tamanho da imagem
    ImageIcon IMGEvo = new ImageIcon(newEvoIMG);
       
    ImageIcon typeimage = new ImageIcon("C:\\Users\\Pichau\\Documents\\NetBeansProjects\\CRUD_POKEMON\\CRUD\\type.png");
    Image typeIMG = typeimage.getImage();
    Image newtypeIMG = typeIMG.getScaledInstance(80, 30, typeIMG.SCALE_SMOOTH); // Ajusta o tamanho da imagem
    ImageIcon IMGtype = new ImageIcon(newtypeIMG);
    
    ImageIcon typepainel1 = new ImageIcon("C:\\Users\\Pichau\\Documents\\NetBeansProjects\\CRUD_POKEMON\\CRUD\\tab1_type.png");
    Image typep1IMG = typepainel1.getImage();
    Image newtypep1IMG = typep1IMG.getScaledInstance(45, 25,  typep1IMG.SCALE_SMOOTH); // Ajusta o tamanho da imagem
    ImageIcon IMGptype = new ImageIcon(newtypep1IMG);
    
    ImageIcon typepainel2 = new ImageIcon("C:\\Users\\Pichau\\Documents\\NetBeansProjects\\CRUD_POKEMON\\CRUD\\tab2_type.png");
    Image typep2IMG = typepainel2.getImage();
    Image newtypep2IMG = typep2IMG.getScaledInstance(45, 25,  typep2IMG.SCALE_SMOOTH); // Ajusta o tamanho da imagem
    ImageIcon IMGp2type = new ImageIcon(newtypep2IMG);
    
    ImageIcon typeseta = new ImageIcon("C:\\Users\\Pichau\\Documents\\NetBeansProjects\\CRUD_POKEMON\\CRUD\\setaBaixo.png");
    Image setaIMG = typeseta.getImage();
    Image newsetaIMG = setaIMG.getScaledInstance(20, 20,  setaIMG.SCALE_SMOOTH); // Ajusta o tamanho da imagem
    ImageIcon IMGseta = new ImageIcon(newsetaIMG);
    
    lbltxt1 = new JLabel("1. Setas");
    lbltxt1.setBounds(10, 30, 120, 60);
    lbltxt1.setFont(new Font("Monospaced", Font.BOLD, 20));;
    painel_tutorial.add(lbltxt1);
    
    lbl_Titulo = new JLabel("TUTORIAL");
    lbl_Titulo.setBounds(140, 5, 150, 30);
    lbl_Titulo.setFont(new Font("Monospaced", Font.BOLD, 25));
    painel_tutorial.add(lbl_Titulo); 
    
    lbltxt2 = new JLabel("Regredir Evolução");
    lbltxt2.setBounds(30, 50, 120, 60);
    lbltxt2.setFont(new Font("Monospaced", Font.BOLD, 10));;
    painel_tutorial.add(lbltxt2);
    
    lbltxt3 = new JLabel("Prosseguir Evolução");
    lbltxt3.setBounds(33, 125, 120, 60);
    lbltxt3.setFont(new Font("Monospaced", Font.BOLD, 10));;
    painel_tutorial.add(lbltxt3);
    
    lbltxt4 = new JLabel("Avançar");
    lbltxt4.setBounds(116, 88, 120, 60);
    lbltxt4.setFont(new Font("Monospaced", Font.BOLD, 10));;
    painel_tutorial.add(lbltxt4);
    
    lbltxt5 = new JLabel("Voltar");
    lbltxt5.setBounds(10, 88, 120, 60);
    lbltxt5.setFont(new Font("Monospaced", Font.BOLD, 10));;
    painel_tutorial.add(lbltxt5);
    
    lbltxt6 = new JLabel("2. Botões");
    lbltxt6.setBounds(250, 30, 120, 60);
    lbltxt6.setFont(new Font("Monospaced", Font.BOLD, 20));;
    painel_tutorial.add(lbltxt6);
    
    lbltxt7 = new JLabel("Buscar Pokemon");
    lbltxt7.setBounds(230, 85, 120, 60);
    lbltxt7.setFont(new Font("Monospaced", Font.BOLD, 7));;
    painel_tutorial.add(lbltxt7);
    
    lbltxt8 = new JLabel("Adicionar Pokemon");
    lbltxt8.setBounds(310, 85, 120, 60);
    lbltxt8.setFont(new Font("Monospaced", Font.BOLD, 7));;
    painel_tutorial.add(lbltxt8);
    
    lbltxt9 = new JLabel("Editar Pokemon");
    lbltxt9.setBounds(315, 125, 120, 60);
    lbltxt9.setFont(new Font("Monospaced", Font.BOLD, 7));;
    painel_tutorial.add(lbltxt9);
    
    lbltxt10 = new JLabel("Deletar Pokemon");
    lbltxt10.setBounds(225, 105, 120, 60);
    lbltxt10.setFont(new Font("Monospaced", Font.BOLD, 7));;
    painel_tutorial.add(lbltxt10);
    
    lbltxt11 = new JLabel("Registrar Evolução");
    lbltxt11.setBounds(225, 125, 120, 60);
    lbltxt11.setFont(new Font("Monospaced", Font.BOLD, 7));;
    painel_tutorial.add(lbltxt11);
    
    lbltxt12 = new JLabel("3. Tipos");
    lbltxt12.setBounds(10, 150, 120, 60);
    lbltxt12.setFont(new Font("Monospaced", Font.BOLD, 20));;
    painel_tutorial.add(lbltxt12);
    
    lbltxt13 = new JLabel("Os tipos exibem as fraquesas e vantagens.");
    lbltxt13.setBounds(10, 260, 220, 60);
    lbltxt13.setFont(new Font("Monospaced", Font.BOLD, 7));
    painel_tutorial.add(lbltxt13);
    
    lbl_search = new JLabel(IMGSearch);
    lbl_search.setBounds(230, 70, 50, 50);
    painel_tutorial.add(lbl_search);
    
    lbl_add = new JLabel(IMGADD);
    lbl_add.setBounds(300, 75, 90, 40);
    painel_tutorial.add(lbl_add);
    
    lbl_edit = new JLabel(IMGEDIT);
    lbl_edit.setBounds(300, 115, 90, 40);
    painel_tutorial.add(lbl_edit);
    
    lbl_delete = new JLabel(IMGDelete);
    lbl_delete.setBounds(211, 105, 90, 40);
    painel_tutorial.add(lbl_delete);
    
    lbl_evo = new JLabel(IMGEvo);
    lbl_evo.setBounds(211, 125, 90, 40);
    painel_tutorial.add(lbl_evo);
    
    lbl_scroll = new JLabel(IMGScroll);
    lbl_scroll.setBounds(47, 80, 70, 70);
    painel_tutorial.add(lbl_scroll);
    
    lbl_type = new JLabel(IMGtype);
    lbl_type.setBounds(40, 200, 80, 30);
    painel_tutorial.add( lbl_type);
    
    lbl_paineltype1 = new JLabel(IMGptype);
    lbl_paineltype1.setBounds(15, 245, 80, 40);
    painel_tutorial.add( lbl_paineltype1);
   
    lbl_paineltype2 = new JLabel(IMGp2type);
    lbl_paineltype2.setBounds(65, 245, 80, 40);
    painel_tutorial.add( lbl_paineltype2);
    
    lbl_seta1 = new JLabel(IMGseta);
    lbl_seta1.setBounds(90, 220, 20, 40);
    painel_tutorial.add( lbl_seta1);
    
    lbl_seta2 = new JLabel(IMGseta);
    lbl_seta2.setBounds(50, 220, 20, 40);
    painel_tutorial.add( lbl_seta2);
    

    this.add(painel_tutorial);
    this.setSize(420, 400); // Mantém o tamanho da janela em 500x400
    this.setLocationRelativeTo(null);
    this.getContentPane().setBackground(Color.WHITE);
    this.setResizable(false);
    this.setVisible(false);
    }
}