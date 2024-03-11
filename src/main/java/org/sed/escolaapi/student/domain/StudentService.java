package org.sed.escolaapi.student.domain;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class StudentService {

  private final StudentRepository studentRepository;

  public List<StudentDTO> getStudents() {
    List<Student> list = studentRepository.findAll();

    return StudentDTO.fromEntity(list);
  }

  public StudentDTO getStudentById(Long id) throws EntityNotFoundException {
    var student = studentRepository.getReferenceById(id);

    return StudentDTO.fromEntity(student);
  }

  public StudentDTO createStudent(StudentDTO student) throws BadRequestException {
    if (Objects.isNull(studentRepository.findByName(student.name()))) {
      var studentEntity = student.toEntity();

      studentRepository.save(studentEntity);
      return StudentDTO.fromEntity(studentEntity);
    }
    throw new BadRequestException("Student already exists");
  }

  public StudentDTO updateStudent(Long id, StudentDTO student) throws EntityNotFoundException {
    if (studentRepository.existsById(id)) {
      var studentEntity = studentRepository.findById(id).get();

      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      LocalDate date = LocalDate.parse(student.registrationDate(), formatter);

      studentEntity.update(student.name(), date);

      studentRepository.save(studentEntity);

      log.info("Entidade com id {} atualizada", id);
      return StudentDTO.fromEntity(studentEntity);
    }
    throw new EntityNotFoundException("Student not found");
  }

  @Transactional
  public void deleteStudent(Long id) throws EntityNotFoundException {
    if (!studentRepository.existsById(id)) {
      throw new EntityNotFoundException("Student not found");
    }
    log.info("Deletando entidade com id {}", id);
    studentRepository.deleteById(id);
  }
}
