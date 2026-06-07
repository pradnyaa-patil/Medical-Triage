package com.triage.medicaltriage.service;

import com.triage.medicaltriage.model.LoginRequest;
import com.triage.medicaltriage.model.LoginResponse;

public interface AuthService {

   LoginResponse login(LoginRequest request);
}
