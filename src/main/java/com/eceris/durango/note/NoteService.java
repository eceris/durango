package com.eceris.durango.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteRepository repository;

    public Note get(Long id) {
        return repository.findOne(id);
    }

    public List<Note> getBundle() {
        return repository.findAll();
    }

    public Note create(Note note) {
        return repository.save(note);
    }

    public Note update(Note note) {
        return repository.save(note);
    }

    public void delete(Note note) {
        repository.delete(note);
    }
}
