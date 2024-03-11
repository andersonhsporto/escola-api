package org.sed.escolaapi.student.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public record StudentDTO(

    @JsonProperty("id")
    Long id,

    @JsonProperty("nome")
    String name,

    @JsonProperty("data_matricula")
    @NotEmpty(message = "Data de matrícula não pode ser vazia")
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$", message = "Data de matrícula inválida ( utilize o formato dd/MM/yyyy )")
    String registrationDate

) {

    public static StudentDTO fromEntity(Student student) {
        String date = student.getRegistrationDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        return new StudentDTO(student.getId(), student.getName(), date);
    }

    public static List<StudentDTO> fromEntity(List<Student> students) {
        return students.stream().map(StudentDTO::fromEntity).toList();
    }

    public Student toEntity() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(registrationDate, formatter);

        return new Student(null, name, date, List.of());
    }
}
