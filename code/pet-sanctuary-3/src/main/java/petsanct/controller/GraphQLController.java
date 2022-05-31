package petsanct.controller;

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

}
