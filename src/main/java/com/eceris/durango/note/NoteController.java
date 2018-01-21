package com.eceris.durango.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/note")
public class NoteController {

    @Autowired
    private NoteService service;

    @GetMapping("{id}")
    @ResponseBody
    public ResponseEntity<Note> get(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Note>> getBundle() {
        return ResponseEntity.ok().body(service.getBundle());
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Note> create(@RequestBody Note note) {
        return ResponseEntity.ok().body(service.create(note));
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<Note> update(@RequestBody Note note) {
        return ResponseEntity.ok().body(service.update(note));
    }

    @DeleteMapping
    @ResponseBody
    public ResponseEntity delete(@RequestBody Note note) {
        service.delete(note);
        return ResponseEntity.ok().build();
    }
}
