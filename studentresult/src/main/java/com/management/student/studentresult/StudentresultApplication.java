package com.management.student.studentresult;


import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import com.management.student.studentresult.utils.FileFormatUtils;


@Configuration
@SpringBootApplication
public class StudentresultApplication {

    public static void main(String[] args) {
    	try {
			FileFormatUtils.readFile();
	        SpringApplication.run(StudentresultApplication.class, args);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

}
