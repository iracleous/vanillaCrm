package gr.codehub.vanillahr.model;

import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class Employee {
    private int id;
    private String  name;
    private String  address;
    private Speciality speciality;
    private Date dateOfBirth;
    private Department belongingDepartment;
    private List<Project> projects;
    private Project  managingProject;
    private  Department directorDepartment;
}
