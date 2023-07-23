package salus.well.projetosaluswell.controller;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import salus.well.projetosaluswell.domain.alimento.Alimento;
import salus.well.projetosaluswell.domain.cliente.Cliente;
import salus.well.projetosaluswell.domain.cliente.ClienteCadastro;
import salus.well.projetosaluswell.domain.comorbidade.Comorbidade;
import salus.well.projetosaluswell.domain.pessoa.Pessoa;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ListObj <T> {
    public T[] vetor;
    public int nroElement;


    public ListObj(int tamanho) {
        this.vetor = (T[]) new Object[tamanho];
        this.nroElement = 0;

    }

    public void adiciona(T elemento) {
        if (!(nroElement >= vetor.length)) {
            vetor[nroElement++] = elemento;
        }

    }
    public int getTamanho(){
        return nroElement;
    }

    public void arquivo(T elemento) throws IOException {

        FileWriter arq = new FileWriter("caminho-do-arquvio/nome-do-arquivo.txt");
        PrintWriter gravarArq = new PrintWriter(arq);
        int i;

        gravarArq.printf("+--Resultado--+%n");
        for (i = 1; i <= 10; i++) {
            gravarArq.printf(elemento.toString());
        }
        gravarArq.printf("+-------------+%n");

        arq.close();

    }

    public Object[] ordenarPorCaloria() {

        for (int i = 0; i < this.nroElement - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < this.nroElement; j++) {
                if (((Alimento) this.vetor[j]).getCalorias() < ((Alimento) this.vetor[minIndex]).getCalorias()) {
                    minIndex = j;
                }
            }
            Object temp = this.vetor[i];
            this.vetor[i] = this.vetor[minIndex];
            this.vetor[minIndex] = (T) temp;

        }

        Alimento[] alimentosOrdenados = new Alimento[this.nroElement];
        for (int i = 0; i < this.nroElement; i++) {
            alimentosOrdenados[i] = (Alimento) this.vetor[i];
        }
        return alimentosOrdenados;
    }

    public T buscaBinaria(double busca) {
        int inicio = 0;
        int fim = nroElement - 1;

        while (inicio <= fim) {
            int meio = (inicio + fim) / 2;

            if (((Alimento) this.vetor[meio]).getCalorias() == busca) {
                return vetor[meio];
            } else {
                if (busca < ((Alimento) vetor[meio]).getCalorias()) {
                    fim = meio - 1;
                } else {
                    inicio = meio + 1;
                }
            }
        }
        return null;
    }

    public static void gravaRegistro(String registro, String nomeArq) {
        BufferedWriter saida = null;

        // try-catch para abrir o arquivo
        try {
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        } catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        // try-catch para gravar e fechar o arquivo
        try {
            saida.append(registro + "\n");
            saida.close();
        } catch (IOException erro) {
            System.out.println("Erro ao gravar no arquivo");
        }
    }

    public  void gravaArquivoTxt(List<Cliente> lista, String nomeArq) {
        int contaRegDadosGravados = 0;

        // Monta o registro de header
        String header = "00CLIENTE";
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        header += "01";


        gravaRegistro(header, nomeArq);


        String corpo;
        for (int i = 0; i < lista.size(); i++) {
            Cliente a = lista.get(i);
            corpo = "02";
            corpo += String.format("%03d", a.getId());
            corpo += String.format("%-20.20s", a.getNome());
            corpo += String.format("%-20.20s", a.getEmail());
            corpo += String.format("%-20.20s", a.getEndereco());
            corpo += String.format("%02d", a.getAvatar());
            corpo += String.format("%5b", a.getAtivo());
            gravaRegistro(corpo, nomeArq);
            contaRegDadosGravados++;
        }

        // Monta e grava o registro de trailer
        String trailer = "01";
        trailer += String.format("%010d", contaRegDadosGravados);
        gravaRegistro(trailer, nomeArq);
    }
    public  void gravaArquivoComorbidadeTxt(List<Comorbidade> lista, String nomeArq) {
        int contaRegDadosGravados = 0;

        // Monta o registro de header
        String header = "00COMORBIDADE";
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        header += "01";


        gravaRegistro(header, nomeArq);


        String corpo;
        for (int i = 0; i < lista.size(); i++) {
            Comorbidade a = lista.get(i);
            corpo = "03";
            corpo += String.format("%03d", a.getId());
            corpo += String.format("%5b", a.getDiabete());

            corpo += String.format("%5b", a.getHipertensao());
            corpo += String.format("%5b", a.getColesterol());
            corpo += String.format("%03d", a.getCliente().getId());


            gravaRegistro(corpo, nomeArq);
            contaRegDadosGravados++;
        }

        // Monta e grava o registro de trailer
        String trailer = "01";
        trailer += String.format("%010d", contaRegDadosGravados);
        gravaRegistro(trailer, nomeArq);
    }
    public  void leArquivoComorbidadeTxt(String nomeArq) {
        BufferedReader entrada = null;

        boolean diabete, colesterol, hipertensao;
        String registro, tipoRegistro;
        Long id, clienteId;
        int qtdRegDadosGravados;
        int contaRegDadosLidos = 0;


        List<Comorbidade> listaLida = new ArrayList<>();


        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
        } catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        // try-catch para ler e fechar o arquivo
        System.out.println("\nLendo e processando o arquivo...");
        try {
            // le o 1o registro do arquivo
            registro = entrada.readLine();

            while (registro != null) {

                tipoRegistro = registro.substring(0, 2);
                if (tipoRegistro.equals("00")) {
                    System.out.println("É um registro de header");
                    System.out.println("Tipo de arquivo: " + registro.substring(2, 6));
                    System.out.println("Ano e semestre: " + registro.substring(6, 11));
                    System.out.println("Data e hora de gravação do arquivo: " + registro.substring(11, 30));
                    //System.out.println("Versão do documento: " + registro.substring(30, 32));
                    System.out.println();
                } else if (tipoRegistro.equals("01")) {
                    System.out.println("É um registro de trailer");
                    qtdRegDadosGravados = Integer.parseInt(registro.substring(2, 12));
                    if (qtdRegDadosGravados == contaRegDadosLidos) {
                        System.out.println("Quantidade de registros de dados gravados " +
                                " compatível com quantidade de registros de dados lidos");
                    } else {
                        System.out.println("Quantidade de registros de dados gravados " +
                                " incompatível com quantidade de registros de dados lidos");
                    }
                } else if (tipoRegistro.equals("02")) {
                    System.out.println("É um registro de dados ou de corpo");
                    id = Long.parseLong(registro.substring(2, 6).trim());


                    diabete = Boolean.parseBoolean(registro.substring(5, 10));
                    colesterol = Boolean.parseBoolean(registro.substring(10, 15));
                    hipertensao = Boolean.parseBoolean(registro.substring(15, 20));

                    clienteId = Long.parseLong(registro.substring(20, 23).trim());

                    // Incrementa o contador de registros de dados lidos
                    contaRegDadosLidos++;

                    // Criamos um objeto Aluno com os dados dos atributos lidos do registro
                    Comorbidade a = new Comorbidade(id, diabete, hipertensao, colesterol, clienteId);

                    // Para importar esse dado para o banco de dados
                    // repository.save(a);

                    // No nosso caso, como não estamos conectados a banco de dados,
                    // Vamos adicionar o objeto aluno a uma lista lida
                    listaLida.add(a);

                } else {
                    System.out.println("Tipo de registro inválido");
                }
                // le o próximo registro
                registro = entrada.readLine();
            }
            entrada.close();

        } catch (IOException erro) {
            System.out.println("Erro ao ler o arquivo");
        }

        // Exibição dos dados da lista lida
        System.out.println("\nLista contendo os dados lidos do arquivo:");
        for (Comorbidade a : listaLida) {
            System.out.println(a);
        }


    }

    public  void leArquivoTxt(String nomeArq) {
        BufferedReader entrada = null;
        String registro, tipoRegistro;
        String email, endereco, nome;
        boolean ativo;
        int avatar;
        Long id;
        int qtdRegDadosGravados;
        int contaRegDadosLidos = 0;


        List<Cliente> listaLida = new ArrayList<>();


        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
        } catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        // try-catch para ler e fechar o arquivo
        System.out.println("\nLendo e processando o arquivo...");
        try {
            // le o 1o registro do arquivo
            registro = entrada.readLine();

            while (registro != null) {

                tipoRegistro = registro.substring(0, 2);
                if (tipoRegistro.equals("00")) {
                    System.out.println("É um registro de header");
                    System.out.println("Tipo de arquivo: " + registro.substring(2, 6));
                    System.out.println("Ano: " + registro.substring(6, 11));
                    System.out.println("Data e hora de gravação do arquivo: " + registro.substring(11, 30));
                    //System.out.println("Versão do documento: " + registro.substring(30, 32));
                    System.out.println();
                } else if (tipoRegistro.equals("01")) {
                    System.out.println("É um registro de trailer");
                    qtdRegDadosGravados = Integer.parseInt(registro.substring(2, 12));
                    if (qtdRegDadosGravados == contaRegDadosLidos) {
                        System.out.println("Quantidade de registros de dados gravados " +
                                " compatível com quantidade de registros de dados lidos");
                    } else {
                        System.out.println("Quantidade de registros de dados gravados " +
                                " incompatível com quantidade de registros de dados lidos");
                    }
                } else if (tipoRegistro.equals("02")) {
                    System.out.println("É um registro de dados ou de corpo");
                  //  id = Long.parseLong(registro.substring(2, 6).trim());

                    nome = registro.substring(6, 26).trim();
                    email = registro.substring(26, 46).trim();
                    endereco = registro.substring(46, 65).trim();
                    avatar = Integer.parseInt(registro.substring(66, 67));
                    ativo = Boolean.parseBoolean(registro.substring(68, 72));
                    // Incrementa o contador de registros de dados lidos
                    contaRegDadosLidos++;

                    // Criamos um objeto Aluno com os dados dos atributos lidos do registro
                    //Cliente a = new Cliente(id, nome, email, endereco, avatar, ativo);

                    var a = new Cliente(nome, email, endereco, avatar, ativo);

                    // Para importar esse dado para o banco de dados
                    // repository.save(a);

                    // No nosso caso, como não estamos conectados a banco de dados,
                    // Vamos adicionar o objeto aluno a uma lista lida
                    listaLida.add(a);

                } else {
                    System.out.println("Tipo de registro inválido");
                }
                // le o próximo registro
                registro = entrada.readLine();
            }
            entrada.close();

        } catch (IOException erro) {
            System.out.println("Erro ao ler o arquivo");
        }

        // Exibição dos dados da lista lida
        System.out.println("\nLista contendo os dados lidos do arquivo:");
        for (Cliente a : listaLida) {
            System.out.println(a);
        }


    }
}


