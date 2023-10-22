package com.invictus.app.api.controller;

import com.invictus.app.api.dto.group.GroupRequestDto;
import com.invictus.app.api.dto.group.GroupResponseDto;
import com.invictus.app.api.service.GroupService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) { this.groupService = groupService; }

    @PostMapping
    public GroupResponseDto save(@RequestBody GroupRequestDto groupRequestDto) {
        return groupService.save(groupRequestDto);
    }
}
