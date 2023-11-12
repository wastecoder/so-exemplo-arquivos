package controller;

import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class ArquivosController implements ArquivoInterface {
    @Override
    public void lerDiretorio(String caminho) throws IOException {
        File diretorio = new File(caminho);

        validarArquivoOuDiretorio(diretorio, "Diretorio");

        File[] conteudo = diretorio.listFiles(); //conteudo = arquivos e diretorios
        for (File atual : conteudo) {
            if (atual.isFile()) {
                System.out.println("     \t" + atual.getName());
            } else {
                System.out.println("<DIR>\t" + atual.getName());
            }
        }
    }

    @Override
    public void criarArquivo(String caminho, String nome) throws IOException {
        File diretorio = new File(caminho);
        File arquivo = new File(caminho, nome);

        validarArquivoOuDiretorio(diretorio, "Diretorio ou arquivo");

        boolean arquivoExiste = false;
        if (arquivo.exists()) { //Se o arquivo existir, nao ira sobrescrever os dados
            arquivoExiste = true;
        }

        String conteudo = gerarConteudo();
        FileWriter fileWriter = new FileWriter(arquivo, arquivoExiste);
        PrintWriter gravar = new PrintWriter(fileWriter);

        gravar.write(conteudo);
        gravar.flush();
        gravar.close();
        fileWriter.close();
    }

    private String gerarConteudo() {
        Scanner leia = new Scanner(System.in);
        StringBuffer conteudo = new StringBuffer();
        String linha = "";

        while (!linha.equalsIgnoreCase("fim")) {
            System.out.println(">> Digite frases para o arquivo - [fim] para sair");
            linha = leia.nextLine();

            if (!linha.equalsIgnoreCase("fim")) {
                conteudo.append(linha).append("\r\n");
            }
        }

        return conteudo.toString();
    }

    @Override
    public void lerArquivo(String caminho, String nome) throws IOException {
        File arquivo = new File(caminho, nome);

        validarArquivoOuDiretorio(arquivo, "Diretorio ou arquivo");

        FileInputStream fluxo = new FileInputStream(arquivo);
        InputStreamReader leitor = new InputStreamReader(fluxo);
        BufferedReader buffer = new BufferedReader(leitor);

        String linha = buffer.readLine();
        while (linha != null) { //Para ao chegar no final do arquivo - EOF
            System.out.println(linha);
            linha = buffer.readLine();
        }

        buffer.close();
        leitor.close();
        fluxo.close();
    }

    @Override
    public void abrirArquivo(String caminho, String nome) throws IOException {
        File arquivo = new File(caminho, nome);

        validarArquivoOuDiretorio(arquivo, "Diretorio ou arquivo");

        Desktop desktop = Desktop.getDesktop();
        desktop.open(arquivo);
    }


    private void validarArquivoOuDiretorio(File file, String tipo) throws IOException {
        if (invalido(file)) {
            throw new IOException(tipo + " inexistente ou nao encontrado!");
        }
    }

    private boolean invalido(File file) {
        return !(file.exists() && (file.isFile() || file.isDirectory()));
    }
}
