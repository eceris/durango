package com.eceris.durango.board;

import com.eceris.durango.component.Auditable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Board extends Auditable<String> {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String content;
}
