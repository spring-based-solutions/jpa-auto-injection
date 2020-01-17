package com.rjh.jpa.repository;

import com.rjh.jpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Null
 * @date 2020-01-13
 */
public interface StudentRepository extends JpaRepository<Student,Long> {

}
