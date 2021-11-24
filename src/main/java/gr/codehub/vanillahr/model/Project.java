package gr.codehub.vanillahr.model;


import lombok.Data;

import java.util.List;

@Data
public class Project {
    private int id;
    private String name;
    private List<Employee> employees;
    private Employee manager;

}
