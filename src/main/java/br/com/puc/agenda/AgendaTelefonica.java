package br.com.puc.agenda;

import br.com.puc.agenda.dao.ContatoDAO;
import br.com.puc.agenda.modelo.Contato;

import java.sql.SQLException;
import java.util.List;

/**
 * Classe de gerenciamento exigida pelo enunciado.
 * Mantem as assinaturas dos metodos pedidos e delega a persistencia ao
 * {@link ContatoDAO}, garantindo separacao de responsabilidades.
 */
public class AgendaTelefonica {

    private final ContatoDAO dao;

    public AgendaTelefonica() {
        this(new ContatoDAO());
    }

    public AgendaTelefonica(ContatoDAO dao) {
        this.dao = dao;
    }

    public void adicionarContato(Contato contato) throws SQLException {
        dao.inserir(contato);
    }

    public boolean removerContato(String nome) throws SQLException {
        return dao.removerPorNome(nome);
    }

    public Contato buscarContato(String nome) throws SQLException {
        return dao.buscarPorNome(nome);
    }

    public List<Contato> listarContatos() throws SQLException {
        return dao.listarTodos();
    }

    /**
     * Operacao adicional para fechar o U do CRUD exigido no objetivo do trabalho.
     * Atualiza telefone e e-mail do contato cujo nome foi informado.
     */
    public boolean atualizarContato(Contato contato) throws SQLException {
        return dao.atualizar(contato);
    }
}
