package go.it.java_notepad.service;
import go.it.java_notepad.entity.Note;
import go.it.java_notepad.entity.User;
import go.it.java_notepad.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;
@RequiredArgsConstructor
@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final Random random = new Random();

    public List<Note> listAll() {
        return noteRepository.findAll();
    }

    public String author() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return authentication.getName();
    }

    public Note add(Note note) {
        long id = random.nextLong();
        note.setId(id);
        note.setUser_id(1L);
        note.setAccess("private");
        noteRepository.save(note);
        return note;
    }

    public void deleteById(long id) {
        noteRepository.deleteById(id);
    }

    public void update(Note note) {
        long id = note.getId();
        if (!noteRepository.existsById(id)) {
            throw new IllegalArgumentException("Note with id=" + id + " does not exist");
        }
        noteRepository.save(note);
    }

    public Note getById(long id) {
        Note note = noteRepository.getReferenceById(id);
        if (note == null) {
            throw new IllegalArgumentException("Note with id=" + id + " does not exist");
        }
        return note;
    }

    public Long getUserId() {
        final User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getUser_id();
    }
}
