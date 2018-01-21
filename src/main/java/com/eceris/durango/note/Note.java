package com.eceris.durango.note;

import com.eceris.durango.component.Auditable;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class Note extends Auditable<String> {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;

    @OneToMany
    private List<Tag> tags;
}
