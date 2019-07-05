package br.com.bot.api.utils;

import java.util.UUID;

public class IdentifierUtil {

    public static String generateRandomID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}

