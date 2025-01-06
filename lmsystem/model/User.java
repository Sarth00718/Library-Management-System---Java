package lmsystem.model;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class User extends Person {
    private List<Book> issueHistory = new ArrayList<>();

    public User(String username, String password) {
        super(username, password);
        loadHistory();
    }

    public List<Book> getIssueHistory() { return issueHistory; }
    public void addIssueHistory(Book book) { issueHistory.add(book); saveHistory();}

    private void loadHistory() {
        try (BufferedReader br = new BufferedReader(new FileReader("history.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                issueHistory.add(new Book(parts[0], parts[1], parts[2], 0));
            }
        } catch (IOException e) {
            System.out.println("Error loading books: " + e.getMessage());
        }
    }

    private void saveHistory() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("history.txt"))) {
            for (Book book : issueHistory) {
                bw.write(book.getTitle() + "," + book.getAuthor() + "," + book.getISBN());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving books history: " + e.getMessage());
        }
    }


    public void login() {
        System.out.println("User  " + username + " logged in.");
    }
    public void logout() {
        System.out.println("User  " + username + " logged out.");
    }
}