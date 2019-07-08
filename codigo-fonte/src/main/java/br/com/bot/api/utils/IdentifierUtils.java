package br.com.bot.api.utils;

import java.util.UUID;

public class IdentifierUtils {

    public static String generateRandomID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}

