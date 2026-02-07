package com.example.coffee.controller;

import com.example.coffee.model.Worker;
import com.example.coffee.repo.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class WorkerController {

    private final WorkerRepository workerRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> creds) {
        String username = creds.get("username");
        String password = creds.get("password");

        Optional<Worker> worker = workerRepository.findByUsername(username);
        
        if (worker.isPresent() && worker.get().getPassword().equals(password)) {
            return ResponseEntity.ok(worker.get());
        }
        
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
