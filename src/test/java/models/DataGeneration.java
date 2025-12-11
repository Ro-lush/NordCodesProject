package models;

public class DataGeneration {
    public static String generateValidToken() {
        // Генерация 32-символьного токена с символами A-F0-9 (ответ от сервиса)
        String characters = "ABCDEF0123456789";
        StringBuilder token = new StringBuilder(32);
        for (int i = 0; i < 32; i++) {
            int index = (int) (Math.random() * characters.length());
            token.append(characters.charAt(index));
        }
        return token.toString();
    }

    public static String generateInvalidToken() {
        return "invalid_token_123!@#";
    }
}
