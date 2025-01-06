package lmsystem.operations;
import lmsystem.model.Book;
import java.util.List;

public interface BookOperations {
    void addBook(Book book);
    void removeBook(Book book);
    List<Book> getBooks();
}