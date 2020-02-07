package SkillBoxDB.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class PurchaseListPK implements Serializable {

    @Column(name = "student_name")
    String studentName;

    @Column(name = "course_name")
    String courseName;

    @SuppressWarnings("unused")
    PurchaseListPK() {}
}
