package br.com.ferreira.estoqueQrk.infra.exception;

public class InfraException extends RuntimeException {
    public InfraException(String message) {
        super(message);
    }
    public InfraException(String message, Throwable throwable){super(message, throwable);}
}
