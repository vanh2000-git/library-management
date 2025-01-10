package view;

import controller.AccountController;
import controller.BookController;
import controller.LoanController;
import exception.AccountNotFoundException;
import exception.BookNotFoundException;
import exception.CustomerNotFoundException;
import exception.InvalidInputException;
import exception.LoanNotFoundException;
import model.*;

import java.util.List;
import java.util.Scanner;

public class ConsoleView {
    private Scanner scanner;
    private AccountController accountController;
    private BookController bookController;
    private LoanController loanController;
    private DataManager dataManager;

    public ConsoleView() {
        scanner = new Scanner(System.in);
        accountController = new AccountController();
        bookController = new BookController();
        loanController = new LoanController();
        dataManager = DataManager.getInstance();
    }

    public void run() {
        if (dataManager.getAccounts().isEmpty()) {
            try {
                accountController.createAccount("admin", "Admin@123", "Admin", "admin@example.com", "librarian");
            } catch (InvalidInputException e) {
                System.err.println("Lỗi khi tạo tài khoản admin mặc định: " + e.getMessage());
            }
        }

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
        while (true) {
            System.out.println("****************************************");
            System.out.println("*     HỆ THỐNG QUẢN LÝ THƯ VIỆN     *");
            System.out.println("****************************************");
            System.out.println("* Xin chào " + accountController.getCurrentAccount().getName() + "!              *");
            System.out.println("*     1. Quản lý tài khoản            *");
            System.out.println("*     2. Quản lý sách                *");
            System.out.println("*     3. Cho mượn sách              *");
            System.out.println("*     4. Nhận trả sách                *");
            System.out.println("*     5. Xem danh sách phiếu mượn   *");
            System.out.println("*     6. Đăng xuất                   *");
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
                    System.out.println("4. Xem danh sách tài khoản");
                    System.out.println("5. Quay lại");
                    System.out.print("Nhập lựa chọn: ");
                    int accountChoice = scanner.nextInt();
                    scanner.nextLine();

                    switch (accountChoice) {
                        case 1:
                            // Thêm tài khoản
                            try {
                                System.out.print("Nhập username: ");
                                String username = scanner.nextLine();
                                System.out.print("Nhập password: ");
                                String password = scanner.nextLine();
                                System.out.print("Nhập họ tên: ");
                                String name = scanner.nextLine();
                                System.out.print("Nhập email: ");
                                String email = scanner.nextLine();
                                System.out.print("Nhập loại tài khoản (librarian/customer): ");
                                String type = scanner.nextLine();
                                accountController.createAccount(username, password, name, email, type);
                            } catch (InvalidInputException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 2:
                            // Sửa tài khoản
                            try {
                                System.out.print("Nhập username tài khoản cần sửa: ");
                                String username = scanner.nextLine();
                                System.out.print("Nhập password mới (bỏ trống nếu không muốn thay đổi): ");
                                String newPassword = scanner.nextLine();
                                System.out.print("Nhập họ tên mới (bỏ trống nếu không muốn thay đổi): ");
                                String newName = scanner.nextLine();
                                System.out.print("Nhập email mới (bỏ trống nếu không muốn thay đổi): ");
                                String newEmail = scanner.nextLine();
                                accountController.updateAccount(username, newPassword, newName, newEmail);
                            } catch (AccountNotFoundException | InvalidInputException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 3:
                            // Xóa tài khoản
                            try {
                                System.out.print("Nhập username tài khoản cần xóa: ");
                                String username = scanner.nextLine();
                                accountController.deleteAccount(username);
                            } catch (AccountNotFoundException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 4:
                            // Xem danh sách tài khoản
                            System.out.println("Danh sách tài khoản:");
                            for (Account account : dataManager.getAccounts().values()) {
                                System.out.println(account);
                            }
                            break;
                        case 5:
                            break; // Quay lại menu chính
                        default:
                            System.out.println("Lựa chọn không hợp lệ!");
                    }
                    break;
                case 2:
                    // Quản lý sách
                    System.out.println("1. Thêm sách");
                    System.out.println("2. Sửa sách");
                    System.out.println("3. Xóa sách");
                    System.out.println("4. Tìm kiếm sách");
                    System.out.println("5. Xem danh sách sách");
                    System.out.println("6. Quay lại");
                    System.out.print("Nhập lựa chọn: ");
                    int bookChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (bookChoice) {
                        case 1:
                            // Thêm sách
                            System.out.print("Nhập mã sách: ");
                            String bookId = scanner.nextLine();
                            System.out.print("Nhập tên sách: ");
                            String title = scanner.nextLine();
                            System.out.print("Nhập tác giả: ");
                            String author = scanner.nextLine();
                            bookController.addBook(bookId, title, author);
                            break;
                        case 2:
                            // Sửa sách
                            try {
                                System.out.print("Nhập mã sách cần sửa: ");
                                String bookIdToUpdate = scanner.nextLine();
                                System.out.print("Nhập tên sách mới (bỏ trống nếu không muốn thay đổi): ");
                                String newTitle = scanner.nextLine();
                                System.out.print("Nhập tác giả mới (bỏ trống nếu không muốn thay đổi): ");
                                String newAuthor = scanner.nextLine();
                                bookController.updateBook(bookIdToUpdate, newTitle, newAuthor);
                            } catch (BookNotFoundException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 3:
                            // Xóa sách
                            try {
                                System.out.print("Nhập mã sách cần xóa: ");
                                String bookIdToDelete = scanner.nextLine();
                                bookController.deleteBook(bookIdToDelete);
                            } catch (BookNotFoundException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 4:
                            // Tìm kiếm sách
                            System.out.print("Nhập từ khóa tìm kiếm: ");
                            String keyword = scanner.nextLine();
                            bookController.searchBook(keyword);
                            break;
                        case 5:
                            // Xem danh sách sách
                            System.out.println("Danh sách sách:");
                            for (Book book : dataManager.getBooks().values()) {
                                System.out.println("- " + book.getTitle() + " - " + book.getAuthor());
                            }
                            break;
                        case 6:
                            break; // Quay lại menu chính
                        default:
                            System.out.println("Lựa chọn không hợp lệ!");
                    }
                    break;
                case 3:
                    // Cho mượn sách
                    try {
                        System.out.print("Nhập mã sách: ");
                        String bookId = scanner.nextLine();
                        System.out.print("Nhập username người mượn: ");
                        String username = scanner.nextLine();
                        loanController.borrowBook(bookId, username);
                        dataManager.saveLoans(); // Lưu danh sách loans vào file
                    } catch (BookNotFoundException | CustomerNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    // Nhận trả sách
                    try {
                        System.out.print("Nhập mã sách: ");
                        String bookId = scanner.nextLine();
                        System.out.print("Nhập username người trả: ");
                        String username = scanner.nextLine();
                        loanController.returnBook(bookId, username);
                        dataManager.saveLoans(); // Lưu danh sách loans vào file
                    } catch (LoanNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    // Hiển thị danh sách phiếu mượn
                    displayLoanList();
                    break;
                case 6:
                    return; // Quay lại menu đăng nhập
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
    private void displayLoanList() {
        List<Loan> allLoans = dataManager.getLoans(); // Lấy danh sách tất cả phiếu mượn từ DataManager
        if (allLoans.isEmpty()) {
            System.out.println("Chưa có phiếu mượn nào.");
        } else {
            System.out.println("Danh sách tất cả phiếu mượn:");
            for (Loan loan : allLoans) {
                System.out.println("- " + loan.getBook().getTitle()
                        + " - " + loan.getCustomer().getName()
                        + " - " + loan.getBorrowDate()
                        + " - " + loan.getDueDate());
            }
        }
    }
    private void displayCustomerMenu() {
        while (true) {
            System.out.println("****************************************");
            System.out.println("*     HỆ THỐNG QUẢN LÝ THƯ VIỆN     *");
            System.out.println("****************************************");
            System.out.println("* Xin chào " + accountController.getCurrentAccount().getName() + "!          *");
            System.out.println("*     1. Tìm kiếm sách                *");
            System.out.println("*     2. Mượn sách                    *");
            System.out.println("*     3. Trả sách                      *");
            System.out.println("*     4. Xem sách đã mượn           *");
            System.out.println("*     5. Đăng xuất                   *");
            System.out.println("****************************************");
            System.out.print("Nhập lựa chọn: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Tìm kiếm sách
                    System.out.print("Nhập từ khóa tìm kiếm: ");
                    String keyword = scanner.nextLine();
                    bookController.searchBook(keyword);
                    break;
                case 2:
                    // Mượn sách
                    try {
                        System.out.print("Nhập mã sách: ");
                        String bookId = scanner.nextLine();
                        loanController.borrowBook(bookId, accountController.getCurrentAccount().getUsername());
                        dataManager.saveLoans(); // Lưu danh sách loans vào file
                    } catch (BookNotFoundException | CustomerNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    // Trả sách
                    try {
                        System.out.print("Nhập mã sách: ");
                        String bookId = scanner.nextLine();
                        loanController.returnBook(bookId, accountController.getCurrentAccount().getUsername());
                        dataManager.saveLoans(); // Lưu danh sách loans vào file
                    } catch (LoanNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    // Xem sách đã mượn
                    Customer customer = (Customer) accountController.getCurrentAccount();
                    List<Book> borrowedBooks = customer.getBorrowedBooks();
                    if (borrowedBooks.isEmpty()) {
                        System.out.println("Bạn chưa mượn sách nào.");
                    } else {
                        System.out.println("Danh sách sách bạn đã mượn:");
                        for (Book book : borrowedBooks) {
                            System.out.println("- " + book.getTitle() + " - " + book.getAuthor());
                        }
                    }
                    break;
                case 5:
                    return; // Quay lại menu đăng nhập
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}