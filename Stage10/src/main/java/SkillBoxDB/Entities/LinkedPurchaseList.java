package SkillBoxDB.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@AllArgsConstructor
@Table(name = "linkedpurchaselist")
public class LinkedPurchaseList {

    @EmbeddedId
    private LinkedPurchaseListPK id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Student student;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Course course;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "course_name")
    private String courseName;

    private int price;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

}
