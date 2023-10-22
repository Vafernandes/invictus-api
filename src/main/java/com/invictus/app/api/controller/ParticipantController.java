package com.invictus.app.api.controller;

import com.invictus.app.api.dto.participant.ParticipantRequestDto;
import com.invictus.app.api.dto.participant.ParticipantResponseDto;
import com.invictus.app.api.service.ParticipantService;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "participants")
public class ParticipantController {

    private ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @PostMapping
    public ParticipantResponseDto save(@RequestBody ParticipantRequestDto participantRequestDto) {
        return participantService.save(participantRequestDto);
    }

    @GetMapping
    public List<ParticipantResponseDto> findAll() {
        return participantService.findAll();
    }

    @GetMapping("/{id}")
    public ParticipantResponseDto findById(@PathVariable UUID id) {
        return participantService.findById(id);
    }
}
