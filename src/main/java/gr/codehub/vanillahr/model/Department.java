package gr.codehub.vanillahr.model;

import lombok.Data;

import java.util.List;

@Data
public class Department {
    private int id;
    private String name;
    private String location;
    private List<Employee> employees;
    private Employee director;

}
