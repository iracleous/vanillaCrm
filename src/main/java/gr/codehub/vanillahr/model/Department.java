package gr.codehub.vanillahr.model;

import lombok.Data;

import java.util.List;

@Data
public class Department {

    /*
    create table Department(
                       id integer   PRIMARY KEY AUTO_INCREMENT,
                        name VARCHAR(50),
                        location VARCHAR(50),
                        director integer\n"
                     );

    insert into Department ( id ,  name,   location,  director) values(?,?,?,?)


     */



    private int id;
    private String name;
    private String location;
    private List<Employee> employees;
    private Employee director;

}
