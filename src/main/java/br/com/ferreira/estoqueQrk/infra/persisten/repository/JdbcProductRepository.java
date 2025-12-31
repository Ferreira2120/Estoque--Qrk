package br.com.ferreira.estoqueQrk.infra.persisten.repository;

import br.com.ferreira.estoqueQrk.domain.enums.TypeProd;
import br.com.ferreira.estoqueQrk.domain.model.Product;
import br.com.ferreira.estoqueQrk.domain.repository.ProductRepository;
import br.com.ferreira.estoqueQrk.infra.exception.InfraException;
import br.com.ferreira.estoqueQrk.infra.exception.ProductException;
import br.com.ferreira.estoqueQrk.infra.persisten.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class JdbcProductRepository implements ProductRepository {

    private final ConnectionDB connectionDB;

    public JdbcProductRepository(ConnectionDB connectionDB) {
        this.connectionDB = connectionDB;
    }

    @Override
    public Product createProduct(Product product) {

        String sql = """
                INSET INTO estoque (name_product, price_product, type_product, measurements, product_code) VALUES (?, ?, ?, ?, ?);
                """;

        try(Connection conn = this.connectionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setString(3, product.getType().name());
            stmt.setString(4, product.getMeasurements().name());
            stmt.setInt(5, product.getProduct_code());

            int lineAffect = stmt.executeUpdate();
            if (lineAffect == 0){
                throw new InfraException("Error creating product! Please check the information to proceed.");
            }
            return product;
        }catch (SQLException e){
            throw new ProductException("Error creating product", e);
        }
    }

    @Override
    public Product selectProductById(Long id) {
        return null;
    }

    @Override
    public Product selectProductByCode(int code) {
        return null;
    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }

    @Override
    public List<Product> selectAllProducts() {
        return List.of();
    }

    @Override
    public List<Product> selectProductsByType(String type) {
        return List.of();
    }

    @Override
    public List<Product> selectProductsByMeasurement(String measurement) {
        return List.of();
    }
}
