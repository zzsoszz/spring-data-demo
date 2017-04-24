package com.qingtian;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qingtian.data.Book;
import com.qingtian.data.BookResponsitory;
import com.qingtian.data.Student;
import com.qingtian.data.StudentResponsitory;
import com.qingtian.data.Teacher;
import com.qingtian.data.TeacherResponsitory;

@Component
public class SystemInitService {
	@Autowired
	TeacherResponsitory teacherResponsitory;
	@Autowired
	StudentResponsitory studentResponsitory;
	@Autowired
	BookResponsitory bookResponsitory;
	
	
	@Transactional
	public void init(){
		
		ArrayList<Student> students=new ArrayList<Student>();
		ArrayList<Teacher> teachers=new ArrayList<Teacher>();
		ArrayList<Book> books=new ArrayList<Book>();
		Book book=new Book();
		book.setName("我欲封天");
		Teacher teacher=new Teacher();
		teacher.setName("张老师");
		Student student=new Student();
		books.add(book);
		student.setBooks(books);
		student.setTeachers(teachers);
		student.setUsername("qingtian");
		students.add(student);
		teachers.add(teacher);
		teacher.setStudents(students);
		teacherResponsitory.save(teacher);
		studentResponsitory.save(student);
		book.setStudent(student);
		bookResponsitory.save(book);
		Student student1=studentResponsitory.findOne(1L);
		System.out.println(student1);
		System.out.println(student1.getUsername());
		for(Book one:student1.getBooks()){
			System.out.println(one.getName()+one.getId());
			System.out.println(one.getStudent().getUsername());
			for(Teacher two:one.getStudent().getTeachers())
			{
				System.out.println(two.getName()+two.getId());
			}
		}
		
		
	}
}
