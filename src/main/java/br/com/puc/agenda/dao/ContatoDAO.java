package br.com.puc.agenda.dao;

import br.com.puc.agenda.modelo.Contato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) responsavel pelas operacoes CRUD da entidade Contato
 * no banco MySQL. Toda interacao com JDBC e isolada nesta classe.
 */
public class ContatoDAO {

    public void inserir(Contato contato) throws SQLException {
        String sql = "INSERT INTO contato (nome, telefone, email) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoBD.obter();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, contato.getNome());
            ps.setString(2, contato.getTelefone());
            ps.setString(3, contato.getEmail());
            ps.executeUpdate();

            try (ResultSet chaves = ps.getGeneratedKeys()) {
                if (chaves.next()) {
                    contato.setId(chaves.getInt(1));
                }
            }
        }
    }

    public Contato buscarPorNome(String nome) throws SQLException {
        String sql = "SELECT id, nome, telefone, email FROM contato WHERE nome = ?";

        try (Connection conn = ConexaoBD.obter();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nome);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        }
        return null;
    }

    public List<Contato> listarTodos() throws SQLException {
        String sql = "SELECT id, nome, telefone, email FROM contato ORDER BY nome";
        List<Contato> contatos = new ArrayList<>();

        try (Connection conn = ConexaoBD.obter();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                contatos.add(mapear(rs));
            }
        }
        return contatos;
    }

    /**
     * Atualiza telefone e e-mail do contato identificado pelo nome.
     * Retorna true se algum registro foi alterado.
     */
    public boolean atualizar(Contato contato) throws SQLException {
        String sql = "UPDATE contato SET telefone = ?, email = ? WHERE nome = ?";

        try (Connection conn = ConexaoBD.obter();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, contato.getTelefone());
            ps.setString(2, contato.getEmail());
            ps.setString(3, contato.getNome());

            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Remove o contato cujo nome corresponda exatamente ao informado.
     * Retorna true se o contato existia e foi removido.
     */
    public boolean removerPorNome(String nome) throws SQLException {
        String sql = "DELETE FROM contato WHERE nome = ?";

        try (Connection conn = ConexaoBD.obter();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nome);
            return ps.executeUpdate() > 0;
        }
    }

    private Contato mapear(ResultSet rs) throws SQLException {
        return new Contato(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("telefone"),
                rs.getString("email"));
    }
}
