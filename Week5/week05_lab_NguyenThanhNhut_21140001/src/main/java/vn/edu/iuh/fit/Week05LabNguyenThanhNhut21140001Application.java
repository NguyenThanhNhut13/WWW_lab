package vn.edu.iuh.fit;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

@SpringBootApplication
public class Week05LabNguyenThanhNhut21140001Application {

    public static void main(String[] args) {
        SpringApplication.run(Week05LabNguyenThanhNhut21140001Application.class, args);
    }

//    @Autowired
//    private DataSource dataSource;
//
//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println(dataSource.getConnection().getCatalog());
//    }
}
