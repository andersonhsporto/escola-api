package org.sed.escolaapi.course.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

public record CourseDTO(

    @JsonProperty("titulo")
    @NotEmpty(message = "O título é obrigatório")
    String title,

    @JsonProperty("creditos")
    @NotNull(message = "Os creditos são obrigatórios")
    @Positive(message = "Os creditos devem ser um número positivo")
    Integer credits,

    @JsonProperty("id")
    Long id) {

  public static CourseDTO fromEntity(Course course) {
    return new CourseDTO(course.getTitle(), course.getCredits(), course.getId());
  }

  public static List<CourseDTO> fromEntity(List<Course> courses) {
    return courses.stream().map(CourseDTO::fromEntity).collect(Collectors.toList());
  }

  public Course toEntity() {
    return new Course(id, title, credits, List.of());
  }

}
