package br.com.ferreira.estoqueQrk.infra.persisten.repository;

import br.com.ferreira.estoqueQrk.domain.enums.Measurements;
import br.com.ferreira.estoqueQrk.domain.enums.TypeProd;
import br.com.ferreira.estoqueQrk.domain.model.Product;
import br.com.ferreira.estoqueQrk.domain.repository.ProductRepository;
import br.com.ferreira.estoqueQrk.infra.exception.InfraException;
import br.com.ferreira.estoqueQrk.infra.exception.ProductException;
import br.com.ferreira.estoqueQrk.infra.persisten.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
                INSET INTO estoque (name_product, price_product, 
                      type_product, measurements, product_code) VALUES (?, ?, ?, ?, ?);
                """;

        try(Connection conn = this.connectionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setString(3, product.getType().name());
            stmt.setString(4, product.getMeasurements().name());
            stmt.setInt(5, product.getProduct_code());

            int affectedLines = stmt.executeUpdate();
            if (affectedLines == 0){
                throw new InfraException("Error creating product! Please check the information to proceed.");
            }

            Product product1 = new Product(
                    product.getName(),
                    product.getPrice(),
                    product.getType(),
                    product.getMeasurements(),
                    product.getProduct_code());
            return product1;
        }catch (SQLException e){
            throw new ProductException("Error creating product", e);
        }
    }

    @Override
    public Product selectProductById(Long id) {

        String sql = """
                    SELECT * FROM estoque WHERE id_product = ?;
                    """;

        try(Connection conn = this.connectionDB.getConnection(); PreparedStatement
                stmt = conn.prepareStatement(sql)){

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()){

                TypeProd typeProd = TypeProd.valueOf(rs.getString("type_product"));
                Measurements measurements = Measurements.valueOf(rs.getString("measurements"));

                Product product = new Product(
                        rs.getLong("id_product"),
                        rs.getString("name_product"),
                        rs.getDouble("price_product"),
                        typeProd,
                        measurements,
                        rs.getInt("product_code")
                );
                return product;
            }else {
                throw new InfraException("We were unable to find the product " +
                        "using the ID! Please check the information to continue.");
            }
        }catch (SQLException e){
            throw new ProductException("The product could not be found.", e);
        }

    }

    @Override
    public Product selectProductByCode(int code) {
        String sql = """
                SELECT * FROM estoque WHERE product_code = ?;
                """;
        try(Connection conn = this.connectionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, code);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()){

                TypeProd typeProd = TypeProd.valueOf(rs.getString("type_product"));
                Measurements measurements = Measurements.valueOf(rs.getString("measurements"));

                Product product = new Product(
                        rs.getLong("id_product"),
                        rs.getString("name_product"),
                        rs.getDouble("price_product"),
                        typeProd,
                        measurements,
                        rs.getInt("product_code")
                );
                return product;
            }else {
                throw new InfraException("We were unable to find the product " +
                        "using the product code! Please check the information to continue.");
            }

        }catch (SQLException e){
            throw new ProductException("The product could not be found.", e);
        }
    }

    @Override
    public Product updateProduct(Product product) {
         String sql = """
                 UPDATE estoque SET name_product = ?, price_product = ?, type_product = ?, measurements = ?, product_code = ? WHERE id_products = ?
                 """;

         try(Connection conn = this.connectionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){

             stmt.setString(1, product.getName());
             stmt.setDouble(2, product.getPrice());
             stmt.setString(3, product.getType().name());
             stmt.setString(4, product.getMeasurements().name());
             stmt.setInt(5, product.getProduct_code());
             stmt.setLong(6, product.getId());

             int affectedLines = stmt.executeUpdate();
             if (affectedLines == 0){
                 throw new InfraException("Error updating product! Please check the information to be processed.");
             }

             return product;
         }catch (SQLException e){
             throw new ProductException("The operation cannot be performed.", e);
         }

    }

    @Override
    public void deleteProduct(Long id) {
        String sql = """
                DELETE FROM estoque WHERE id_product = ?;
                """;

        try(Connection conn = this.connectionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setLong(1, id);

            int affectedLines = stmt.executeUpdate();
            if (affectedLines == 0){
                throw new InfraException("Error deleting product! Please check the information to be processed.");
            }
        }catch (SQLException e){
            throw new ProductException("The operation cannot be performed.", e);
        }

    }

    @Override
    public List<Product> selectAllProducts() {

        String sql = """
                SELECT * FROM estoque;
                """;

        try(Connection conn = this.connectionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){

            ResultSet rs = stmt.executeQuery();
            List<Product> products = new java.util.ArrayList<>();

            while (rs.next()){
                TypeProd typeProd = TypeProd.valueOf(rs.getString("type_product"));
                Measurements measurements = Measurements.valueOf(rs.getString("measurements"));

                Product product = new Product(
                        rs.getLong("id_product"),
                        rs.getString("name_product"),
                        rs.getDouble("price_product"),
                        typeProd,
                        measurements,
                        rs.getInt("product_code")
                );
                products.add(product);
            }
            return products;
        }catch (SQLException e){
            throw new ProductException("The operation cannot be performed.", e);
        }
    }

    @Override
    public List<Product> selectProductsByType(String type) {
        String sql = """
                SELECT * FROM estoque WHERE type_product = ?;
                """;

        try(Connection conn = this.connectionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, type);

            ResultSet rs = stmt.executeQuery();
            List<Product> products = new java.util.ArrayList<>();

            while (rs.next()){
                TypeProd typeProd = TypeProd.valueOf(rs.getString("type_product"));
                Measurements measurements = Measurements.valueOf(rs.getString("measurements"));

                Product product = new Product(
                        rs.getLong("id_product"),
                        rs.getString("name_product"),
                        rs.getDouble("price_product"),
                        typeProd,
                        measurements,
                        rs.getInt("product_code")
                );
                products.add(product);
            }
            return products;
        }catch (SQLException e) {
            throw new ProductException("The operation cannot be performed.", e);
        }

    }

    @Override
    public List<Product> selectProductsByMeasurement(String measurement) {
        String sql = """
                SELECT * FROM estoque WHERE measurements = ?;
                """;
        try(Connection conn = this.connectionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, measurement);

            ResultSet rs = stmt.executeQuery();
            List<Product> products = new java.util.ArrayList<>();

            while (rs.next()){
                TypeProd typeProd = TypeProd.valueOf(rs.getString("type_product"));
                Measurements measurements = Measurements.valueOf(rs.getString("measurements"));

                Product product = new Product(
                        rs.getLong("id_product"),
                        rs.getString("name_product"),
                        rs.getDouble("price_product"),
                        typeProd,
                        measurements,
                        rs.getInt("product_code")
                );
                products.add(product);
            }
            return products;
        }catch (SQLException e) {
            throw new ProductException("The operation cannot be performed.", e);
        }
    }
}
