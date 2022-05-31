package petsanct;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureHttpGraphQlTester
class ForceErrorTest {

    @Autowired
    HttpGraphQlTester graphQlTester;

    @Test
    public void testForceError() {
        HttpGraphQlTester testerWithHeader = graphQlTester.mutate().header("force-error", "true").build();
        testerWithHeader.document("{dogs{name}}")
                .execute()
                .errors()
                .expect(responseError -> "Forced error".equals(responseError.getMessage()))
                .verify();
    }
}
