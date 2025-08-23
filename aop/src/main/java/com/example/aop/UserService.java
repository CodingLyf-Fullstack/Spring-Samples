package com.example.aop;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public String getUserById(int id) {
        if (id == 1) {
            return "CodingLyf";
        }
        return "Unknown User";
    }

    public void updateUser(int id, String name) {
        // Imagine DB update here
        System.out.println("Updating user " + id + " to name " + name);
    }
}
	