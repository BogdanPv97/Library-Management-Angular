package com.example.demo;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import com.github.javafaker.Faker;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

//	@Bean
//	CommandLineRunner commandLineRunner(
//			BookRepository bookRepository) {
//		return args -> {
//			Faker faker = new Faker();
//
//
//			for(int i=1;i<=100;i++){
//				Book b=new Book(faker.book().title(),faker.book().author(),faker.book().genre(),(int)(Math.floor(Math.random()*2000+1000)));
//
//				bookRepository.save(b);
//			}
//
//
//		};
//	}
}
