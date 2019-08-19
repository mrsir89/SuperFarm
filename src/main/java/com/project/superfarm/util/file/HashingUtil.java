package com.project.superfarm.util.file;


import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class HashingUtil {
    public String sha256Encoding(String original) {
        return Hashing.sha256().hashString(original, StandardCharsets.UTF_8).toString();
    }
}
