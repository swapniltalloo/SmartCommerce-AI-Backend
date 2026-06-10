package com.swapnil.smartcommerce.service;

import com.swapnil.smartcommerce.entity.Order;
import com.swapnil.smartcommerce.entity.OrderItem;
import com.swapnil.smartcommerce.entity.User;
import com.swapnil.smartcommerce.repository.OrderRepository;
import com.swapnil.smartcommerce.repository.UserRepository;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
public class GeminiService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Value("${gemini.api.key}")
    private String apiKey;

    @Transactional
    public String recommendProducts() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String username =
                authentication.getName();

        User user =
                userRepository.findByUsername(username)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User not found"
                                )
                        );

        List<Order> orders =
                orderRepository.findByUser(user);

        if (orders.isEmpty()) {

            return """
                    No order history found.
                    Place some orders first.
                    """;
        }

        StringBuilder purchaseHistory =
                new StringBuilder();

        for (Order order : orders) {

            for (OrderItem item :
                    order.getOrderItems()) {

                purchaseHistory
                        .append(item.getProduct().getName())
                        .append(", ");
            }
        }

        String prompt = """
                User purchased the following products:

                %s

                Recommend 5 similar products.
                
                For each recommendation provide:
                - Product Name
                - Short Reason

                Keep the response concise.
                """
                .formatted(purchaseHistory);

        return askGemini(prompt);
    }

    public String askGemini(String prompt) {

        System.out.println("================================");
        System.out.println("Gemini Key Loaded: "
                + (apiKey != null && !apiKey.isEmpty()));
        System.out.println("================================");

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
                """
                .formatted(prompt.replace("\"", "\\\""));

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

            String responseBody =
                    response.body() != null
                            ? response.body().string()
                            : "No response body";

            System.out.println(
                    "Gemini Status Code: "
                            + response.code()
            );

            System.out.println(
                    "Gemini Response: "
                            + responseBody
            );

            return responseBody;

        } catch (IOException e) {

            throw new RuntimeException(
                    "Failed to call Gemini API",
                    e
            );
        }
    }
}