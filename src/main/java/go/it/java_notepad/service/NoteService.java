package go.it.java_notepad.service;

import go.it.java_notepad.entity.Note;
import go.it.java_notepad.entity.User;
import go.it.java_notepad.repository.NoteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

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
        note.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
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
        Note note;
        try {
            note = noteRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException("Note with id=" + id + " does not exist");
        }
        return note;
    }

    public Long getUserId() {
        final User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getUser_id();
    }

    public boolean isTitleValid(Note note) {
        return note.getTitle().length() >= 5 && note.getTitle().length() <= 100;
    }

    public boolean isContentValid(Note note) {
        return note.getContent().length() >= 5 && note.getContent().length() <= 10000;
    }

}
