package dao;

import java.util.List;

public interface PhonesDao {
    List getByEmpId(int id);
    List getByNumber(String number);
    List getAll();
}
