package view;

import controller.ArquivoInterface;
import controller.ArquivosController;

import java.io.IOException;
import java.util.Scanner;

public class Executar {
    public static void main(String[] args) {
        ArquivoInterface arquivo = new ArquivosController();
        String nome, caminho;
        Scanner leia = new Scanner(System.in);
        int escolha;

        do {
            System.out.println("""
                    \n+---------------------------------+
                    |     >>> MENU DE ARQUIVO <<<     |
                    +---------------------------------+
                    | 1) Listar conteudo de uma pasta |
                    | 2) Criar um arquivo             |
                    | 3) Ler um arquivo               |
                    | 4) Abrir um arquivo existente   |
                    | 9) Finalizar o programa         |
                    +---------------------------------+""");

            System.out.print(">> Escolha: ");
            escolha = leia.nextInt();


            leia.nextLine(); //Consome o \n (da tecla Enter) da entrada da variavel escolha

            switch (escolha) {
                case 1:
                    System.out.println("\n>> Exemplo de caminho: C:\\Windows");

                    System.out.print(">> Digite o caminho da pasta: ");
                    caminho = leia.nextLine();

                    try {
                        arquivo.lerDiretorio(caminho);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case 2, 3, 4:
                    System.out.println("\n>> Exemplo de caminho: C:\\Windows");
                    System.out.println(">> Exemplo de nome: alunos.txt");

                    System.out.print(">> Digite o caminho da pasta: ");
                    caminho = leia.nextLine();

                    System.out.print(">> Digite o nome e extensao do arquivo: ");
                    nome = leia.nextLine();

                    try {
                        if (escolha == 2)
                            arquivo.criarArquivo(caminho, nome);
                        else if (escolha == 3)
                            arquivo.lerArquivo(caminho, nome);
                        else
                            arquivo.abrirArquivo(caminho, nome);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case 9:
                    System.out.println("PROGRAMA FINALIZADO!");
                    break;

                default:
                    System.out.println("Escolha invalida! Tente novamente.");
            }

        } while (escolha != 9);
    }
}
