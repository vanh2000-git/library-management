package controller;

import exception.BookNotFoundException;
import model.Book;
import model.DataManager;

import java.util.List;
import java.util.stream.Collectors;

public class BookController {
    private DataManager dataManager;

    public BookController() {
        this.dataManager = DataManager.getInstance();
    }

    public void addBook(String bookId, String title, String author) {
        // Kiểm tra đầu vào (có thể bổ sung thêm kiểm tra trùng mã sách)
        // ...

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

        // Kiểm tra đầu vào
        // ...

        if (newTitle != null) {
            book.setTitle(newTitle);
        }
        if (newAuthor != null) {
            book.setAuthor(newAuthor);
        }
        // ... (cập nhật các thuộc tính khác)

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
        List<Book> foundBooks = dataManager.getBooks().values().stream()
                .filter(book -> book.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                        book.getAuthor().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());

        if (foundBooks.isEmpty()) {
            System.out.println("Không tìm thấy sách nào phù hợp.");
        } else {
            System.out.println("Kết quả tìm kiếm:");
            for (Book book : foundBooks) {
                System.out.println("- " + book.getTitle() + " - " + book.getAuthor());
            }
        }
    }
}