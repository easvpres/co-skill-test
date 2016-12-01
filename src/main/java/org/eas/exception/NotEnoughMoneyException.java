package org.eas.exception;

/**
 * @author eas
 */
public class NotEnoughMoneyException extends Exception {
    private String userNumber;

    public NotEnoughMoneyException(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getUserNumber() {
        return userNumber;
    }
}
