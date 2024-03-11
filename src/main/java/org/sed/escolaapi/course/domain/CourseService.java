package org.sed.escolaapi.course.domain;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class CourseService {

  private static final String COURSE_NOT_FOUND = "Course not found";

  private final CourseRepository courseRepository;

  public List<CourseDTO> getCourses() {
    List<Course> list = courseRepository.findAll(Sort.by(Sort.Direction.ASC, "title"));

    return CourseDTO.fromEntity(list);
  }

  public CourseDTO getCourseById(Long id) throws EntityNotFoundException {
    var course = courseRepository.getReferenceById(id);

    return CourseDTO.fromEntity(course);
  }


  public CourseDTO createCourse(CourseDTO course) throws BadRequestException {
    if (Objects.isNull(courseRepository.findByTitle(course.title()))) {
      var courseEntity = course.toEntity();

      courseRepository.save(courseEntity);
      return CourseDTO.fromEntity(courseEntity);
    }
    log.info("Curso já existe");
    throw new BadRequestException("Curso já existe");
  }

  public CourseDTO updateCourse(Long id, CourseDTO course) throws EntityNotFoundException {
    var courseEntity = courseRepository.findById(id)
        .orElseThrow(() -> {
          log.info("Curso não encontrado");
          return new EntityNotFoundException(COURSE_NOT_FOUND);
        });

    courseEntity.updateCourse(course.title(), course.credits());
    courseRepository.save(courseEntity);

    return CourseDTO.fromEntity(courseEntity);
  }


  @Transactional
  public void deleteCourse(Long id) throws EntityNotFoundException {
    var courseEntity = courseRepository.findById(id)
        .orElseThrow(
            () -> {
              log.info("Curso não encontrado");
              return new EntityNotFoundException(COURSE_NOT_FOUND);
            });

    courseRepository.delete(courseEntity);
  }
}
