package go.it.java_notepad.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Note {
    @Id
    private long id;
    private String title;
    private String content;
    @Enumerated(EnumType.STRING)
    private AccessType access;
    private Long user_id;

    public Note(String title, String content, AccessType access, Long user_id) {
        this.title = title;
        this.content = content;
        this.access = access;
        this.user_id = user_id;
    }
}