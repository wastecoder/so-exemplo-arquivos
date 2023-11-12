package controller;

import java.io.IOException;

public interface ArquivoInterface {
    void lerDiretorio(String caminho) throws IOException;
    void criarArquivo(String caminho, String nome) throws IOException;
    void lerArquivo(String caminho, String nome) throws IOException;
    void abrirArquivo(String caminho, String nome) throws IOException;
}
