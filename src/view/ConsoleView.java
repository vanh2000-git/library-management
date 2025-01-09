package view;

import controller.AccountController;
import controller.BookController;
import controller.LoanController;
import exception.AccountNotFoundException;
import exception.BookNotFoundException;
import exception.CustomerNotFoundException;
import exception.InvalidInputException;
import exception.LoanNotFoundException;
import model.Account;
import model.Book;
import model.Customer;
import model.DataManager;
import model.Librarian;

import java.util.List;
import java.util.Scanner;

public class ConsoleView {
    private Scanner scanner;
    private AccountController accountController;
    private BookController bookController;
    private LoanController loanController;
    private DataManager dataManager; // Thêm dataManager

    public ConsoleView() {
        scanner = new Scanner(System.in);
        accountController = new AccountController();
        bookController = new BookController();
        loanController = new LoanController();
        dataManager = DataManager.getInstance(); // Khởi tạo dataManager
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
        // ... (code tương tự như đã trình bày trước đó)
    }

    private void displayCustomerMenu() {
        // ... (code tương tự như đã trình bày trước đó)
    }
}