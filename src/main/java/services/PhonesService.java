package services;

import dao.PhonesDaoImpl;
import models.PhonesEntity;

import java.util.List;

public class PhonesService {
    private PhonesDaoImpl phonesDao = new PhonesDaoImpl();

    public PhonesService(){}
    public boolean create(PhonesEntity phone){
        boolean result = true;
        if(!phonesDao.create(phone))
            result = false;
        return result;
    }

    public boolean update(PhonesEntity phone){
        boolean result = true;
        if(!phonesDao.update(phone))
            result = false;
        return result;
    }

    public boolean delete(PhonesEntity phone){
        boolean result = true;
        if(!phonesDao.delete(phone))
            result = false;
        return result;
    }

    public List<PhonesEntity> getPhonesById(int id){
       return phonesDao.getByEmpId(id);
    }


    public int getEmpByNumber(String number){
        List<PhonesEntity> phones =  phonesDao.getByNumber(number);
        PhonesEntity phone = phones.get(0);
        return phone.getEmployeesByEmployeeId().getId();
    }

    public boolean isValid(String number){
        return number.matches("^(8|\\+7)(\\d{10})");
    }
}
