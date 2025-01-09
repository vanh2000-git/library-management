package controller;

import exception.AccountNotFoundException;
import exception.InvalidInputException;
import model.Account;
import model.AccountFactory;
import model.DataManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountController {
    private DataManager dataManager;
    private AccountFactory accountFactory;
    private Account currentAccount; // Biến lưu trữ tài khoản hiện tại

    public AccountController() {
        this.dataManager = DataManager.getInstance();
        this.accountFactory = new AccountFactory();
    }

    public void createAccount(String username, String password, String name, String email, String type)
            throws InvalidInputException {
        // Validate input using regex
        if (!isValidUsername(username)) {
            throw new InvalidInputException("Invalid username format. Username must be 5-20 alphanumeric characters.");
        }
        if (!isValidPassword(password)) {
            throw new InvalidInputException("Invalid password format. Password must be at least 8 characters, " +
                    "containing at least one uppercase letter, one lowercase letter, one number and one special character.");
        }
        // ... (validate other inputs)

        Account account = accountFactory.createAccount(type, username, password, name, email);
        dataManager.addAccount(account);
        System.out.println("Tạo tài khoản thành công!");
    }

    public boolean login(String username, String password) {
        Account account = dataManager.findAccount(username);
        if (account != null && account.getPassword().equals(password)) {
            currentAccount = account; // Lưu trữ thông tin người dùng hiện tại
            return true;
        } else {
            return false;
        }
    }

    public void updateAccount(String username, String newPassword, String newName, String newEmail)
            throws AccountNotFoundException, InvalidInputException {
        Account account = dataManager.findAccount(username);
        if (account == null) {
            throw new AccountNotFoundException("Không tìm thấy tài khoản có username " + username);
        }

        // Validate input
        if (newPassword != null && !isValidPassword(newPassword)) {
            throw new InvalidInputException("Invalid password format. Password must be at least 8 characters, " +
                    "containing at least one uppercase letter, one lowercase letter, one number and one special character.");
        }
        // ... (validate other inputs)

        if (newPassword != null) {
            account.setPassword(newPassword);
        }
        if (newName != null) {
            account.setName(newName);
        }
        if (newEmail != null) {
            account.setEmail(newEmail);
        }

        dataManager.saveAccounts(); // Lưu thay đổi vào file
        System.out.println("Cập nhật tài khoản thành công!");
    }

    public void deleteAccount(String username) throws AccountNotFoundException {
        Account account = dataManager.findAccount(username);
        if (account == null) {
            throw new AccountNotFoundException("Không tìm thấy tài khoản có username " + username);
        }

        dataManager.getAccounts().remove(username);
        dataManager.saveAccounts(); // Lưu thay đổi vào file
        System.out.println("Xóa tài khoản thành công!");
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

    public Account getCurrentAccount() {
        return currentAccount;
    }
}