package petsanct;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;
import petsanct.controller.GraphQLController;
import petsanct.services.EmployeeService;
import petsanct.services.PetService;
import petsanct.services.SanctuaryEmployee;

@GraphQlTest(GraphQLController.class)
public class GraphQLControllerTest {

    @Autowired
    GraphQlTester graphQlTester;

    @MockBean
    EmployeeService employeeService;

    @MockBean
    PetService petService;

    @Test
    public void testEmployee() {
        Mockito.when(employeeService.getEmployee("E123"))
                .thenReturn(new SanctuaryEmployee("E123", "TestName"));
        graphQlTester
                .document("{employee(id:\"E123\"){name}}")
                .execute()
                .path("employee.name")
                .entity(String.class)
                .isEqualTo("TestName");
    }
}