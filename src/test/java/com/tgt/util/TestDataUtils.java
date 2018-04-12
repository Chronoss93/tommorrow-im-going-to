package com.tgt.util;

import com.tgt.group.Group;
import com.tgt.note.Note;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class TestDataUtils {

    private static List<Group> groups = new ArrayList<>();
    private static List<Note> notes = new ArrayList<>();

    static {
        Note note1 = new Note();
        note1.setObjectId(ObjectId.createFromLegacyFormat(1,1,1));
        note1.setText("bla bla bla");
        notes.add(note1);
        Note note2 = new Note();
        note2.setObjectId(ObjectId.createFromLegacyFormat(3,4,5));
        note2.setText("One upon a time");
        notes.add(note2);
    }

    static {
        Group group1 = new Group();
        group1.setObjectId(ObjectId.createFromLegacyFormat(1,3,1));
        group1.setName("Warsong");
        group1.setNotes(getNotes());
        groups.add(group1);

        Group group2 = new Group();
        group2.setObjectId(ObjectId.createFromLegacyFormat(1,4,1));
        group2.setName("Arathi");
        group2.setNotes(getNotes());
        groups.add(group2);
    }


    public static List<Group> getGroups() {
        return groups;
    }

    public static List<Note> getNotes() {
        return notes;
    }
}
