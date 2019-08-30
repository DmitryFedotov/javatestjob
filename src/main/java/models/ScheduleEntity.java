package models;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "schedule", schema = "company")
public class ScheduleEntity {
    private int id;
    private String shifts;
    private int workTimeHours;
    private Collection<EmployeesEntity> employeesById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "shifts", nullable = false, length = 10)
    public String getShifts() {
        return shifts;
    }

    public void setShifts(String shifts) {
        this.shifts = shifts;
    }

    @Basic
    @Column(name = "work_time", nullable = false)
    public int getWorkTimeHours() {
        return workTimeHours;
    }

    public void setWorkTimeHours(int workTimeHours) {
        this.workTimeHours = workTimeHours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScheduleEntity that = (ScheduleEntity) o;

        if (id != that.id) return false;
        if (workTimeHours != that.workTimeHours) return false;
        if (shifts != null ? !shifts.equals(that.shifts) : that.shifts != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (shifts != null ? shifts.hashCode() : 0);
        result = 31 * result + workTimeHours;
        return result;
    }

    @OneToMany(mappedBy = "scheduleByScheduleId")
    public Collection<EmployeesEntity> getEmployeesById() {
        return employeesById;
    }

    public void setEmployeesById(Collection<EmployeesEntity> employeesById) {
        this.employeesById = employeesById;
    }
}
