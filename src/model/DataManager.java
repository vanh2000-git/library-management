package model;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DataManager {
    private static DataManager instance;
    private Map<String, Account> accounts;
    private Map<String, Book> books;
    private static final String ACCOUNT_FILE = "accounts.dat";
    private static final String BOOK_FILE = "books.dat";

    private DataManager() {
        this.accounts = loadAccounts();
        this.books = loadBooks();
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

    // Other methods for data management (load, save, search, ...)

    private Map<String, Account> loadAccounts() {
        Map<String, Account> accounts = new HashMap<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ACCOUNT_FILE))) {
            accounts = (Map<String, Account>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Handle exception (e.g., create a new file if it doesn't exist)
        }
        return accounts;
    }

    private void saveAccounts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ACCOUNT_FILE))) {
            oos.writeObject(accounts);
        } catch (IOException e) {
            // Handle exception
        }
    }

    private Map<String, Book> loadBooks() {
        Map<String, Book> books = new HashMap<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(BOOK_FILE))) {
            books = (Map<String, Book>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Handle exception (e.g., create a new file if it doesn't exist)
        }
        return books;
    }

    private void saveBooks() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BOOK_FILE))) {
            oos.writeObject(books);
        } catch (IOException e) {
            // Handle exception
        }
    }
}