package com.epam.mjc.nio;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class FileReader {

    public Profile getDataFromFile(File file) throws IOException {
        Map<String, String> profileData;
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(file))) {
            profileData = readProfileData(reader);
        }
        return createProfile(profileData);
    }

    private Map<String, String> readProfileData(BufferedReader reader) throws IOException {
        Map<String, String> profileData = new HashMap<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(": ");
            if (parts.length == 2) {
                profileData.put(parts[0], parts[1]);
            }
        }
        return profileData;
    }

    private Profile createProfile(Map<String, String> profileData) {
        String name = profileData.get("Name");
        Integer age = Integer.parseInt(profileData.get("Age"));
        String email = profileData.get("Email");
        Long phone = Long.parseLong(profileData.get("Phone"));
        return new Profile(name, age, email, phone);
    }
}
