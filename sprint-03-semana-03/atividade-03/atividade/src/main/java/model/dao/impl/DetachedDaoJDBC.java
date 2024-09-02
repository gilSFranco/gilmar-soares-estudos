package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DetachedDao;
import model.entities.Detached;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DetachedDaoJDBC implements DetachedDao {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private Connection connection;

    public DetachedDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Detached detached) {
        PreparedStatement st = null;

        try{

            st = connection.prepareStatement(
                    "INSERT INTO Detached "
                    + "(plateDetached, amountToPayDetached, entryTime, departureTime) "
                    + "VALUES "
                    + "(?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            st.setString(1, detached.getPlate());
            st.setDouble(2, detached.getAmountToPay());
            st.setString(3, formatter.format(detached.getEntryTime()));
            st.setString(4, formatter.format(detached.getDepartureTime()));

            int rowsAffected = st.executeUpdate();

            if(rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();

                while(rs.next()) {
                    String plate = rs.getString(1);
                    detached.setPlate(plate);
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
    public void update(Detached detached) {
        PreparedStatement st = null;

        try{

            st = connection.prepareStatement(
                    "UPDATE Detached "
                    + "SET amountToPayDetached = ?, entryTime = ?, departureTime = ? "
                    + "WHERE "
                    + "plateDetached = ?"
            );

            st.setDouble(1, detached.getAmountToPay());
            st.setString(2, formatter.format(detached.getEntryTime()));
            st.setString(3, formatter.format(detached.getDepartureTime()));
            st.setString(4, detached.getPlate());

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

            st = connection.prepareStatement("DELETE FROM Detached WHERE plateDetached = ?");

            st.setString(1, plate);

            st.executeUpdate();

        } catch(SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Detached findByPlate(String plate) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{

            st = connection.prepareStatement("SELECT * FROM Detached WHERE plateDetached = ?");

            st.setString(1, plate);

            rs = st.executeQuery();

            if(rs.next()) {
                Detached detached = new Detached();

                detached.setPlate(rs.getString(1));
                detached.setAmountToPay(rs.getDouble(2));
                detached.setEntryTime(LocalDateTime.parse(rs.getString(3), formatter));
                detached.setDepartureTime(LocalDateTime.parse(rs.getString(4), formatter));

                return detached;
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
    public List<Detached> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{

            st = connection.prepareStatement("SELECT * FROM Detached");
            rs = st.executeQuery();

            List<Detached> lista = new ArrayList<>();
            while(rs.next()) {
                Detached detached = new Detached();

                detached.setPlate(rs.getString(1));
                detached.setAmountToPay(rs.getDouble(2));
                detached.setEntryTime(LocalDateTime.parse(rs.getString(3), formatter));
                detached.setDepartureTime(LocalDateTime.parse(rs.getString(4), formatter));

                lista.add(detached);
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
