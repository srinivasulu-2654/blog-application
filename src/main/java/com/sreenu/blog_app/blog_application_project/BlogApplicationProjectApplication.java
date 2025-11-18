package com.sreenu.blog_app.blog_application_project;

import com.sreenu.blog_app.blog_application_project.config.AppConstants;
import com.sreenu.blog_app.blog_application_project.entities.Role;
import com.sreenu.blog_app.blog_application_project.repositories.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class BlogApplicationProjectApplication implements CommandLineRunner {

    @Autowired
    private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {

		SpringApplication.run(BlogApplicationProjectApplication.class, args);
	}

	@Bean   // (here @Configuration is not providing even though it will work why because there is @SpringBootApplication)
	public ModelMapper modelMapper() {
		return new ModelMapper();

	}

    @Override
    public void run(String... args) throws Exception {
        System.out.println(passwordEncoder.encode("xyz"));

		try{
			Role role = new Role();
			role.setId(AppConstants.ADMIN_USER);
			role.setName("ADMIN_USER");

			Role role1 = new Role();
			role1.setId(AppConstants.NORMAL_USER);
			role1.setName("NORMAL_USER");

			List<Role> roles = List.of(role,role1);

		List<Role> result = 	roleRepo.saveAll(roles);

		  result.forEach(r->{
			  System.out.println(r.getName());
		  });
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
