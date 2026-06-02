package com.swapnil.smartcommerce.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import okhttp3.*;
import java.io.IOException;
@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    public String askGemini(String prompt) {

        OkHttpClient client =
                new OkHttpClient();

        String jsonBody = """
        {
          "contents": [
            {
              "parts": [
                {
                  "text": "%s"
                }
              ]
            }
          ]
        }
        """.formatted(prompt);

        RequestBody body =
                RequestBody.create(
                        jsonBody,
                        MediaType.parse(
                                "application/json"
                        )
                );

        Request request =
                new Request.Builder()
                        .url(
                                "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key="
                                        + apiKey
                        )
                        .post(body)
                        .build();

        try {

            Response response =
                    client.newCall(request)
                            .execute();

            return response.body()
                    .string();

        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }
}