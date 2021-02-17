package com.fares.CRUDdemo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fares.CRUDdemo.Entity.Student;
import com.fares.CRUDdemo.Repository.IStudentRepository;

@Configuration
public class StudentConfig {

	@Bean
	CommandLineRunner commandLineRunner(IStudentRepository iStudentRepository) {
		return args -> {
			
		Student Fares = new Student("Fares","fares@gmail.com",LocalDate.of(1995, 9, 06),25);
		Student Test = new Student("Test","test@gmail.com",LocalDate.of(2000, 10, 5),20);
		
		iStudentRepository.saveAll(List.of(Fares,Test));
		};
	}
	
}
