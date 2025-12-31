package br.com.ferreira.estoqueQrk.infra.persisten;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionDB {
    Connection getConnection() throws SQLException;
}
