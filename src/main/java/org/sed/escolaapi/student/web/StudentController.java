package org.sed.escolaapi.student.web;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.sed.escolaapi.student.domain.StudentDTO;
import org.sed.escolaapi.student.domain.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/estudante")
public class StudentController {

  private final StudentService studentService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<StudentDTO> getStudents() {
    return studentService.getStudents();
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public StudentDTO getStudentById(@PathVariable Long id) throws EntityNotFoundException {
    return studentService.getStudentById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public StudentDTO createStudent(@RequestBody @Valid StudentDTO student)
      throws BadRequestException {
    return studentService.createStudent(student);
  }

  @PatchMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public StudentDTO updateStudent(@PathVariable Long id, @Valid @RequestBody StudentDTO student)
      throws BadRequestException {
    return studentService.updateStudent(id, student);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteStudent(@PathVariable Long id) throws EntityNotFoundException {
    studentService.deleteStudent(id);
  }

  @ExceptionHandler(BadRequestException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String handleBadRequestException(BadRequestException e) {
    log.info("Requisição inválida {}", e.getMessage());
    return "Requisição inválida";
  }

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String studentNotFoundException(EntityNotFoundException ex) {
    log.info("Estudante não encontrado {}", ex.getMessage());
    return "Estudante não encontrado";
  }


}
