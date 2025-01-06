package lmsystem.model;

public class Book {
    private String title, author, ISBN;
    private int quantity;

    public Book(String title, String author, String ISBN, int quantity) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.quantity = quantity;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getISBN() { return ISBN; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}