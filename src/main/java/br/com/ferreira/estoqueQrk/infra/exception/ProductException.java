package br.com.ferreira.estoqueQrk.infra.exception;

public class ProductException extends RuntimeException {

    public ProductException(String message) {
        super(message);
    }

    public ProductException(String message, Throwable throwable){
        super(message, throwable);
    }

}
