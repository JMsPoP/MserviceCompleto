package com.jmtsu.ms.course.endpoint.controller;


import com.jmtsu.ms.core.model.Course;

import com.jmtsu.ms.course.endpoint.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("v1/admin/course")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseController {
	private final CourseService courseService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Iterable<Course>> list(Pageable pageable){
		return new ResponseEntity<>(courseService.list(pageable), HttpStatus.OK);
	}
}

@RestController
@RequestMapping("/course")
class HttpController {
	@GetMapping("/public")
	String privateRoute( ) {
		return String.format("""
				<h1>Private route, only authorized personal! 🔐  </h1>
				""");
	}
}