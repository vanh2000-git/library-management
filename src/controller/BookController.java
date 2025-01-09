package controller;

import model.Book;
import model.DataManager;

public class BookController {
    private DataManager dataManager;

    public BookController() {
        this.dataManager = DataManager.getInstance();
    }

    public void addBook(String bookId, String title, String author) {
        // ... (code to add a new book)
        Book book = new Book(bookId, title, author);
        dataManager.addBook(book);
        System.out.println("Thêm sách thành công!");
    }

    // Other methods for book management (update, delete, search, ...)
}