package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Loan implements Serializable {
    private Book book;
    private Customer customer;
    private LocalDate borrowDate;
    private LocalDate dueDate;

    public Loan(Book book, Customer customer) {
        this.book = book;
        this.customer = customer;
        this.borrowDate = LocalDate.now();
        this.dueDate = borrowDate.plusWeeks(2); // Ví dụ: hạn trả sau 2 tuần
    }

    // Getters and setters for all attributes
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}