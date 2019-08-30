package models;

import services.PositionService;
import services.ScheduleService;

import javax.swing.table.AbstractTableModel;
import java.util.Date;
import java.util.List;

public class EmpTableModel extends AbstractTableModel {

    private List<EmployeesEntity> employees;
    private ScheduleService scheduleService = new ScheduleService();
    private PositionService posService = new PositionService();
    private static final int COLUMN_COUNT = 9;

    public EmpTableModel(List<EmployeesEntity> employees){
        this.employees = employees;
    }

    @Override
    public int getRowCount() {
        return employees.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_COUNT;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex){
            case 0:
                return "Фамилия";
            case 1:
                return "Имя";
            case 2:
                return "Отчество";
            case 3:
                return "Адресс";
            case 4:
                return "Дата рождения";
            case 5:
                return "Комментарий";
            case 6:
                return "Должность";
            case 7:
                return "График работы";
            case 8:
                return "Список телефонов";
        }
        return "";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 4:
                return Date.class;
            default:
                return String.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        EmployeesEntity emp = employees.get(rowIndex);
        String str = "....";
        switch (columnIndex){
            case 0:
                return emp.getLastName();
            case 1:
                return emp.getFirstName();
            case 2:
                return emp.getMiddleName();
            case 3:
                return emp.getAdress();
            case 4:
                return emp.getBirthday();
            case 5:
                return emp.getComment();
            case 6:
                return posService.getPosition(emp.getPositionsByPositionId());
            case 7:
                return scheduleService.getSchedule(emp.getScheduleByScheduleId());
            case 8:
                return str;

        }
        return null;
    }
}
