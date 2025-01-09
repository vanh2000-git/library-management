package model;

public class AccountFactory {
    public Account createAccount(String type, String username, String password, String name, String email) {
        if (type.equalsIgnoreCase("librarian")) {
            return new Librarian(username, password, name, email);
        } else if (type.equalsIgnoreCase("customer")) {
            return new Customer(username, password, name, email);
        } else {
            return null; // Or throw an exception
        }
    }
}