package br.com.ferreira.estoqueQrk.domain.exception;

public class EntityExcption extends RuntimeException {
    public EntityExcption(String message) {
        super(message);
    }

    public EntityExcption(String message, Throwable excption){
        super(message, excption);
    }
}
