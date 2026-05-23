package br.com.puc.agenda.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Utilitario responsavel por obter conexoes JDBC com o MySQL.
 * As credenciais sao lidas do arquivo classpath:db.properties para evitar
 * fixa-las no codigo-fonte.
 */
public final class ConexaoBD {

    private static final String ARQUIVO_PROPRIEDADES = "/db.properties";

    private static String url;
    private static String usuario;
    private static String senha;

    static {
        carregarPropriedades();
    }

    private ConexaoBD() {
    }

    private static void carregarPropriedades() {
        try (InputStream in = ConexaoBD.class.getResourceAsStream(ARQUIVO_PROPRIEDADES)) {
            if (in == null) {
                throw new IllegalStateException(
                        "Arquivo db.properties nao encontrado no classpath.");
            }
            Properties props = new Properties();
            props.load(in);

            url = props.getProperty("db.url");
            usuario = props.getProperty("db.user");
            senha = props.getProperty("db.password");

            if (url == null || usuario == null || senha == null) {
                throw new IllegalStateException(
                        "db.properties precisa conter db.url, db.user e db.password.");
            }
        } catch (IOException e) {
            throw new IllegalStateException(
                    "Falha ao ler db.properties: " + e.getMessage(), e);
        }
    }

    public static Connection obter() throws SQLException {
        return DriverManager.getConnection(url, usuario, senha);
    }
}
