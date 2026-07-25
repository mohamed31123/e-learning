package com.school.elearning.repository;

import com.school.elearning.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository  extends JpaRepository<Lesson, Long> {

}
