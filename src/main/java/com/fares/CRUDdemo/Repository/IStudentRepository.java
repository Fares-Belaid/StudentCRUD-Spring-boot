package com.fares.CRUDdemo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fares.CRUDdemo.Entity.Student;


@Repository
public interface IStudentRepository extends JpaRepository<Student, Long> {

	@Query("select s from Student s where s.email = ?1")
	Optional<Student> findByEmail(String email);

}
