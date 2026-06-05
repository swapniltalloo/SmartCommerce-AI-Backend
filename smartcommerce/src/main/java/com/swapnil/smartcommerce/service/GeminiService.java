package com.swapnil.smartcommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import okhttp3.*;
import java.io.IOException;
import com.swapnil.smartcommerce.entity.User;
import com.swapnil.smartcommerce.entity.Order;
import com.swapnil.smartcommerce.entity.OrderItem;
import com.swapnil.smartcommerce.repository.UserRepository;
import com.swapnil.smartcommerce.repository.OrderRepository;

import java.util.List;
@Service
public class GeminiService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;
    @Value("${gemini.api.key}")
    private String apiKey;
    public String recommendProducts(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        List<Order> orders =
                orderRepository.findByUser(user);

        if (orders.isEmpty()) {
            return "No order history found.";
        }

        StringBuilder purchaseHistory =
                new StringBuilder();

        for (Order order : orders) {

            for (OrderItem item :
                    order.getOrderItems()) {

                purchaseHistory.append(
                        item.getProduct().getName()
                ).append(", ");
            }
        }

        String prompt = """
            User has purchased:

            %s

            Recommend 5 products
            with reasons.
            """
                .formatted(purchaseHistory);

        return askGemini(prompt);
    }
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