package com.tgt.group;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
@AllArgsConstructor
public class GroupController {

    private GroupRepository groupRepository;

    @GetMapping
    public ResponseEntity<List<Group>> getAll(@PageableDefault Pageable pageable) {
        Page<Group> retoures = groupRepository.findAll(pageable);
        return new ResponseEntity<>(retoures.getContent(), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Group> saveGroup(@RequestBody GroupCreateDto createDto) {
        Group entity = new Group();
        entity.setName(createDto.getName());
        Group saved = groupRepository.save(entity);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}
