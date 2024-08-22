package application;

import db.DB;
import db.DbException;

import java.sql.*;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) {
        Connection connection = null;
        Statement st = null;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try{

            connection = DB.getConnection();
            connection.setAutoCommit(false);
            st = connection.createStatement();

            /*
            st = connection.preparedStatement(
                          "DELETE FROM department "
                        + "WHERE "
                        + "Id = ?"
                    );

            st.setInt(1, 1);
            */

            int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");

            /*
            int x = 1;
            if (x < 2){
                throw new SQLException("Fake error");
            }
            */

            int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 2");

            connection.commit();

            System.out.println("Rows 1: " + rows1);
            System.out.println("Rows 2: " + rows2);

        } catch (SQLException e) {
            try {
                connection.rollback();
                throw new DbException("Transaction rolled back! Caused by: " + e.getMessage());
            } catch (SQLException e1) {
                throw new DbException("Error trying to rollback! Caused by: " + e1.getMessage());
            }
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}