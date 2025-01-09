package model;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataManager {
    private static DataManager instance;
    private Map<String, Account> accounts;
    private Map<String, Book> books;
    private List<Loan> loans;
    private static final String ACCOUNT_FILE = "accounts.dat";
    private static final String BOOK_FILE = "books.dat";
    private static final String LOAN_FILE = "loans.dat";

    private DataManager() {
        this.accounts = loadAccounts();
        this.books = loadBooks();
        this.loans = loadLoans();
    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public void addAccount(Account account) {
        accounts.put(account.getUsername(), account);
        saveAccounts();
    }

    public void addBook(Book book) {
        books.put(book.getBookId(), book);
        saveBooks();
    }

    public Account findAccount(String username) {
        return accounts.get(username);
    }

    public Book findBook(String bookId) {
        return books.get(bookId);
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public Map<String, Book> getBooks() {
        return books;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void saveLoans() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(LOAN_FILE))) {
            oos.writeObject(loans);
        } catch (IOException e) {
            System.err.println("Lỗi khi lưu file loans.dat: " + e.getMessage());
        }
    }

    private List<Loan> loadLoans() {
        List<Loan> loans = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(LOAN_FILE))) {
            loans = (List<Loan>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            File file = new File(LOAN_FILE);
            try {
                file.createNewFile();
            } catch (IOException ex) {
                System.err.println("Lỗi khi tạo file loans.dat: " + ex.getMessage());
            }
        }
        return loans;
    }

    private Map<String, Account> loadAccounts() {
        Map<String, Account> accounts = new HashMap<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ACCOUNT_FILE))) {
            accounts = (Map<String, Account>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            File file = new File(ACCOUNT_FILE);
            try {
                file.createNewFile();
            } catch (IOException ex) {
                System.err.println("Lỗi khi tạo file accounts.dat: " + ex.getMessage());
            }
        }
        return accounts;
    }

    public void saveAccounts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ACCOUNT_FILE))) {
            oos.writeObject(accounts);
        } catch (IOException e) {
            System.err.println("Lỗi khi lưu file accounts.dat: " + e.getMessage());
        }
    }

    private Map<String, Book> loadBooks() {
        Map<String, Book> books = new HashMap<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(BOOK_FILE))) {
            books = (Map<String, Book>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            File file = new File(BOOK_FILE);
            try {
                file.createNewFile();
            } catch (IOException ex) {
                System.err.println("Lỗi khi tạo file books.dat: " + ex.getMessage());
            }
        }
        return books;
    }

    public void saveBooks() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BOOK_FILE))) {
            oos.writeObject(books);
        } catch (IOException e) {
            System.err.println("Lỗi khi lưu file books.dat: " + e.getMessage());
        }
    }
}