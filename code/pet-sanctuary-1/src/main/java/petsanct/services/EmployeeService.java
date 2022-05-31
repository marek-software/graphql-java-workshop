package petsanct.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final Map<String, Employee> idToEmployee = new LinkedHashMap<>();

    public EmployeeService() {
        idToEmployee.put("E1", new Employee("E1", "Charlotte"));
        idToEmployee.put("E2", new Employee("E2", "George"));
        idToEmployee.put("E3", new Employee("E3", "Mia"));
        idToEmployee.put("E4", new Employee("E4", "Charles"));

    }

    public List<Employee> getEmployees() {
        return new ArrayList<>(idToEmployee.values());
    }

    public List<Employee> getEmployees(List<String> ids) {
        return ids.stream().map(id -> idToEmployee.get(id)).collect(Collectors.toList());
    }

    public Employee getEmployee(String id) {
        return idToEmployee.get(id);
    }

    public Employee changeName(String employeeId, String newName) {
        Employee Employee = idToEmployee.get(employeeId);
        Employee.setName(newName);
        return Employee;
    }
}
