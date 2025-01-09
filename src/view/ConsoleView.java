package view;

import controller.AccountController;
import controller.BookController;
import controller.LoanController;
import exception.BookNotFoundException;
import exception.CustomerNotFoundException;
import exception.InvalidInputException;
import model.Account;
import model.Librarian;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Scanner;

public class ConsoleView {
    private Scanner scanner;
    private AccountController accountController;
    private BookController bookController;
    private LoanController loanController;

    public ConsoleView() {
        scanner = new Scanner(System.in);
        accountController = new AccountController();
        bookController = new BookController();
        loanController = new LoanController();
    }

    public void run() {
        while (true) {
            displayLoginMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Login logic
                    System.out.print("Nhập username: ");
                    String username = scanner.nextLine();
                    System.out.print("Nhập password: ");
                    String password = scanner.nextLine();
                    if (accountController.login(username, password)) {
                        Account currentAccount = accountController.getCurrentAccount();
                        if (currentAccount instanceof Librarian) {
                            displayLibrarianMenu();
                        } else {
                            displayCustomerMenu();
                        }
                    } else {
                        System.out.println("Đăng nhập thất bại!");
                    }
                    break;
                case 2:
                    System.exit(0);
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private void displayLoginMenu() {
        System.out.println("****************************************");
        System.out.println("*     HỆ THỐNG QUẢN LÝ THƯ VIỆN     *");
        System.out.println("****************************************");
        System.out.println("*                                      *");
        System.out.println("*     1. Đăng nhập                    *");
        System.out.println("*     2. Thoát                        *");
        System.out.println("*                                      *");
        System.out.println("****************************************");
        System.out.print("Nhập lựa chọn: ");
    }

    private void displayLibrarianMenu() {
        // ... (code to display librarian menu and handle choices)
        while (true) {
            System.out.println("****************************************");
            System.out.println("*     HỆ THỐNG QUẢN LÝ THƯ VIỆN     *");
            System.out.println("****************************************");
            System.out.println("* Xin chào [Tên thủ thư]!              *"); // Thay [Tên thủ thư] bằng tên thật
            System.out.println("*     1. Quản lý tài khoản            *");
            System.out.println("*     2. Quản lý sách                *");
            System.out.println("*     3. Cho mượn sách              *");
            System.out.println("*     4. Nhận trả sách                *");
            System.out.println("*     5. Đăng xuất                   *");
            System.out.println("****************************************");
            System.out.print("Nhập lựa chọn: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Quản lý tài khoản
                    System.out.println("1. Thêm tài khoản");
                    System.out.println("2. Sửa tài khoản");
                    System.out.println("3. Xóa tài khoản");
                    System.out.println("4. Quay lại");
                    System.out.print("Nhập lựa chọn: ");
                    int accountChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (accountChoice) {
                        case 1:
                            // Thêm tài khoản
                            try {
                                // ... (lấy thông tin tài khoản từ người dùng)
                                accountController.createAccount(username, password, name, email, type);
                            } catch (InvalidInputException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 2:
                            // Sửa tài khoản
                            try {
                                // ... (lấy thông tin tài khoản từ người dùng)
                                accountController.updateAccount(username, newPassword, newName, newEmail);
                            } catch (AccountNotFoundException | InvalidInputException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 3:
                            // Xóa tài khoản
                            try {
                                // ... (lấy username từ người dùng)
                                accountController.deleteAccount(username);
                            } catch (AccountNotFoundException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 4:
                            break; // Quay lại menu chính
                        default:
                            System.out.println("Lựa chọn không hợp lệ!");
                    }
                    break;
            }

        }
    }

    private void displayCustomerMenu() {
        // ... (code to display customer menu and handle choices)
    }
}