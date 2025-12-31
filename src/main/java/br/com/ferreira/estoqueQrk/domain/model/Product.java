package br.com.ferreira.estoqueQrk.domain.model;

import br.com.ferreira.estoqueQrk.domain.enums.Measurements;
import br.com.ferreira.estoqueQrk.domain.enums.TypeProd;

public class Product {

    private Long id;

    private String name;

    private Double price;

    private TypeProd type;

    private Measurements measurements;

    private int product_code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeProd getType() {
        return type;
    }

    public void setType(TypeProd type) {
        this.type = type;
    }

    public Measurements getMeasurements() {
        return measurements;
    }

    public void setMeasurements(Measurements measurements) {
        this.measurements = measurements;
    }

    public int getProduct_code() {
        return product_code;
    }

    public void setProduct_code(int product_code) {
        this.product_code = product_code;
    }
}
