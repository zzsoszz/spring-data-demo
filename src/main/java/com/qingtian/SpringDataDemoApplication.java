package com.qingtian;
/*
 * http://docs.jboss.org/hibernate/annotations/3.4/reference/zh_cn/html_single/#entity-mapping-association
 * 在EJB3规范中多对一这端几乎总是双向关联中的主体(owner)端, 而一对多这端的关联注解为@OneToMany( mappedBy=... )
 */
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

import com.qingtian.data.Book;
import com.qingtian.data.BookResponsitory;
import com.qingtian.data.Student;
import com.qingtian.data.StudentResponsitory;
import com.qingtian.data.Teacher;
import com.qingtian.data.TeacherResponsitory;


@SpringBootApplication
public class SpringDataDemoApplication  implements ApplicationListener<ApplicationReadyEvent> {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataDemoApplication.class, args);
	}
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent e) {
		
		TeacherResponsitory teacherResponsitory = e.getApplicationContext().getBean(TeacherResponsitory.class);
		StudentResponsitory studentResponsitory = e.getApplicationContext().getBean(StudentResponsitory.class);
		ArrayList<Student> students=new ArrayList<Student>();
		ArrayList<Teacher> teachers=new ArrayList<Teacher>();
		ArrayList<Book> books=new ArrayList<Book>();
		Book book=new Book();
		book.setName("woyufengtian");
		
		Teacher teacher=new Teacher();
		teacher.setName("zhanglaoshi");
		Student student=new Student();
		books.add(book);
		student.setBooks(books);
		student.setTeachers(teachers);
		student.setUsername("qingtian");
		book.setStudent(student);
		students.add(student);
		teachers.add(teacher);
		teacher.setStudents(students);
		teacherResponsitory.save(teacher);
		studentResponsitory.save(student);
		BookResponsitory bookResponsitory = e.getApplicationContext().getBean(BookResponsitory.class);
		bookResponsitory.save(book);
		
		Student student1=studentResponsitory.findOne(1L);
		System.out.println(student1);
		System.out.println(student1.getUsername());
		System.out.println(student1.getBooks());
	}
	
}
