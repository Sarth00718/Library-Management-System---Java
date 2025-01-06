package lmsystem.operations;
import lmsystem.model.User;
import java.util.List;

public interface UserOperations {
    void addUser (User user);
    void removeUser (User user);
    List<User> getUsers();
}