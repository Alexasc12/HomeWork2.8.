package pro.sky.skyprospringdemotwo.service;


import org.springframework.stereotype.Service;
import pro.sky.skyprospringdemotwo.exception.EmployeeAlreadyAddedException;
import pro.sky.skyprospringdemotwo.exception.EmployeeNotFoundException;
import pro.sky.skyprospringdemotwo.model.Employee;

import java.util.*;

@Service

public class EmployeeServiceImpl implements EmployeeService {

    public final Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }


    @Override
    public Employee add(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFullName())) {

            throw new EmployeeAlreadyAddedException();

        }
        employees.put(employee.getFullName(),employee);

        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFullName())) {
            employees.remove(employee.getFullName());
            return employee;
        }
        throw new EmployeeNotFoundException();

    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFullName())) {
            return employee;
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(employees.values());
    }
}