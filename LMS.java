import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import lmsystem.admin.Admin;
import lmsystem.model.Book;
import lmsystem.model.Fine;
import lmsystem.model.User;

public class LMS {
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String BLUE = "\u001B[34m";
    
    Admin admin = new Admin("lmsystem", "lms@123");
    Scanner scanner = new Scanner(System.in);

    public void run() {
        while (true) {
            System.out.println("\n" + BLUE + "****************************************");
            System.out.println("**************  LMS Menu  **************");
            System.out.println("****************************************" + RESET);
            System.out.println("1. Login as Admin");
            System.out.println("2. Login as User");
            System.out.println("3. Exit" + RESET);
            System.out.print("Enter your choice: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1: loginAsAdmin(); break;
                    case 2: loginAsUser (); break;
                    case 3: System.out.println(GREEN + "Exiting..." + RESET); return;
                    default: System.out.println(RED + "Invalid choice. Please try again." + RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(RED + "Invalid input. Please enter a number." + RESET);
            }
        }
    }

    private void loginAsAdmin() {
        System.out.print("Enter admin username: ");
        String username = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();

        if (username.equals(admin.getUsername()) && password.equals(admin.getPassword())) {
            admin.login();
            System.out.println(GREEN + "Login successful!" + RESET);
            adminMenu();
        } else {
            System.out.println(RED + "Invalid username or password. Please try again." + RESET);
        }
    }

    private void loginAsUser () {
        System.out.print("Enter user username: ");
        String username = scanner.nextLine();
        System.out.print("Enter user password: ");
        String password = scanner.nextLine();

        User user = findUser (username, password);
        if (user != null) {
            user.login();
            System.out.println(GREEN + "Login successful!" + RESET);
            userMenu(user);
        } else {
            System.out.println(RED + "Invalid username or password. Please try again." + RESET);
        }
    }

    private User findUser (String username, String password) {
        for (User  user : admin.getUsers()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    private void adminMenu() {
        while (true) {
            System.out.println("\n" + BLUE + "****************************************");
            System.out.println("*************  Admin Menu  *************");
            System.out.println("****************************************" + RESET);
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Add User");
            System.out.println("4. Remove User");
            System.out.println("5. Add Fine");
            System.out.println("6. View Books");
            System.out.println("7. View Users");
            System.out.println("8. View Fines");
            System.out.println("9. Logout"+ RESET);
            System.out.print("Enter your choice: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1: addBook(); break;
                    case 2: removeBook(); break;
                    case 3: addUser (); break;
                    case 4: removeUser (); break;
                    case 5: addFine(); break;
                    case 6: viewBooks(); break;
                    case 7: viewUsers(); break;
                    case 8: viewFines(); break;
                    case 9: System.out .println(GREEN + "Logging out..." + RESET); return;
                    default: System.out.println(RED + "Invalid choice. Please try again." + RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(RED + "Invalid input. Please enter a number." + RESET);
            }
        }
    }

    private void userMenu(User user) {
        while (true) {
            System.out.println("\n" + BLUE + "****************************************");
            System.out.println("*************  User Menu  **************");
            System.out.println("****************************************" + RESET);
            System.out.println("1. Search Books");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. View Issue History");
            System.out.println("5. Pay Fine");
            System.out.println("6. Logout" + RESET);
            System.out.print("Enter your choice: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1: searchBooks(); break;
                    case 2: issueBook(user); break;
                    case 3: returnBook(user); break;
                    case 4: viewIssueHistory(user); break;
                    case 5: payFine(user); break;
                    case 6: System.out.println(GREEN + "Logging out..." + RESET); return;
                    default: System.out.println(RED + "Invalid choice. Please try again." + RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(RED + "Invalid input. Please enter a number." + RESET);
            }
        }
    }

    private void addBook() {
        try {
            System.out.print("Enter book title: ");
            String title = scanner.nextLine();
            System.out.print("Enter book author: ");
            String author = scanner.nextLine();
            System.out.print("Enter book ISBN: ");
            String ISBN = scanner.nextLine();
            System.out.print("Enter book quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            admin.addBook(new Book(title, author, ISBN, quantity));
            System.out.println(GREEN + "Book added successfully!" + RESET);
        } catch (NumberFormatException e) {
            System.out.println(RED + "Invalid quantity. Please enter a valid number." + RESET);
        }
    }

    private void removeBook() {
        System.out.print("Enter book ISBN: ");
        String ISBN = scanner.nextLine();

        for (Book book : admin.getBooks()) {
            if (book.getISBN().equals(ISBN)) {
                admin.removeBook(book);
                System.out.println(GREEN + "Book removed successfully!" + RESET);
                return;
            }
        }
        System.out.println(RED + "Book not found!" + RESET);
    }

    private void addUser () {
        System.out.print("Enter user username: ");
        String username = scanner.nextLine();
        System.out.print("Enter user password: ");
        String password = scanner.nextLine();

        admin.addUser(new User(username, password));
        System.out.println(GREEN + "User added successfully!" + RESET);
    }

    private void removeUser() {
        System.out.print("Enter user username: ");
        String username = scanner.nextLine();

        for (User  user : admin.getUsers()) {
            if (user.getUsername().equals(username)) {
                admin.removeUser (user);
                System.out.println(GREEN + "User  removed successfully!" + RESET);
                return;
            }
        }
        System.out.println(RED + "User  not found!" + RESET);
    }

    private void addFine() {
        System.out.print("Enter user username: ");
        String username = scanner.nextLine();
        System.out.print("Enter fine amount: ");
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(RED + "Invalid amount. Please enter a valid number." + RESET);
            return;
        }

        System.out.print("Enter fine due date (yyyy-MM-dd): ");
        String dueDateStr = scanner.nextLine();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dueDate ;
        try {
            dueDate = sdf.parse(dueDateStr);
        } catch (ParseException e) {
            System.out.println(RED + "Invalid date format. Please use yyyy-MM-dd." + RESET);
            return;
        }
    
        User user = ByUsername(username); 
        if (user != null) {
            admin.addFineForUser(amount, dueDate, user);
            System.out.println(GREEN + "Fine added successfully!" + RESET);
        } else {
            System.out.println(RED + "User not found!" + RESET);
        }
    }

    private User ByUsername(String username) { 
        for (User  user : admin.getUsers()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
    
    private void viewBooks() {
        System.out.println("\n" + BLUE + "==============================");
        System.out.println("          Books List            ");
        System.out.println("==============================");
        System.out.printf("%-20s %-20s %-10s %-10s\n", "Title", "Author", "ISBN", "Quantity");
        System.out.println("---------------------------------------------------------------");
        for (Book book : admin.getBooks()) {
            System.out.printf("%-20s %-20s %-10s %-10d\n", book.getTitle(), book.getAuthor (), book.getISBN(), book.getQuantity());
        }
        System.out.println("---------------------------------------------------------------");
    }

    private void viewFines() {
        System.out.println("\n" + BLUE + "==============================");
        System.out.println("          Fines List            ");
        System.out.println("==============================");
        System.out.printf("%-10s %-15s %-15s\n", "Amount", "Due Date", "Username");
        System.out.println("---------------------------------------------------------------");
        for (Fine fine : admin.getFines()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            System.out.printf("%-10.2f %-15s %-15s\n", fine.getAmount(), sdf.format(fine.getDueDate()), fine.getUser().getUsername());
        }
        System.out.println("---------------------------------------------------------------");
    }

    private void viewUsers() {
        System.out.println("\n" + BLUE + "==============================");
        System.out.println("          Users List            ");
        System.out.println("==============================");
        System.out.printf("%-10s %-10s\n", "Username", "Password");
        System.out.println("---------------------------------------------------------------");
        for (User   user : admin.getUsers()) {
            System.out.printf("%-10s %-10s\n", user.getUsername(), user.getPassword());
        }
        System.out.println("---------------------------------------------------------------");
    }

    private void searchBooks() {
        System.out.print("Enter book title or author: ");
        String query = scanner.nextLine();

        boolean found = false;
        for (Book book : admin.getBooks()) {
            if (book.getTitle().contains(query) || book.getAuthor().contains(query)) {
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("ISBN: " + book.getISBN());
                System.out.println("Quantity: " + book.getQuantity());
                System.out.println();
                found = true;
            }
        }
        if (!found) {
            System.out.println(RED + "No books found matching the query." + RESET);
        }
    }

    private void issueBook(User user) {
        System.out.print("Enter book ISBN: ");
        String ISBN = scanner.nextLine();

        for (Book book : admin.getBooks()) {
            if (book.getISBN().equals(ISBN) && book.getQuantity() > 0) {
                book.setQuantity(book.getQuantity() - 1);
                user.addIssueHistory(book);
                System.out.println(GREEN + "Book issued successfully!" + RESET);
                return;
            }
        }
        System.out.println(RED + "Book not available!" + RESET);
    }

    private void returnBook(User user) {
        System.out.print("Enter book ISBN: ");
        String ISBN = scanner.nextLine();

        for (Book book : user.getIssueHistory()) {
            if (book.getISBN().equals(ISBN)) {
                book.setQuantity(book.getQuantity() + 1);
                user.getIssueHistory().remove(book);
                System.out.println(GREEN + "Book returned successfully!" + RESET);
                return;
            }
        }
        System.out.println(RED + "Book not found in issue history!" + RESET);
    }

    private void viewIssueHistory(User user) {
        System.out.println("\n" + BLUE + "==============================");
        System.out.println("      Issue History List        ");
        System.out.println("==============================");
        System.out.printf("%-20s %-20s %-10s\n", "Title", "Author", "ISBN");
        System.out.println("---------------------------------------------------------------");
        for (Book book : user.getIssueHistory()) {
            System.out.printf("%-20s %-20s %-10s\n", book.getTitle(), book.getAuthor(), book.getISBN());
        }
        System.out.println("---------------------------------------------------------------");
    }

    private void payFine(User user) {
        System.out.print("Enter fine amount to pay: ");
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(RED + "Invalid amount. Please enter a valid number." + RESET);
            return;
        }
        Fine fineToPay = null;
        for (Fine fine : admin.getFines()) {
            if (fine.getUser().equals(user) && fine.getAmount() == amount) {
                fineToPay = fine;
                break;
            }
        }
        if (fineToPay != null) {
            admin.removeFine(fineToPay);
            
            System.out.println(GREEN + "Fine paid successfully!" + RESET);
        } else {
            System.out.println(RED + "Fine not found or amount is incorrect!" + RESET);
        }
    }
    public static void main(String[] args) {
        LMS system = new LMS();
        system.run();
    }
}