/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoescola.modelo.arquivo;

import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.Integer.parseInt;
import java.util.Scanner;

/**
 *
 * @author felipe
 */
 public abstract class  Arquivo {

    protected final int autoIncremento(String tabela) {
        int numLinhas = 1;
        //abre um arquivo e cria um file
        File arquivoCSV = new File(tabela);

        try {

            //cria um scanner para ler o arquivo
            Scanner leitor = new Scanner(arquivoCSV);

            //variavel que armazenara as linhas do arquivo
            String linhasDoArquivo = null;

            //ignora a primeira linha do arquivo
            leitor.nextLine();
            //percorre todo o arquivo
            while (leitor.hasNext()) {
                //recebe cada linha do arquivo
                linhasDoArquivo = leitor.nextLine();

                //separa os campos entre as virgulas de cada linha
                //imprime a coluna que quiser
            }
            if (linhasDoArquivo != null) {
                String[] valoresEntreVirgulas = linhasDoArquivo.split(",");
                if (valoresEntreVirgulas[0] != null) {
                    numLinhas += parseInt(valoresEntreVirgulas[0]);
                }
            }
        } catch (FileNotFoundException e) {
            return 0;
        }
        return numLinhas;
    }

}
