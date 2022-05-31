package workshop;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PetController {

    static class Pet {
        String name;

        public Pet(String name) {
            this.name = name;
        }
    }

    @QueryMapping
    public List<Pet> pets() {
        return List.of(new Pet("Luna"), new Pet("Skipper"));
    }

}
