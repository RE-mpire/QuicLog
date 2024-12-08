import com.quiclog.command.*;
import com.quiclog.model.*;
import com.quiclog.viewmodel.*;

public class Main {
    public static void main(String[] args) {
        ApplicationViewModel appViewModel = new ApplicationViewModel();

        Bucket bucket = appViewModel.createBucket("My Bucket");
        System.out.println("Created bucket: " + bucket.getName());

        Note note = appViewModel.createNoteInBucket("My Bucket", "This is a note.");
        System.out.println("Added note: " + note.getContent());

        appViewModel.editNoteInBucket("My Bucket", "This is a note.", "This is an edited note.");
        System.out.println("Edited note: " + note.getContent());

        appViewModel.deleteNoteInBucket("My Bucket", "This is an edited note.");
        System.out.println("Deleted note.");

        appViewModel.undo();
        System.out.println("Undo delete: " + note.getContent());

        appViewModel.redo();
        System.out.println("Redo delete.");

        appViewModel.deleteBucket("My Bucket");
        System.out.println("Deleted bucket.");

        appViewModel.undo();
        System.out.println("Undo delete bucket: " + bucket.getName());

        appViewModel.redo();
        System.out.println("Redo delete bucket.");
    }
}