package com.tgt.group;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tgt.note.Note;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
public class Group {

    @Id
    private ObjectId objectId;
    private String name;
    @DBRef
    private List<Note> notes;

}
