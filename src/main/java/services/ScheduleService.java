package services;

import dao.ScheduleDaoImpl;
import models.ScheduleEntity;

import java.util.List;

public class ScheduleService {
    private ScheduleDaoImpl scheduleDao = new ScheduleDaoImpl();
    private List<ScheduleEntity> schedules;

    public ScheduleService(){
        schedules = getAllSchedules();
    }

    public List<ScheduleEntity> getAllSchedules(){return scheduleDao.getAll();}

    public String getSchedule(ScheduleEntity sch){
        String result = "График не задан.";
        for(ScheduleEntity schedule : schedules){
            if(schedule.getId() == sch.getId())
                result = schedule.getShifts() + " по " + schedule.getWorkTimeHours() + " часов";
        }

        return result;
    }
}
