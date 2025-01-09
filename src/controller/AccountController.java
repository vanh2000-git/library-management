package controller;

import exception.InvalidInputException;
import model.Account;
import model.AccountFactory;
import model.DataManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountController {
    private DataManager dataManager;
    private AccountFactory accountFactory;

    public AccountController() {
        this.dataManager = DataManager.getInstance();
        this.accountFactory = new AccountFactory();
    }

    public void createAccount(String username, String password, String name, String email, String type)
            throws InvalidInputException {
        // Validate input using regex
        if (!isValidUsername(username)) {
            throw new InvalidInputException("Invalid username format.");
        }
        if (!isValidPassword(password)) {
            throw new InvalidInputException("Invalid password format.");
        }
        // ... (validate other inputs)

        Account account = accountFactory.createAccount(type, username, password, name, email);
        dataManager.addAccount(account);
        System.out.println("Tạo tài khoản thành công!");
    }

    private boolean isValidUsername(String username) {
        // Regex pattern for username validation
        String regex = "^[a-zA-Z0-9]{5,20}$"; // Example: username must be 5-20 alphanumeric characters
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    private boolean isValidPassword(String password) {
        // Regex pattern for password validation
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        // Example: password must be at least 8 characters,
        // containing at least one uppercase letter, one lowercase letter, one number and one special character
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public boolean login(String username, String password) {
        // ... (code to check if username and password match an account)
        Account account = dataManager.findAccount(username);
        if (account == null && account.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    // Other methods for account management (update, delete, ...)
}