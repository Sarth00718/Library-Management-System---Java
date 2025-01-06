package lmsystem.admin;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import lmsystem.model.Book;
import lmsystem.model.Fine;
import lmsystem.model.Person;
import lmsystem.model.User;
import lmsystem.operations.BookOperations;
import lmsystem.operations.FineOperations;
import lmsystem.operations.UserOperations;

public class Admin extends Person implements BookOperations, UserOperations, FineOperations {
    private List<Book> books = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<Fine> fines = new ArrayList<>();

    public Admin(String username, String password) {
        super(username, password);
        loadBooks();
        loadUsers();
        loadFines();
    }

    private void loadBooks() {
        try (BufferedReader br = new BufferedReader(new FileReader("books.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                books.add(new Book(parts[0], parts[1], parts[2], Integer.parseInt(parts[3])));
            }
        } catch (IOException e) {
            System.out.println("Error loading books: " + e.getMessage());
        }
    }

    private void loadUsers() {
        try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                users.add(new User(parts[0], parts[1]));
            }
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
    }

    private void loadFines() {
        try (BufferedReader br = new BufferedReader(new FileReader("fines.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                fines.add(new Fine(Double.parseDouble(parts[0]), 
                                   new SimpleDateFormat("yyyy-MM-dd").parse(parts[1]), 
                                   byUsername(parts[2])));
            }
        } catch (IOException | ParseException e) {
            System.out.println("Error loading fines: " + e.getMessage());
        }
    }

    public void saveBooks() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("books.txt"))) {
            for (Book book : books) {
                bw.write(book.getTitle() + "," + book.getAuthor() + "," + book.getISBN() + "," + book.getQuantity());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    public void saveUsers() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("users.txt"))) {
            for (User  user : users) {
                bw.write(user.getUsername() + "," + user.getPassword());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    public void saveFines() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("fines.txt"))) {
            for (Fine fine : fines) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                bw.write(fine.getAmount() + "," + sdf.format(fine.getDueDate()) + "," + fine.getUser ().getUsername());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving fines: " + e.getMessage());
        }
    }

    public void addBook(Book book) { books.add(book); saveBooks(); }
    public void removeBook(Book book) { books.remove(book);  saveBooks (); }
    public List<Book> getBooks() { return books; }
    public void addUser (User user) { users.add(user); saveUsers(); }
    public void removeUser (User user) { users.remove(user);  saveUsers(); }
    public User byUsername(String username) {
        for (User  user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
    public List<User> getUsers() {  return users; }
    public void addFine(Fine fine) { fines.add(fine); saveFines(); }
    public void addFineForUser (double amount, Date dueDate, User user) {
        Fine fine = new Fine(amount, dueDate, user);
        fines.add(fine);
        saveFines(); 
    }
    public void removeFine(Fine fine) { fines.remove(fine); saveFines(); }
    public List<Fine> getFines() { return fines; }
    public void login() {
        System.out.println("Admin " + username + " logged in.");
    }
    public void logout() {
        System.out.println("Admin " + username + " logged out.");
    }
}