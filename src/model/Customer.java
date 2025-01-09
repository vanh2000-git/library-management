package model;

import java.util.ArrayList;
import java.util.List;

public class Customer extends Account {
    private List<Book> borrowedBooks;

    public Customer(String username, String password, String name, String email) {
        super(username, password, name, email);
        this.borrowedBooks = new ArrayList<>();
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}