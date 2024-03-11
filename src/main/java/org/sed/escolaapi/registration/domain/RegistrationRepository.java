package org.sed.escolaapi.registration.domain;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

  Collection<Object> findByStudentIdAndCourseId(Long aLong, Long aLong1);
}
