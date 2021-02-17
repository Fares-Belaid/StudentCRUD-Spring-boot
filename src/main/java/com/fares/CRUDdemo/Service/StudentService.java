package com.fares.CRUDdemo.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fares.CRUDdemo.Entity.Student;
import com.fares.CRUDdemo.Repository.IStudentRepository;


@Service
public class StudentService {

	private final IStudentRepository iStudentRepository;
	
	@Autowired
	public StudentService(IStudentRepository iStudentRepository) {
		super();
		this.iStudentRepository = iStudentRepository;
	}


	public List<Student> getStudents() {
		return iStudentRepository.findAll();
	}



	public void addNewStudent(Student student) {
		Optional<Student> studentByEmail = iStudentRepository.findByEmail(student.getEmail());
		if (studentByEmail.isPresent()) {
			throw new IllegalStateException("email taken");
		}
		iStudentRepository.save(student);
	}


	public void deleteStudent(Long studentId) {
		iStudentRepository.findById(studentId);
		boolean exists = iStudentRepository.existsById(studentId);
		if (!exists) {
			throw new IllegalStateException("student with id "+studentId + " does not exist");
		}
		iStudentRepository.deleteById(studentId);
	}


	@Transactional
	public void updateStudent(Long studentId, String name, String email) {
		Student student = iStudentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("student with id "+studentId + " does not exist"));
		
		if (name != null && name.length()>0 && !Objects.equals(student.getName(), name)) {
			student.setName(name);
			
		}
		if (email != null && email.length()>0 && !Objects.equals(student.getEmail(), email)) {
			Optional<Student> studentOptinal = iStudentRepository.findByEmail(email);
			if (studentOptinal.isPresent()) {
				throw new IllegalStateException("email taken");
				
			}
			student.setEmail(email);
			
		}
	}
	
}
