package com.eceris.durango.note;

import com.eceris.durango.component.Auditable;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Tag extends Auditable<String> {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
}
