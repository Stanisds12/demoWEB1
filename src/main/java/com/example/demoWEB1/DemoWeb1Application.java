package com.example.demoWEB1;

 



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;



//import com.example.demoWEB1.model.Teacher;
import com.example.demoWEB1.service.TeacherService;









@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication



public class DemoWeb1Application  {
	



	private static TeacherService tc;
	public DemoWeb1Application(TeacherService tc) {
		 DemoWeb1Application.tc=tc;
	 }
	
	public static void main(String[] args) {
	SpringApplication.run(DemoWeb1Application.class, args);
		//tc.create(new Teacher(tc.list().size()+1, "Dorivaldo", "Laravel"));
	
	  System.out.println(tc.getTeacher(3));
	 // tc.delete(4);
	 //System.out.println(tc.list());
	
	}

}
