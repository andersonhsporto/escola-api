package org.sed.escolaapi.course.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table(name = "courses")
public class Course {

  @Id
  @Column(name = "id")
  private Long id;

  @Column(name = "title")
  private String title;

  @Column(name = "credits")
  private int credits;

  public void updateCourse(String title, int credits) {
    this.title = title;
    this.credits = credits;
  }

}
