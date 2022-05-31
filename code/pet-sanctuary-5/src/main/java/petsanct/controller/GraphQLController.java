package petsanct.controller;

import graphql.GraphqlErrorBuilder;
import graphql.execution.DataFetcherResult;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.ContextValue;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import petsanct.services.Cat;
import petsanct.services.Dog;
import petsanct.services.Employee;
import petsanct.services.EmployeeService;
import petsanct.services.PetService;
import reactor.core.publisher.Mono;

import java.util.List;

@Controller
public class GraphQLController {

    private final PetService petService;
    private EmployeeService employeeService;

    public GraphQLController(PetService petService, EmployeeService employeeService) {
        this.petService = petService;
        this.employeeService = employeeService;
    }

    @QueryMapping
    public List<Cat> cats() {
        return petService.getCats();
    }

    @QueryMapping
    public Object dogs(@ContextValue(name = "force-error", required = false) Boolean forceError) {
        if (forceError != null && forceError) {
            return DataFetcherResult.newResult()
                    .error(GraphqlErrorBuilder.newError()
                            .message("Forced error")
                            .build())
                    .build();
        }
        return petService.getDogs();
    }

    @SchemaMapping(typeName = "Dog", field = "keeper")
    public Employee dogKeeper(Dog dog) {
        return employeeService.getEmployee(dog.getKeeperId());
    }

    @SchemaMapping(typeName = "Cat", field = "keeper")
    public Employee catKeeper(Cat cat) {
        return employeeService.getEmployee(cat.getKeeperId());
    }

    @SchemaMapping(field = "outdoor")
    public boolean outdoorCat(Cat cat) {
        return cat.isOutdoorCat();
    }


    @QueryMapping
    public Employee employee(@Argument String id) {
        return employeeService.getEmployee(id);
    }

    static class ChangeEmployeeNamePayload {

        Employee employee;

        public ChangeEmployeeNamePayload(Employee employee) {
            this.employee = employee;
        }

        public Employee getEmployee() {
            return employee;
        }

        public void setEmployee(Employee employee) {
            this.employee = employee;
        }
    }

    static class ChangeEmployeeNameInput {
        String id;
        String newName;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNewName() {
            return newName;
        }

        public void setNewName(String newName) {
            this.newName = newName;
        }
    }

    @MutationMapping
    public ChangeEmployeeNamePayload changeEmployeeName(@Argument ChangeEmployeeNameInput input) {
        Employee employee = employeeService.changeName(input.getId(), input.getNewName());
        return new ChangeEmployeeNamePayload(employee);
    }

    @Component
    class ForceErrorInterceptor implements WebGraphQlInterceptor {

        @Override
        public Mono<WebGraphQlResponse> intercept(
                WebGraphQlRequest request, Chain chain
        ) {
            boolean forceError = request
                    .getHeaders()
                    .containsKey("force-error");
            if (forceError) {
                request.configureExecutionInput((executionInput, builder) -> {
                    executionInput
                            .getGraphQLContext()
                            .put("force-error", true);
                    return executionInput;
                });
            }
            return chain.next(request);
        }
    }


}
