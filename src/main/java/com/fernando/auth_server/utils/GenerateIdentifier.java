package com.fernando.auth_server.utils;

import java.util.UUID;

public class GenerateIdentifier {
    private GenerateIdentifier(){

    }
    public static String generateIdentifierUser() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
