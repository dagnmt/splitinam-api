package com.splitinam.api.controller;

import com.splitinam.api.model.Session;
import com.splitinam.api.service.SessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sessions")
@CrossOrigin(origins = "http://localhost:5173")
public class SessionController {

    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping
    public ResponseEntity<Session> createSession(@RequestBody Session session) {
        return ResponseEntity.ok(sessionService.createSession(session));
    }

    @GetMapping("/{sessionCode}")
    public ResponseEntity<Session> getSession(@PathVariable String sessionCode) {
        return sessionService.getSession(sessionCode)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{sessionCode}/pay")
    public ResponseEntity<Session> markAsPaid(
            @PathVariable String sessionCode,
            @RequestParam String person) {
        return ResponseEntity.ok(sessionService.markAsPaid(sessionCode, person));
    }
}