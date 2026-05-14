package com.splitinam.api.service;

import com.splitinam.api.model.Session;
import com.splitinam.api.repository.SessionRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public Session createSession(Session session) {
        session.setSessionCode(UUID.randomUUID().toString().substring(0, 6).toUpperCase());
        session.setCreatedAt(LocalDateTime.now());
        session.setPayments(new HashMap<>());
        BigDecimal perPerson = session.getTotal()
                .divide(
                        BigDecimal.valueOf(session.getPeople().size()),
                        2,
                        RoundingMode.HALF_UP);
        session.setPerPerson(perPerson);
        return sessionRepository.save(session);
    }

    public Optional<Session> getSession(String sessionCode) {
        return sessionRepository.findBySessionCode(sessionCode);
    }

    public Session markAsPaid(String sessionCode, String personName) {
        Session session = sessionRepository.findBySessionCode(sessionCode)
                .orElseThrow(() -> new RuntimeException("Session not found"));
        session.getPayments().put(personName, true);
        return sessionRepository.save(session);
    }
}