package IMPLEMENTACOES;

import javax.swing.*;
import java.io.File;

public class Capitura_IMG {

    // Método para capturar uma imagem do computador
    public static File capturarImagem() {
        JFileChooser fileChooser = new JFileChooser();
        int resultado = fileChooser.showOpenDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        return null;
    }
}