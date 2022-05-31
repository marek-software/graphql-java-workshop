package petsanct.controller;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import petsanct.services.Cat;
import petsanct.services.Dog;
import petsanct.services.PetService;

import java.util.List;

@Controller
public class GraphQLController {

    private final PetService petService;

    public GraphQLController(PetService petService) {
        this.petService = petService;
    }

    @QueryMapping
    public List<Cat> cats() {
        return petService.getCats();
    }

    @QueryMapping
    public List<Dog> dogs() {
        return petService.getDogs();
    }
}
