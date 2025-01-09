package model;

public class LoanReceipt extends LoanTemplate {
    @Override
    protected void printHeader() {
        System.out.println("****************************************");
        System.out.println("*             PHIẾU MƯỢN SÁCH         *");
        System.out.println("****************************************");
    }

    @Override
    protected void printBookDetails(Book book) {
        System.out.println("Tên sách: " + book.getTitle());
        System.out.println("Tác giả: " + book.getAuthor());
        // ... other book details
    }

    @Override
    protected void printCustomerDetails(Customer customer) {
        System.out.println("Tên người mượn: " + customer.getName());
        // ... other customer details
    }

    @Override
    protected void printLoanDetails(Loan loan) {
        System.out.println("Ngày mượn: " + loan.getBorrowDate());
        System.out.println("Hạn trả: " + loan.getDueDate());
    }

    @Override
    protected void printFooter() {
        System.out.println("****************************************");
        System.out.println("*  Cảm ơn bạn đã sử dụng dịch vụ!   *");
        System.out.println("****************************************");
    }
}