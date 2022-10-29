package com.example.spatiallasertestproject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.List;
//import org.springframework.orm.jpa.JpaTemplate;

@SpringBootApplication
public class SpatialLaserTestProjectApplication {
    //private JpaTemplate jpaRemplate = new JpaTemplate();
//    private JdbcTemplate jdbcTemplate = new JdbcTemplate();
    public static void main(String[] args){
        SpringApplication.run(SpatialLaserTestProjectApplication.class, args);
    }

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

//    @Override
//    public void run(String[] args) throws Exception{
//        String sql = "SELECT * FROM table_a";
//
//        List<Address> addressList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Address.class));
//
//        System.out.println(addressList);
//    }

}
