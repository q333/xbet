package by.academy.it.dao;

public class DAOException extends Exception {

    public DAOException(String message, Exception e) {
        super(message, e);
    }

    public DAOException(String message) {
        super(message);
    }
}
