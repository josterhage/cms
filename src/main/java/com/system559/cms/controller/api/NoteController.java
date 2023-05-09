package com.system559.cms.controller.api;

import com.system559.cms.repository.NoteRepository;
import com.system559.cms.security.service.AccessControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/note")
public class NoteController {
    private final AccessControlService accessControlService;
    private final NoteRepository noteRepository;

    @Autowired
    public NoteController(AccessControlService accessControlService,
                          NoteRepository noteRepository) {
        this.accessControlService = accessControlService;
        this.noteRepository = noteRepository;
    }

    @GetMapping
    public String getAll(@AuthenticationPrincipal String authenticatedUserId) {
        return authenticatedUserId;
    }
}
