package br.com.ferreira.estoqueQrk.domain.repository;

import br.com.ferreira.estoqueQrk.domain.model.Product;

import java.util.List;

public interface ProductRepository {
    Product createProduct(Product product);
    Product selectProductById(Long id);
    Product selectProductByCode(int code);
    Product updateProduct(Product product);
    void deleteProduct(Long id);
    List<Product> selectAllProducts();
    List<Product> selectProductsByType(String type);
    List<Product> selectProductsByMeasurement(String measurement);
}
