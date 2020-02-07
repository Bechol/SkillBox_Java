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
public class LinkedPurchaseListPK implements Serializable {

    @Column(name = "student_id")
    int studentId;

    @Column(name = "course_id")
    int courseId;

    @SuppressWarnings("unused")
    LinkedPurchaseListPK() {}
}
