package controller;

import exception.BookNotFoundException;
import exception.CustomerNotFoundException;
import exception.LoanNotFoundException;
import model.*;

import java.util.ArrayList;
import java.util.List;

public class LoanController {
    private DataManager dataManager;
    private List<Loan> loans;

    public LoanController() {
        this.dataManager = DataManager.getInstance();
        this.loans = new ArrayList<>(); // Hoặc load từ file nếu có
    }

    public void borrowBook(String bookId, String username) throws BookNotFoundException, CustomerNotFoundException {
        Book book = dataManager.findBook(bookId);
        if (book == null) {
            throw new BookNotFoundException("Không tìm thấy sách có mã " + bookId);
        }

        Customer customer = (Customer) dataManager.findAccount(username);
        if (customer == null) {
            throw new CustomerNotFoundException("Không tìm thấy khách hàng có username " + username);
        }

        Loan loan = new Loan(book, customer);
        loans.add(loan);
        customer.borrowBook(book); // Cập nhật danh sách sách mượn của khách hàng

        // In phiếu mượn
        LoanReceipt loanReceipt = new LoanReceipt();
        loanReceipt.printLoan(loan);
    }

    public void returnBook(String bookId, String username) throws LoanNotFoundException {
        // ... (code to return a book)
        Loan loan = findLoan(bookId, username);
        if (loan == null) {
            throw new LoanNotFoundException("Không tìm thấy phiếu mượn");
        }
        loans.remove(loan);
        Customer customer = loan.getCustomer();
        customer.returnBook(loan.getBook());
        System.out.println("Trả sách thành công!");
    }

    private Loan findLoan(String bookId, String username) {
        for (Loan loan : loans) {
            if (loan.getBook().getBookId().equals(bookId) &&
                    loan.getCustomer().getUsername().equals(username)) {
                return loan;
            }
        }
        return null;
    }

    // Other methods for loan management
}