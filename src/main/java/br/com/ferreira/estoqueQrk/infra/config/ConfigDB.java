package br.com.ferreira.estoqueQrk.infra.config;

import br.com.ferreira.estoqueQrk.infra.persisten.ConnectionDB;
import br.com.ferreira.estoqueQrk.infra.persisten.ConnectionDBImpl;
import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ConfigDB {

    @ApplicationScoped
    public ConnectionDB connectionDB(AgroalDataSource agroalDataSource){
        return new ConnectionDBImpl(agroalDataSource);
    }

}
