package com.invictus.app.api.controller;

import com.invictus.app.api.dto.participant.ParticipantRequestDto;
import com.invictus.app.api.dto.participant.ParticipantResponseDto;
import com.invictus.app.api.dto.participant.ParticipantSaveRequestDto;
import com.invictus.app.api.service.ParticipantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "participants")
public class ParticipantController {

    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @PostMapping
    public ParticipantResponseDto save(@RequestBody ParticipantSaveRequestDto requestDto) {
        return participantService.save(requestDto);
    }

    @GetMapping
    public List<ParticipantResponseDto> findAll() {
        return participantService.findAll();
    }

    @GetMapping("/{id}")
    public ParticipantResponseDto findById(@PathVariable UUID id) {
        return participantService.findById(id);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> joinGroup(@RequestBody ParticipantRequestDto requestDto, @PathVariable UUID id) {
        requestDto.setId(id);
        participantService.joinGroup(requestDto);
        return ResponseEntity.ok().build();
    }
}
