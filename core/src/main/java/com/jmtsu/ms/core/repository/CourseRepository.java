package com.jmtsu.ms.core.repository;

import com.jmtsu.ms.core.model.Course;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {

}
