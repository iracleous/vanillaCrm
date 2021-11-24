package gr.codehub.vanillahr.repository;

import gr.codehub.vanillahr.db.DbService;
import gr.codehub.vanillahr.model.Department;

import java.util.List;
import java.util.Optional;

public class DepartmentRepositoryImpl implements Repository<Department>{

    private DbService dbservice;

    public DepartmentRepositoryImpl(DbService dbservice) {
        this.dbservice = dbservice;
    }


    @Override
    public Department create(Department department) {
        int key= dbservice.saveDepartment(department);
        department.setId(key);
        return department;
    }

    @Override
    public Optional<Department> read(int id) {
        return Optional.empty();
    }

    @Override
    public List<Department> read() {
        return null;
    }

    @Override
    public Department update(int Id, Department e) {
        return null;
    }

    @Override
    public boolean delete(int Id) {
        return false;
    }



}
