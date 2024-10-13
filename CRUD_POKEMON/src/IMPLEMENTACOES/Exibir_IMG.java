package IMPLEMENTACOES;

import javax.swing.*;
import java.awt.*;

public class Exibir_IMG {

    private byte[] imagemBytes;

    public Exibir_IMG(byte[] imagemBytes) {
        this.imagemBytes = imagemBytes;
    }

    public void exibirImagemRedimensionada() {
        // Implemente a lógica para exibir a imagem redimensionada
        try {
            // Converter o array de bytes em uma imagem
            ImageIcon icon = new ImageIcon(imagemBytes);
            Image image = icon.getImage();

            // Redimensionar a imagem para 90x90 pixels
            Image imagemRedimensionada = image.getScaledInstance(90, 90, Image.SCALE_SMOOTH);

            // Exibir a imagem em um JLabel
            JLabel labelImagem = new JLabel(new ImageIcon(imagemRedimensionada));

            // Criar e exibir um JFrame para mostrar a imagem
            JFrame frame = new JFrame();
            frame.getContentPane().add(labelImagem);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
