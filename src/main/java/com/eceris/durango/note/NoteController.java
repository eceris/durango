package com.eceris.durango.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    private NoteService service;

    @GetMapping("/note/{id}")
    @ResponseBody
    public ResponseEntity<Note> get(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @PostMapping("/note")
    @ResponseBody
    public ResponseEntity<Note> create(@RequestBody Note note) {
        return ResponseEntity.ok().body(service.create(note));
    }

    @PutMapping("/note")
    @ResponseBody
    public ResponseEntity<Note> update(@RequestBody Note note) {
        return ResponseEntity.ok().body(service.update(note));
    }

    @DeleteMapping("/note")
    @ResponseBody
    public ResponseEntity delete(@RequestBody Note note) {
        service.delete(note);
        return ResponseEntity.ok().build();
    }
}
