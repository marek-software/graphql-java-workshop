package petsanct.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final Map<String, SanctuaryEmployee> idToEmployee = new LinkedHashMap<>();

    public EmployeeService() {
        idToEmployee.put("E1", new SanctuaryEmployee("E1", "Charlotte"));
        idToEmployee.put("E2", new SanctuaryEmployee("E2", "George"));
        idToEmployee.put("E3", new SanctuaryEmployee("E3", "Mia"));
        idToEmployee.put("E4", new SanctuaryEmployee("E4", "Charles"));

    }

    public List<SanctuaryEmployee> getEmployees() {
        return new ArrayList<>(idToEmployee.values());
    }

    public List<SanctuaryEmployee> getEmployees(List<String> ids) {
        return ids.stream().map(id -> idToEmployee.get(id)).collect(Collectors.toList());
    }

    public SanctuaryEmployee getEmployee(String id) {
        return idToEmployee.get(id);
    }

    public SanctuaryEmployee changeName(String employeeId, String newName) {
        SanctuaryEmployee sanctuaryEmployee = idToEmployee.get(employeeId);
        sanctuaryEmployee.setName(newName);
        return sanctuaryEmployee;
    }
}
