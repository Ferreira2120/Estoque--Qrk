package br.com.ferreira.estoqueQrk.infra.persisten;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionDBImpl implements ConnectionDB{

    private final DataSource dataSource;

    public ConnectionDBImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }
}
