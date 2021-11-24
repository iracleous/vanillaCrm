package gr.codehub.vanillahr.model;

import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class Employee {


    /*
    create table Employee(
        id integer   PRIMARY KEY AUTO_INCREMENT,
        name VARCHAR(50),
        address VARCHAR(50),
        specialityString   VARCHAR(50),
        specialityInt integer,
        dateOfBirth date,
        belongingDepartmentId integer,
        managingProjectId integer,
        directorDepartmentId integer
     );

insert into Employee( name,  address ,   specialityString,  specialityInt ,    dateOfBirth  )  values(?,?,?,?, ?)

delete from Employee where id=?

update Employee set belongingDepartmentId=? where id=?

select * from Employee

select * from Employee  where id=?


     */




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
