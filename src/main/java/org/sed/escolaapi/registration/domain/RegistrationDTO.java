package org.sed.escolaapi.registration.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.sed.escolaapi.course.domain.CourseDTO;
import org.sed.escolaapi.student.domain.StudentDTO;

public record RegistrationDTO(

    @JsonProperty("id")
    Long id,

    @JsonProperty("estudante")
    StudentDTO student,

    @JsonProperty("curso")
    CourseDTO course
) {

  public static RegistrationDTO fromEntity(Registration registration) {
    return new RegistrationDTO(
        registration.getId(),
        StudentDTO.fromEntity(registration.getStudent()),
        CourseDTO.fromEntity(registration.getCourse())
    );
  }

  public static List<RegistrationDTO> fromEntity(List<Registration> registrations) {
    return registrations.stream().map(RegistrationDTO::fromEntity).toList();
  }

}
