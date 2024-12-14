package com.gorgeous.ringolift.services;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

@Service
public class FileReaderServiceImpl implements FileReaderService {
    @Override
    public String readFile(String fileName) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("static/" + StringUtils.cleanPath(Objects.requireNonNull(fileName)));
        try (InputStream inputStream = classPathResource.getInputStream()) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            return stringBuilder.toString();
        }
    }
}
