package test;

import api.ApiController;
import models.ApiResponse;
import models.DataGeneration;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Epic("API Endpoint Testing")
@Feature("POST /endpoint")
@Tag("api")
@DisplayName("Тестирование основного эндпоинта приложения")
public class ApiEndpoint extends ApiController {

    @Test
    @Story("Безопасность")
    @DisplayName("Проверка API-ключа: неверный ключ")
    void testInvalidApiKey() {
        String token = DataGeneration.generateValidToken();

        ApiResponse response = executeRequestWithCustomApiKey(
                "invalid_key", token, "LOGIN");

        assertNotNull(response.getResult());
        assertEquals("Missing or invalid API Key", response.getMessage());
    }

    @Test
    @Story("Валидации")
    @DisplayName("Проверка валидации токена: некорректные символы")
    void testTokenValidationInvalidCharacters() {
        String invalidToken = DataGeneration.generateInvalidToken();

        ApiResponse response = executeRequest(invalidToken, "LOGIN", 400);

        // Ожидаем ошибку валидации
        assertEquals("ERROR", response.getResult());
        assertNotNull(response.getMessage());
    }

    @Test
    @Story("Аутентификация")
    @DisplayName("Успешная аутентификация (LOGIN) с валидным токеном")
    void testSuccessfulLoginWithValidToken() {
        String token = DataGeneration.generateValidToken();

        ApiResponse response = executeRequest(token, "LOGIN", 200);

        assertNotNull(response.getResult());

    }

}