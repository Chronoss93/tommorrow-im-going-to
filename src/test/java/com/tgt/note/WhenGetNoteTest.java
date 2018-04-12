package com.tgt.note;

import com.tgt.AbstractIntegrationTest;
import com.tgt.group.GroupRepository;
import com.tgt.util.TestDataUtils;
import com.tgt.util.TestUtils;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class WhenGetNoteTest extends AbstractIntegrationTest{

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private NoteRepository noteRepository;

    @After
    public void cleanUp(){
        groupRepository.deleteAll();
        noteRepository.deleteAll();
    }


    @Test
    public void shouldReturnAllGroups() throws Exception {
        String expectedPayload = TestUtils.loadResourceAsString("api/json/examples/note/two-notes.json");
        noteRepository.save(TestDataUtils.getNotes());

        ResultActions result = this.mockMvc.perform(get("/api/v1/notes")
                .accept(MediaType.APPLICATION_JSON_UTF8));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(expectedPayload));
    }


}