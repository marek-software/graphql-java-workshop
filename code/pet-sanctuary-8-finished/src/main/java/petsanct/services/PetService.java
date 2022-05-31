package petsanct.services;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Service
public class PetService {

    static List<String> dogNames = List.of("Abby", "Abe", "Addie", "Abbott", "Cassie", "Bruce", "Chanel", "Denver", "Gabby", "Gemma", "Iggy", "Juno", "Otis", "Misty", "Precious", "Zoe", "Zeus");

    private final Map<String, Dog> idToDog = new LinkedHashMap<>();
    private final Map<String, Cat> idToCat = new LinkedHashMap<>();

    public PetService() {
        idToDog.put("D1", new Dog("D1", "Luna", false, "E1"));
        idToDog.put("D2", new Dog("D2", "Skipper", false, "E3"));
        idToDog.put("D3", new Dog("D3", "Sophie", true, "E1"));
        idToDog.put("D4", new Dog("D4", "Mixie", true, "E2"));

        idToCat.put("C1", new Cat("C1", "Tiger", true, "E2"));
        idToCat.put("C2", new Cat("C2", "Felie", false, "E2"));
        idToCat.put("C3", new Cat("C3", "Dot", false, "E3"));
        idToCat.put("C4", new Cat("C4", "Bella", true, "E4"));
    }

    public List<Dog> getDogs() {
        return new ArrayList<>(idToDog.values());
    }

    public List<Cat> getCats() {
        return new ArrayList<>(idToCat.values());
    }

    public Cat getCat(String id) {
        return idToCat.get(id);
    }

    public Dog getDog(String id) {
        return idToDog.get(id);
    }


    public Flux<Dog> newDogAdded() {
        Random random = new Random();
        return Flux.interval(Duration.ofSeconds(1)).map(aLong -> {
            String id = UUID.randomUUID().toString();
            String name = dogNames.get(random.nextInt(dogNames.size()));
            boolean shedding = random.nextBoolean();
            Dog newDog = new Dog(id, name, shedding, "E6");
            idToDog.put(id, newDog);
            return newDog;
        });
    }
}
