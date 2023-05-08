package go.it.java_notepad.endpoint;

import go.it.java_notepad.entity.Note;
import go.it.java_notepad.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/note")
public class NoteController {
    private final NoteService noteService;
    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView result = new ModelAndView("note-list");
        result.addObject("noteList", noteService.listAll());
        result.addObject("author", noteService.author());
        return result;
    }

    @PostMapping("/delete")
    public RedirectView delete(@RequestParam long id) {
        noteService.deleteById(id);
        return new RedirectView("/note/list");
    }
    @GetMapping("/edit")
    public ModelAndView edit(@RequestParam long id) {
        ModelAndView result = new ModelAndView("note-create-edit");
        result.addObject("note", noteService.getById(id));
        return result;
    }

    @PostMapping("/edit")
    public RedirectView edit(@RequestParam long id,
                             @RequestParam String title,
                             @RequestParam String content,
                             @RequestParam String accessType) {
        Note note = new Note();
        note.setId(id);
        note.setTitle(title);
        note.setContent(content);
        note.setAccess(accessType);
        note.setUser_id(noteService.getUserId());
        noteService.update(note);
        return new RedirectView("/note/list");
    }
    @GetMapping("/create")
    public ModelAndView add(){
        ModelAndView result = new ModelAndView("note-create-edit");
        result.addObject("note", null);
        return result;
    }

    @PostMapping("/create")
    public RedirectView add(@RequestParam String title,
                             @RequestParam String content,
                             @RequestParam String accessType){
        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        note.setAccess(accessType);
        note.setUser_id(noteService.getUserId());
        noteService.add(note);
        return new RedirectView("/note/list");
    }

    @GetMapping("/note-share")
    public ModelAndView share(@RequestParam long id) {
        ModelAndView result = new ModelAndView("note-share");
        Note note = noteService.getById(id);
        if (note.getAccess().equals("public")) {
            result.addObject("note", note);
        } else {
            result.addObject("note", null);
        }
        return result;
    }
}

