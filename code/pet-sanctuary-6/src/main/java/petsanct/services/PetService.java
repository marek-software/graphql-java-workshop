package petsanct.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class PetService {


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


}
