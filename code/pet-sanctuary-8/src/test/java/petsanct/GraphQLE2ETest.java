package petsanct;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.HttpGraphQlTester;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureHttpGraphQlTester
class GraphQLE2ETest {

    @Autowired
    HttpGraphQlTester graphQlTester;

    @Test
    public void testDogNames() {
        graphQlTester.document("{dogs{name}}")
                .execute()
                .path("dogs[*].name")
                .entityList(String.class)
                .isEqualTo(List.of("Luna", "Skipper", "Sophie", "Mixie"));
    }
}
