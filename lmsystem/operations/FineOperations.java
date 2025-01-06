package lmsystem.operations;
import lmsystem.model.Fine;
import java.util.List;

public interface FineOperations {
    void addFine(Fine fine);
    void removeFine(Fine fine);
    List<Fine> getFines();
}