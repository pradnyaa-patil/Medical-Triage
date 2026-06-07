package com.triage.medicaltriage.service;

import com.triage.medicaltriage.config.JwtUtil;
import com.triage.medicaltriage.entity.PatientEntity;
import com.triage.medicaltriage.model.LoginRequest;
import com.triage.medicaltriage.model.LoginResponse;
import com.triage.medicaltriage.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Autowired
    private PatientRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest request) {

        PatientEntity patientEntity = repository.findByEmail(request.getEmail());

        if (patientEntity == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }
        boolean valid = passwordEncoder.matches(request.getPassword(), patientEntity.getPassword());
        if (!valid) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(patientEntity.getEmail());

        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setFirstName(patientEntity.getFirstName());
        response.setPatientId(patientEntity.getPatientId());

        return response;
    }
}
