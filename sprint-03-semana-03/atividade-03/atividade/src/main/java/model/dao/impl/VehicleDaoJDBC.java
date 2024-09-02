package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.VehicleDao;
import model.entities.Category;
import model.entities.MonthlyPayer;
import model.entities.Vehicle;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class VehicleDaoJDBC implements VehicleDao {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private Connection connection;

    public VehicleDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Vehicle vehicle) {
        PreparedStatement st = null;

        try{

            st = connection.prepareStatement(
                    "INSERT INTO Vehicle "
                    + "(plateVehicle, exemptedVehicle, codeCategory, codeMonthlyPayer) "
                    + "VALUES "
                    + "(?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            st.setString(1, vehicle.getPlate());
            st.setInt(2, vehicle.getExempted() ? 1 : 0);
            st.setInt(3, vehicle.getCategory().getCodeCategory());
            st.setInt(4, vehicle.getMonthlyPayer().getCodeMonthlyPayer());

            int rowsAffected = st.executeUpdate();

            if(rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();

                while(rs.next()) {
                    String plate = rs.getString(1);
                    vehicle.setPlate(plate);
                }

                DB.closeResultSet(rs);
            } else{
                throw new DbException("Unexpected error! No rows affected!");
            }

        } catch(SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Vehicle vehicle) {
        PreparedStatement st = null;

        try{

            st = connection.prepareStatement(
                    "UPDATE Vehicle "
                            + "SET exemptedVehicle = ?, codeCategory = ?, codeMonthlyPayer = ? "
                            + "WHERE "
                            + "plateVehicle = ?"
            );

            st.setInt(1, vehicle.getExempted() ? 1 : 0);
            st.setInt(2, vehicle.getCategory().getCodeCategory());
            st.setInt(3, vehicle.getMonthlyPayer().getCodeMonthlyPayer());
            st.setString(4, vehicle.getPlate());

            st.executeUpdate();

        } catch(SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteByPlate(String plate) {
        PreparedStatement st = null;

        try{

            st = connection.prepareStatement("DELETE FROM Vehicle WHERE plateVehicle = ?");
            st.setString(1, plate);

            st.executeUpdate();

        } catch(SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    public Vehicle instantiateVehicle(ResultSet rs, Category category, MonthlyPayer monthlyPayer) throws SQLException {
        Vehicle vehicle = new Vehicle();

        vehicle.setPlate(rs.getString("plateVehicle"));
        vehicle.setExempted(rs.getBoolean("exemptedVehicle"));
        vehicle.setCategory(category);
        vehicle.setMonthlyPayer(monthlyPayer);

        return vehicle;
    }

    public MonthlyPayer instantiateMonthlyPayer(ResultSet rs) throws SQLException {
        MonthlyPayer monthlyPayer = new MonthlyPayer();

        monthlyPayer.setCodeMonthlyPayer(rs.getInt("codeMonthlyPayer"));
        monthlyPayer.setMonthlyFee(rs.getDouble("monthlyFeeMonthlyPayer"));
        monthlyPayer.setCurrentMonth(LocalDate.parse(rs.getString("currentMonthMonthlyPayer"), formatter));
        monthlyPayer.setMonthlyFeePaid(rs.getBoolean("monthlyFeePaidMonthlyPayer"));

        return monthlyPayer;
    }

    public Category instantiateCategory(ResultSet rs) throws SQLException {
        Category category = new Category();

        category.setCodeCategory(rs.getInt("codeCategory"));
        category.setNameCategory(rs.getString("nameCategory"));

        return category;
    }

    @Override
    public Vehicle findByPlate(String plate) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{

            st = connection.prepareStatement("SELECT * FROM Vehicle WHERE plateVehicle = ?");
            st.setString(1, plate);

            rs = st.executeQuery();

            if(rs.next()) {
                Category category = instantiateCategory(rs);
                MonthlyPayer monthlyPayer = instantiateMonthlyPayer(rs);

                return instantiateVehicle(rs, category, monthlyPayer);
            }

            return null;

        } catch(SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public Vehicle findByCategoryId(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{

            st = connection.prepareStatement(
                    "SELECT "
                    + "Vehicle.plateVehicle, Vehicle.exemptedVehicle, "
                    + "Category.nameCategory, MonthlyPayer.monthlyFeeMonthlyPayer, "
                    + "MonthlyPayer.currentMonthMonthlyPayer, MonthlyPayer.monthlyFeePaidMonthlyPayer "
                    + "FROM "
                    + "Vehicle "
                    + "INNER JOIN "
                    + "Category "
                    + "ON "
                    + "Vehicle.codeCategory = Category.codeCategory "
                    + "INNER JOIN "
                    + "MonthlyPayer "
                    + "ON "
                    + "Vehicle.codeMonthlyPayer = MonthlyPayer.codeMonthlyPayer "
                    + "WHERE Category.codeCategory = ? "
                    + "ORDER BY MonthlyPayer.currentMonthMonthlyPayer"
            );

            st.setInt(1, id);

            rs = st.executeQuery();

            if(rs.next()) {
                Category category = instantiateCategory(rs);
                MonthlyPayer monthlyPayer = instantiateMonthlyPayer(rs);
                Vehicle vehicle = instantiateVehicle(rs, category, monthlyPayer);

                return vehicle;
            }

            return null;

        } catch(SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public Vehicle findByMonthlyPayerId(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{

            st = connection.prepareStatement(
                    "SELECT "
                    + "Vehicle.plateVehicle, Vehicle.exemptedVehicle, "
                    + "Category.nameCategory, MonthlyPayer.monthlyFeeMonthlyPayer, "
                    + "MonthlyPayer.currentMonthMonthlyPayer, MonthlyPayer.monthlyFeePaidMonthlyPayer "
                    + "FROM "
                    + "Vehicle "
                    + "INNER JOIN "
                    + "Category "
                    + "ON "
                    + "Vehicle.codeCategory = Category.codeCategory "
                    + "INNER JOIN "
                    + "MonthlyPayer "
                    + "ON "
                    + "Vehicle.codeMonthlyPayer = MonthlyPayer.codeMonthlyPayer "
                    + "WHERE MonthlyPayer.codeMonthlyPayer = ? "
                    + "ORDER BY MonthlyPayer.currentMonthMonthlyPayer"
            );

            st.setInt(1, id);

            rs = st.executeQuery();

            if(rs.next()) {
                Category category = instantiateCategory(rs);
                MonthlyPayer monthlyPayer = instantiateMonthlyPayer(rs);
                Vehicle vehicle = instantiateVehicle(rs, category, monthlyPayer);

                return vehicle;
            }

            return null;

        } catch(SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Vehicle> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{

            st = connection.prepareStatement(
                    "SELECT "
                    + "Vehicle.plateVehicle, Vehicle.exemptedVehicle, "
                    + "Category.nameCategory, MonthlyPayer.monthlyFeeMonthlyPayer, "
                    + "MonthlyPayer.currentMonthMonthlyPayer, MonthlyPayer.monthlyFeePaidMonthlyPayer "
                    + "FROM "
                    + "Vehicle "
                    + "INNER JOIN "
                    + "Category "
                    + "ON "
                    + "Vehicle.codeCategory = Category.codeCategory "
                    + "INNER JOIN "
                    + "MonthlyPayer "
                    + "ON "
                    + "Vehicle.codeMonthlyPayer = MonthlyPayer.codeMonthlyPayer "
                    + "ORDER BY MonthlyPayer.currentMonthMonthlyPayer"
            );

            rs = st.executeQuery();

            List<Vehicle> lista = new ArrayList<>();
            while(rs.next()) {
                Category category = instantiateCategory(rs);
                MonthlyPayer monthlyPayer = instantiateMonthlyPayer(rs);
                Vehicle vehicle = instantiateVehicle(rs, category, monthlyPayer);

                lista.add(vehicle);
            }

            return lista;

        } catch(SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
}
