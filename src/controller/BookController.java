package controller;

import model.Book;
import model.DataManager;

public class BookController {
    private DataManager dataManager;

    public BookController() {
        this.dataManager = DataManager.getInstance();
    }

    public void addBook(String bookId, String title, String author) {
        Book book = new Book(bookId, title, author);
        dataManager.addBook(book);
        System.out.println("Thêm sách thành công!");
    }

    public void updateBook(String bookId, String newTitle, String newAuthor)
            throws BookNotFoundException {
        Book book = dataManager.findBook(bookId);
        if (book == null) {
            throw new BookNotFoundException("Không tìm thấy sách có mã " + bookId);
        }


        if (newTitle != null) {
            book.setTitle(newTitle);
        }
        if (newAuthor != null) {
            book.setAuthor(newAuthor);
        }

        dataManager.saveBooks(); // Lưu thay đổi vào file
        System.out.println("Cập nhật sách thành công!");
    }

    public void deleteBook(String bookId) throws BookNotFoundException {
        Book book = dataManager.findBook(bookId);
        if (book == null) {
            throw new BookNotFoundException("Không tìm thấy sách có mã " + bookId);
        }

        dataManager.getBooks().remove(bookId);
        dataManager.saveBooks(); // Lưu thay đổi vào file
        System.out.println("Xóa sách thành công!");
    }

    public void searchBook(String keyword) {
        // Tìm kiếm sách theo keyword (tên sách, tác giả, ...)
        // ...
    }

    // Other methods for book management (update, delete, search, ...)
}