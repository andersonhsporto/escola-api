package org.sed.escolaapi.registration.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.sed.escolaapi.course.domain.Course;
import org.sed.escolaapi.student.domain.Student;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table(name = "registration")
public class Registration {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER)
  private Course course;

  @ManyToOne(fetch = FetchType.EAGER)
  private Student student;

  public Registration(Course course, Student student) {
    this.course = course;
    this.student = student;
  }

  public void update(Course course, Student student) {
    this.student = student;
    this.course = course;
  }

}
