package br.com.puc.agenda;

import br.com.puc.agenda.modelo.Contato;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * Classe principal que oferece um menu interativo no console para o usuario
 * exercitar todas as operacoes CRUD da agenda telefonica.
 *
 * O menu foi estendido em uma opcao em relacao ao item III do enunciado para
 * contemplar tambem a operacao de Update exigida no objetivo geral (CRUD completo).
 */
public class AgendaTeste {

    private static final Scanner SC = new Scanner(System.in);
    private static final AgendaTelefonica AGENDA = new AgendaTelefonica();

    public static void main(String[] args) {
        System.out.println("====================================");
        System.out.println("    AGENDA TELEFONICA - PUC");
        System.out.println("====================================");

        boolean executando = true;
        while (executando) {
            exibirMenu();
            int opcao = lerInteiro("Escolha uma opcao: ");

            try {
                switch (opcao) {
                    case 1 -> adicionar();
                    case 2 -> remover();
                    case 3 -> buscar();
                    case 4 -> listar();
                    case 5 -> atualizar();
                    case 6 -> {
                        executando = false;
                        System.out.println("Encerrando a aplicacao. Ate logo!");
                    }
                    default -> System.out.println("Opcao invalida. Tente novamente.");
                }
            } catch (SQLException e) {
                System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
            }
        }

        SC.close();
    }

    private static void exibirMenu() {
        System.out.println();
        System.out.println("--------- MENU ---------");
        System.out.println("1 - Adicionar novo contato");
        System.out.println("2 - Remover contato");
        System.out.println("3 - Buscar contato pelo nome");
        System.out.println("4 - Listar todos os contatos");
        System.out.println("5 - Atualizar contato");
        System.out.println("6 - Sair");
        System.out.println("------------------------");
    }

    private static void adicionar() throws SQLException {
        System.out.println("\n>>> Adicionar novo contato");
        String nome = lerTexto("Nome: ");
        String telefone = lerTexto("Telefone: ");
        String email = lerTexto("E-mail: ");

        Contato existente = AGENDA.buscarContato(nome);
        if (existente != null) {
            System.out.println("Ja existe um contato com esse nome. Use a opcao 5 para atualizar.");
            return;
        }

        Contato novo = new Contato(nome, telefone, email);
        AGENDA.adicionarContato(novo);
        System.out.println("Contato adicionado com sucesso! " + novo);
    }

    private static void remover() throws SQLException {
        System.out.println("\n>>> Remover contato");
        String nome = lerTexto("Nome do contato a remover: ");

        boolean removido = AGENDA.removerContato(nome);
        if (removido) {
            System.out.println("Contato removido com sucesso.");
        } else {
            System.out.println("Nenhum contato encontrado com esse nome.");
        }
    }

    private static void buscar() throws SQLException {
        System.out.println("\n>>> Buscar contato");
        String nome = lerTexto("Nome do contato: ");

        Contato c = AGENDA.buscarContato(nome);
        if (c != null) {
            System.out.println("Contato encontrado: " + c);
        } else {
            System.out.println("Nenhum contato encontrado com esse nome.");
        }
    }

    private static void listar() throws SQLException {
        System.out.println("\n>>> Lista de contatos");
        List<Contato> contatos = AGENDA.listarContatos();

        if (contatos.isEmpty()) {
            System.out.println("A agenda esta vazia.");
            return;
        }

        for (Contato c : contatos) {
            System.out.println(c);
        }
        System.out.println("Total: " + contatos.size() + " contato(s).");
    }

    private static void atualizar() throws SQLException {
        System.out.println("\n>>> Atualizar contato");
        String nome = lerTexto("Nome do contato a atualizar: ");

        Contato atual = AGENDA.buscarContato(nome);
        if (atual == null) {
            System.out.println("Nenhum contato encontrado com esse nome.");
            return;
        }

        System.out.println("Dados atuais: " + atual);
        String novoTelefone = lerTexto("Novo telefone: ");
        String novoEmail = lerTexto("Novo e-mail: ");

        atual.setTelefone(novoTelefone);
        atual.setEmail(novoEmail);

        boolean atualizado = AGENDA.atualizarContato(atual);
        if (atualizado) {
            System.out.println("Contato atualizado com sucesso! " + atual);
        } else {
            System.out.println("Nao foi possivel atualizar o contato.");
        }
    }

    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return SC.nextLine().trim();
    }

    private static int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        String linha = SC.nextLine().trim();
        try {
            return Integer.parseInt(linha);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
