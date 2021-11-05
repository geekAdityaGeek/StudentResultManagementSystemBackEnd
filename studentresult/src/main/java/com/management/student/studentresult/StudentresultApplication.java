package com.management.student.studentresult;

import com.management.student.studentresult.dao.Role;
import com.management.student.studentresult.dao.User;
import com.management.student.studentresult.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Configuration;


@Configuration
@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class StudentresultApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentresultApplication.class, args);

	}

}
