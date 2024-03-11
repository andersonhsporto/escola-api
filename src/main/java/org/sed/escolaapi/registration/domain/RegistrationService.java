package org.sed.escolaapi.registration.domain;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.sed.escolaapi.course.domain.Course;
import org.sed.escolaapi.course.domain.CourseRepository;
import org.sed.escolaapi.student.domain.Student;
import org.sed.escolaapi.student.domain.StudentRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class RegistrationService {

  private final RegistrationRepository registrationRepository;

  private final CourseRepository courseRepository;

  private final StudentRepository studentRepository;

  private static final String COURSE_NOT_FOUND = "Curso não encontrado";
  private static final String STUDENT_NOT_FOUND = "Estudante não encontrado";

  private static final String REGISTRATION_NOT_FOUND = "Matricula não encontrada";

  public List<RegistrationDTO> getRegistrations() {
    return RegistrationDTO.fromEntity(registrationRepository.findAll());
  }


  public RegistrationDTO getRegistrationById(Long id) throws EntityNotFoundException {
    var registration = registrationRepository.getReferenceById(id);

    return RegistrationDTO.fromEntity(registration);
  }


  public RegistrationDTO createRegistration(RegistrationCreationDTO registration)
      throws BadRequestException {
    if (registrationRepository.findByStudentIdAndCourseId(registration.studentId(),
        registration.courseId()).isEmpty()) {

      if (!courseRepository.existsById(registration.courseId())) {
        log.info(COURSE_NOT_FOUND);
        throw new BadRequestException(COURSE_NOT_FOUND);
      }

      if (!studentRepository.existsById(registration.studentId())) {
        log.info(STUDENT_NOT_FOUND);
        throw new BadRequestException(STUDENT_NOT_FOUND);
      }

      var course = courseRepository.findById(registration.courseId()).orElseThrow(EntityNotFoundException::new);
      var student = studentRepository.findById(registration.studentId()).orElseThrow(EntityNotFoundException::new);

      Registration registrationEntity = new Registration(course, student);

      registrationRepository.save(registrationEntity);
      return RegistrationDTO.fromEntity(registrationEntity);
    }
    log.info("Matricula já existe");
    throw new BadRequestException("Matricula já existe");
  }

  public RegistrationDTO updateRegistration(Long id, RegistrationCreationDTO registration)
      throws BadRequestException {
    if (registrationRepository.existsById(id)) {

      if (!courseRepository.existsById(registration.courseId())) {
        log.info(COURSE_NOT_FOUND);
        throw new BadRequestException(COURSE_NOT_FOUND);
      }

      if (!studentRepository.existsById(registration.studentId())) {
        log.info(STUDENT_NOT_FOUND);
        throw new BadRequestException(STUDENT_NOT_FOUND);
      }

      Course course = courseRepository.getReferenceById(registration.courseId());
      Student student = studentRepository.getReferenceById(registration.studentId());

      Registration registrationEntity = registrationRepository.findById(id).get();

      registrationEntity.update(course, student);

      registrationRepository.save(registrationEntity);
      return RegistrationDTO.fromEntity(registrationEntity);
    }
    log.info(REGISTRATION_NOT_FOUND);
    throw new EntityNotFoundException(REGISTRATION_NOT_FOUND);
  }

  @Transactional
  public void deleteRegistration(Long id) {
    if (registrationRepository.existsById(id)) {
      registrationRepository.deleteById(id);
    }
    log.info(REGISTRATION_NOT_FOUND);
    throw new EntityNotFoundException(REGISTRATION_NOT_FOUND);
  }
}
