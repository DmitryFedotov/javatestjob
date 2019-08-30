package models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employees", schema = "company")
public class EmployeesEntity {
    private int id;
    private String lastName;
    private String firstName;
    private String middleName;
    private String adress;
    private java.util.Date birthday;
    private String comment;
    private PositionsEntity positionsByPositionId;
    private ScheduleEntity scheduleByScheduleId;

    public EmployeesEntity(){
    }

    public EmployeesEntity(String lastName, String firstName, String middleName, String adress, Date birthday, String comment,
                           PositionsEntity positionsByPositionId, ScheduleEntity scheduleByScheduleId) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.adress = adress;
        this.birthday = birthday;
        this.comment = comment;
        this.positionsByPositionId = positionsByPositionId;
        this.scheduleByScheduleId = scheduleByScheduleId;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "last_name", nullable = false, length = 20)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "first_name", nullable = false, length = 20)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "middle_name", nullable = false, length = 20)
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Basic
    @Column(name = "adress", nullable = false, length = 50)
    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "birthday", nullable = false)
    public java.util.Date getBirthday() {
        return birthday;
    }

    public void setBirthday(java.util.Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "comment", nullable = true, length = 50)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeesEntity that = (EmployeesEntity) o;

        if (id != that.id) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (middleName != null ? !middleName.equals(that.middleName) : that.middleName != null) return false;
        if (adress != null ? !adress.equals(that.adress) : that.adress != null) return false;
        if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (adress != null ? adress.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "position_id", referencedColumnName = "id", nullable = true)
    public PositionsEntity getPositionsByPositionId() {
        return positionsByPositionId;
    }

    public void setPositionsByPositionId(PositionsEntity positionsByPositionId) {
        this.positionsByPositionId = positionsByPositionId;
    }

    @ManyToOne
    @JoinColumn(name = "schedule_id", referencedColumnName = "id", nullable = true)
    public ScheduleEntity getScheduleByScheduleId() {
        return scheduleByScheduleId;
    }

    public void setScheduleByScheduleId(ScheduleEntity scheduleByScheduleId) {
        this.scheduleByScheduleId = scheduleByScheduleId;
    }

}
