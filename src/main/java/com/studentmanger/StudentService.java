package com.studentmanger;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public Student addStudent(Student student) {
        return repository.save(student);
    }

    public Student getStudentById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Student updateStudent(Integer id, Student updatedStudent) {
        Student student = repository.findById(id).orElse(null);

        if (student != null) {
            student.setName(updatedStudent.getName());
            student.setEmail(updatedStudent.getEmail());
            student.setAge(updatedStudent.getAge());

            return repository.save(student);
        }

        return null;
    }

    public void deleteStudent(Integer id) {
        repository.deleteById(id);
    }
}