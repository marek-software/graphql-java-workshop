package petsanct.controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import petsanct.services.Cat;
import petsanct.services.Dog;
import petsanct.services.Employee;
import petsanct.services.EmployeeService;
import petsanct.services.PetService;

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
    public List<Dog> dogs() {
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
}
