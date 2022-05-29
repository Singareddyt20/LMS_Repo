package com.springboot.rest.example.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.rest.example.model.Book;
import com.springboot.rest.example.model.Student;
import com.springboot.rest.example.service.BookIssueService;
import com.springboot.rest.example.service.StudentService;

@RestController
@RequestMapping(path = "/students")
public class StudentController {

    @Autowired
    private StudentService studentService;
    
    @Autowired
    private BookIssueService bookIssueService;

    @PostMapping(path = "/add")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
    	Student student1 = studentService.addStudent(student);
        return ResponseEntity.ok(student1);
    }

    @PostMapping(path = "/update")
    public ResponseEntity<Student> updateStudent(
            @RequestBody Student student) {
    	System.out.println("Ragasri controller"+student.getStudentId()+","+student.getFirstName());
        Student student1 = studentService.updateStudent(student);
        return ResponseEntity.ok(student1);
    }
    @DeleteMapping(path = "/{studentId}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable(name = "studentId") Integer StudentId) {
    	String message="";
       boolean status=studentService.deleteStudent(StudentId);
       if(status) {
    	   message="Student with id "+StudentId+"deleted successfully";
       }
       else {
    	   message="Student with id "+StudentId+"not deleted";
       }
       return ResponseEntity.ok(message);
    }

    @GetMapping(path = "/{studentId}")
    public ResponseEntity<Student> getStudent(
            @PathVariable(name = "studentId") Integer studentId) {
        Student student = studentService.getStudentById(studentId);
        return ResponseEntity.ok(student);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<Page<Student>> getStudents(@PageableDefault(page = 0,
            size = 30) @SortDefault.SortDefaults({@SortDefault(sort = "modified",
                    direction = Sort.Direction.DESC)}) Pageable pageable) {
        Page<Student> students = studentService.getAllStudents(pageable);
        return ResponseEntity.ok(students);
    }
    
    @GetMapping(path = "/allstudents")
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
        
    }
    @GetMapping(path = "/{studentId}/search")
    public ResponseEntity<Student> searchStudent(@PathVariable(name = "studentId") Integer studentId) {
    	String message="";
      Student student=studentService.getStudentById(studentId);
       		     return ResponseEntity.ok(student);
    }
    
    @GetMapping(path = "/{studentId}/studentWithIssuedBooks")
    public ResponseEntity<Map<Student,List<Book>>> fetchStudentWithIssuedBooks(@PathVariable(name = "studentId") Integer studentId) {
    	//Student student1 = studentService.addStudent(student);
    	Map<Student,List<Book>> mapStudentToBook=bookIssueService.fetchStudentWithAllIssuedBooks(studentId);
        return ResponseEntity.ok(mapStudentToBook);
    }
    

}
