package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.MonthlyPayerDao;
import model.entities.MonthlyPayer;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MonthlyPayerDaoJDBC implements MonthlyPayerDao {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private Connection connection;

    public MonthlyPayerDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(MonthlyPayer monthlyPayer) {
        PreparedStatement st = null;

        try{

            st = connection.prepareStatement(
                    "INSERT INTO MonthlyPayer "
                    + "(monthlyFeeMonthlyPayer, currentMonthMonthlyPayer, monthlyFeePaidMonthlyPayer) "
                    + "VALUES "
                    + "(?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            st.setDouble(1, monthlyPayer.getMonthlyFee());
            st.setString(2, formatter.format(monthlyPayer.getCurrentMonth()));
            st.setInt(3, monthlyPayer.getMonthlyFeePaid() ? 1 : 0);

            int rowsAffected = st.executeUpdate();

            if(rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();

                while(rs.next()) {
                    int id = rs.getInt(1);
                    monthlyPayer.setCodeMonthlyPayer(id);
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
    public void update(MonthlyPayer monthlyPayer) {
        PreparedStatement st = null;

        try{

            st = connection.prepareStatement(
                    "UPDATE MonthlyPayer "
                            + "SET monthlyFeeMonthlyPayer = ?, currentMonthMonthlyPayer = ?, monthlyFeePaidMonthlyPayer = ? "
                            + "WHERE "
                            + "codeMonthlyPayer = ?"
            );

            st.setDouble(1, monthlyPayer.getMonthlyFee());
            st.setString(2, formatter.format(monthlyPayer.getCurrentMonth()));
            st.setInt(3, monthlyPayer.getMonthlyFeePaid() ? 1 : 0);
            st.setInt(4, monthlyPayer.getCodeMonthlyPayer());

            st.executeUpdate();

        } catch(SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;

        try{

            st = connection.prepareStatement("DELETE FROM MonthlyPayer WHERE codeMonthlyPayer = ?");

            st.setInt(1, id);

            st.executeUpdate();

        } catch(SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public MonthlyPayer findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{

            st = connection.prepareStatement("SELECT * FROM MonthlyPayer WHERE codeMonthlyPayer = ?");

            st.setInt(1, id);

            rs = st.executeQuery();

            if(rs.next()) {
                MonthlyPayer monthlyPayer = new MonthlyPayer();

                monthlyPayer.setCodeMonthlyPayer(rs.getInt(1));
                monthlyPayer.setMonthlyFee(rs.getDouble(2));
                monthlyPayer.setCurrentMonth(LocalDate.parse(rs.getString(3), formatter));
                monthlyPayer.setMonthlyFeePaid(rs.getBoolean(4));

                return monthlyPayer;
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
    public List<MonthlyPayer> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{

            st = connection.prepareStatement("SELECT * FROM MonthlyPayer");
            rs = st.executeQuery();

            List<MonthlyPayer> lista = new ArrayList<>();
            while(rs.next()) {
                MonthlyPayer monthlyPayer = new MonthlyPayer();

                monthlyPayer.setCodeMonthlyPayer(rs.getInt(1));
                monthlyPayer.setMonthlyFee(rs.getDouble(2));
                monthlyPayer.setCurrentMonth(LocalDate.parse(rs.getString(3), formatter));
                monthlyPayer.setMonthlyFeePaid(rs.getBoolean(4));

                lista.add(monthlyPayer);
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
