package SkillBoxDB.Entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Formatter;
@Data
@Entity
@Table(name = "purchaselist")
public class PurchaseList implements Serializable {

    @EmbeddedId
    private PurchaseListPK id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_name", referencedColumnName = "name", insertable = false, updatable = false)
    private Student student;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_name", referencedColumnName = "name", insertable = false, updatable = false)
    private Course course;

    @Column(name = "price")
    private int price;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    @Override
    public String toString(){
        return new Formatter().format("PurchaseList (studentName: %s, courseName: %s, subscriptionDate: %3$td.%3$tm.%3$tY)",
                id.studentName, id.courseName, subscriptionDate).toString();
    }
}
