package model.dao;

import db.DB;
import model.dao.impl.DetachedDaoJDBC;
import model.dao.impl.MonthlyPayerDaoJDBC;
import model.dao.impl.VehicleDaoJDBC;

public class DaoFactory {
    public static DetachedDao createDetachedDao() {
        return new DetachedDaoJDBC(DB.getConnection());
    }

    public static MonthlyPayerDao createMonthlyPayerDao() {
        return new MonthlyPayerDaoJDBC(DB.getConnection());
    }

    public static VehicleDao createVehicleDao() {
        return new VehicleDaoJDBC(DB.getConnection());
    }
}
