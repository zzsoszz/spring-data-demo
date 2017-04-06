package com.qingtian.data;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Student {
	@GeneratedValue
	@Id
	Long id;
	
	String username;
	
	@ManyToMany
	List<Teacher> teachers;
	
	//交出维护权
	@OneToMany(mappedBy="student",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	List<Book> books;
	
	
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public void setUsername(String username) {
		this.username = username;
	}


}
