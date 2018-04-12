package com.tgt.note;

import com.tgt.exception.DocumentNotFoundException;
import com.tgt.group.Group;
import com.tgt.group.GroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
@AllArgsConstructor
public class NoteController {

    private GroupRepository groupRepository;
    private NoteRepository noteRepository;

    @GetMapping
    public ResponseEntity<List<Note>> getAll(@PageableDefault Pageable pageable) {
        Page<Note> notes = noteRepository.findAll(pageable);
        return new ResponseEntity<>(notes.getContent(), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Group> saveNote(@RequestBody NoteCreateDto createDto) throws DocumentNotFoundException {
        Group group = groupRepository.findByName(createDto.getGroupName())
                .orElseThrow(() -> new DocumentNotFoundException("Couldn't find group with name=" + createDto.getGroupName()));

        Note note = new Note();
        note.setText(createDto.getText());
        Note savedNote = noteRepository.save(note);
        group.getNotes().add(savedNote);
        Group savedGroup = groupRepository.save(group);

        return new ResponseEntity<>(savedGroup, HttpStatus.CREATED);
    }
}
