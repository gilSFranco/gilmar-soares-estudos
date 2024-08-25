package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.EmployeeDao;
import model.entities.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoJDBC implements EmployeeDao {
    private Connection connection;

    public EmployeeDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Employee employee) {
        PreparedStatement st = null;

        try{
            st = connection.prepareStatement(
                    "INSERT INTO Employee "
                    + "(nomeEmployee, salarioEmployee) "
                    + "VALUES "
                    + "(?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, employee.getName());
            st.setDouble(2, employee.getBaseSalary());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();

                while (rs.next()) {
                    int id = rs.getInt(1);
                    employee.setId(id);
                }

                DB.closeResultSet(rs);
            } else{
                throw new DbException("Unexpected error! No rows affected!");
            }
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Employee> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = connection.prepareStatement("SELECT * FROM Employee");
            rs = st.executeQuery();

            List<Employee> lista = new ArrayList<>();

            while (rs.next()) {
                Employee employee = new Employee();

                employee.setId(rs.getInt("codigoEmployee"));
                employee.setName(rs.getString("nomeEmployee"));
                employee.setBaseSalary(rs.getDouble("salarioEmployee"));

                lista.add(employee);
            }

            return lista;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
}
