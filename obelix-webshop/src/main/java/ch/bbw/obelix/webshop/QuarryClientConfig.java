package ch.bbw.obelix.webshop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class QuarryClientConfig {

    @Bean
    public WebClient quarryWebClient(WebClient.Builder builder) {
        return builder
                .baseUrl("http://localhost:8081")
                .build();
    }
}
