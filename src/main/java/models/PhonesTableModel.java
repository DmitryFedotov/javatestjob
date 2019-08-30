package models;

import services.PhonesService;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PhonesTableModel extends AbstractTableModel {

    private List<PhonesEntity> phones;
    private PhonesService phonesService = new PhonesService();
    private static final int COLUMN_COUNT = 2;

    public PhonesTableModel(List<PhonesEntity> phones){this.phones = phones;}

    @Override
    public int getRowCount() {
        return phones.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_COUNT;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex){
            case 0:
                return "Номер телефона";
            case 1:
                return "Тип";
        }
        return "";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PhonesEntity phone = phones.get(rowIndex);
        switch (columnIndex){
            case 0:
                return phone.getNumber();
            case 1:
                return phone.getType();
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
