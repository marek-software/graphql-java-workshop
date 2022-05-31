package petsanct;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.graphql.test.tester.WebSocketGraphQlTester;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import petsanct.services.Dog;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.net.URI;
import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SubscriptionTest {

    @Value("http://localhost:${local.server.port}${spring.graphql.websocket.path}")
    private String baseUrl;

    private GraphQlTester graphQlTester;


    @BeforeEach
    void setUp() {
        URI url = URI.create(baseUrl);
        this.graphQlTester = WebSocketGraphQlTester.builder(url, new ReactorNettyWebSocketClient()).build();
    }


    @Test
    void testNewDogAdded() {
        Flux<Dog> hello = graphQlTester
                .document("subscription {newDogAdded { name shedding }}")
                .executeSubscription()
                .toFlux("newDogAdded", Dog.class);

        StepVerifier.create(hello)
                .expectNextMatches(dog -> dog.getName().length() > 2)
                .expectNextMatches(dog -> dog.getName().length() > 2)
                .expectNextMatches(dog -> dog.getName().length() > 2)
                .thenCancel()
                .verify();
    }
}