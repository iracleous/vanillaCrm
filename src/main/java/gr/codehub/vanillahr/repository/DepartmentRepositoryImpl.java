package gr.codehub.vanillahr.repository;

import gr.codehub.vanillahr.db.DbService;
import gr.codehub.vanillahr.model.Department;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class DepartmentRepositoryImpl implements Repository<Department>{

    private DbService dbservice;

    public DepartmentRepositoryImpl(DbService dbservice) {
        this.dbservice = dbservice;
    }


    @Override
    public Department create(Department department) {
        int key= saveDepartment(department);
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



    ////////





    private int saveDepartment( Department department){

        Connection connection = dbservice.getConnection();
        if (connection== null) return 0;

        String sql = "    insert into Department (  name,   location ) values(?,?) ";

        int key = 0;
        try (PreparedStatement insert = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS) ){

            insert.setString(1, department.getName());
            insert.setString(2, department.getLocation());



            insert.executeUpdate();

            ResultSet rs = insert.getGeneratedKeys();
            if (rs != null && rs.next()) {
                key = rs.getInt(1);
            }
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        finally{
            return key;
        }
    }








}
