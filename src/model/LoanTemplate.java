package model;

public abstract class LoanTemplate {
    public final void printLoan(Loan loan) {
        printHeader();
        printBookDetails(loan.getBook());
        printCustomerDetails(loan.getCustomer());
        printLoanDetails(loan);
        printFooter();
    }

    protected abstract void printHeader();
    protected abstract void printBookDetails(Book book);
    protected abstract void printCustomerDetails(Customer customer);
    protected abstract void printLoanDetails(Loan loan);
    protected abstract void printFooter();
}