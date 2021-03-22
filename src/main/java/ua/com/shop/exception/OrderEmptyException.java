package ua.com.shop.exception;

public class OrderEmptyException extends RuntimeException {

    public OrderEmptyException() {
    }

    public OrderEmptyException(String message) {
        super(message);
    }
}
