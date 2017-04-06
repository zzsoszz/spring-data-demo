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
		Teacher teacher=new Teacher();
		teacher.setName("zhanglaoshi");
		ArrayList<Student> students=new ArrayList<Student>();
		Student student=new Student();
		List<Book> books=new ArrayList<Book>();
		students.add(student);
		teacher.setStudents(students);
		teacherResponsitory.save(teacher);
		
		
		BookResponsitory bookResponsitory = e.getApplicationContext().getBean(BookResponsitory.class);
		Book book=new Book();
		book.setName("语文");
		book.setStudent(student);
		books.add(book);
		Book booknew=bookResponsitory.findOne(1L);
		System.out.println(booknew);
		
//		System.out.println(booknew.getName());
//		System.out.println(booknew.getStudent().getId());
		
	}
	
}
