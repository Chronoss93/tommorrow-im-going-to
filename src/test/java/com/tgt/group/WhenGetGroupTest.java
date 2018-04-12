package com.tgt.group;

import com.tgt.AbstractIntegrationTest;
import com.tgt.note.NoteRepository;
import com.tgt.util.TestDataUtils;
import com.tgt.util.TestUtils;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class WhenGetGroupTest extends AbstractIntegrationTest {
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
        String expectedPayload = TestUtils.loadResourceAsString("api/json/examples/group/two-groups.json");
        noteRepository.save(TestDataUtils.getNotes());
        groupRepository.save(TestDataUtils.getGroups());

        ResultActions result = this.mockMvc.perform(get("/api/v1/groups")
                .accept(MediaType.APPLICATION_JSON_UTF8));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(expectedPayload));
    }

}
