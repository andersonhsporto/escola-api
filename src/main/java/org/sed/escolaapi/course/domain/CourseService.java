package org.sed.escolaapi.course.domain;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CourseService {

  private final CourseRepository courseRepository;

  private static final String COURSE_NOT_FOUND = "Course not found";


  public List<CourseDTO> getCourses() {
    List<Course> list = courseRepository.findAll(Sort.by(Sort.Direction.ASC, "title"));

    return CourseDTO.fromEntity(list);
  }

  public CourseDTO getCourseById(Long id) throws BadRequestException {
    var course = courseRepository.findById(id)
        .orElseThrow(() -> new BadRequestException(COURSE_NOT_FOUND));

    return CourseDTO.fromEntity(course);
  }


  public CourseDTO createCourse(CourseDTO course) throws BadRequestException {
    if (Objects.isNull(courseRepository.findByTitle(course.title()))) {
      var courseEntity = course.toEntity();

      courseRepository.save(courseEntity);
      return CourseDTO.fromEntity(courseEntity);
    }
    throw new BadRequestException("Course already exists");
  }

  public CourseDTO updateCourse(Long id, CourseDTO course) throws BadRequestException {
    var courseEntity = courseRepository.findById(id)
        .orElseThrow(() -> new BadRequestException(COURSE_NOT_FOUND));

    courseEntity.updateCourse(course.title(), course.credits());
    courseRepository.save(courseEntity);

    return CourseDTO.fromEntity(courseEntity);
  }


  @Transactional
  public void deleteCourse(Long id) throws BadRequestException {
    var courseEntity = courseRepository.findById(id)
        .orElseThrow(() -> new BadRequestException(COURSE_NOT_FOUND));

    courseRepository.delete(courseEntity);
  }
}
