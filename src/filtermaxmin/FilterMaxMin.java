/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filtermaxmin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author charleshenriqueportoferreira
 */
public class FilterMaxMin {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String nomeArquivo = args[0];
        String arquivoModificicado = "";
        try {
            arquivoModificicado = lerArquivo(nomeArquivo);
        } catch (IOException ex) {
            Logger.getLogger(FilterMaxMin.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            printFile(args[1], arquivoModificicado);
        } catch (IOException ex) {
            Logger.getLogger(FilterMaxMin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String lerArquivo(String filePath) throws FileNotFoundException, IOException {
        StringBuilder linha = new StringBuilder();
        try (FileReader fr = new FileReader(filePath); BufferedReader br = new BufferedReader(fr)) {
            while (br.ready()) {
                String linhaLida = br.readLine();
                int min = 16000;
                int max = 20;
                if (!linhaLida.contains("SNO")) {
                    String arrayChars[] = linhaLida.split(",");

                    for (int i = 0; i < arrayChars.length; i++) {
                        try {
                            int numero = Integer.parseInt(arrayChars[i]);
                            if (numero < min) {
                                min = numero;
                            }
                            if (numero > max) {
                                max = numero;
                            }
                        } catch (NumberFormatException e) {
                        }
                    }
                    if (max / min >= 2) {
                        String linhaNormalizada = String.join(",", arrayChars);
                        linha.append(linhaNormalizada);
                        linha.append("\n");
                    }

                } else {
                    linha.append(linhaLida);
                    linha.append("\n");
                }

            }
            br.close();
            fr.close();
        }
        return linha.toString();
    }

    public static void printFile(String fileName, String texto) throws IOException {
        try (FileWriter fw = new FileWriter(fileName); BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(texto);
            bw.newLine();
            bw.close();
            fw.close();
        }
    }

}
