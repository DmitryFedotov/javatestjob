package services;

import dao.PositionDaoImpl;
import models.PositionsEntity;

import java.util.List;

public class PositionService {
    private PositionDaoImpl posDao = new PositionDaoImpl();
    private List<PositionsEntity> positions;

    public PositionService(){
        positions = getAllPos();
    }

    public List<PositionsEntity> getAllPos(){return posDao.getAll();}

    public String getPosition(PositionsEntity positionId){
        String result = "Должность не задана.";
        for (PositionsEntity pos : positions
        ) {
            if(pos.getId() == positionId.getId())
                result = pos.getName();

        }
        return result;
    }

}
