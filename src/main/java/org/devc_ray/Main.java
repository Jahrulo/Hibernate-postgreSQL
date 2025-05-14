package org.devc_ray;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            // 1. Set up hibernate
            SessionFactory sessionFactory = new Configuration()
                    .addAnnotatedClass(Student.class)
                    .addAnnotatedClass(Laptop.class)
                    .configure("hibernate.cfg.xml")
                    .buildSessionFactory();

            // 2. Create and save a student
            createAndSaveStudent(sessionFactory);

        } catch (Exception e) {
            logger.error("Error in Hibernate operation", e);
        }
    }

    private static void createAndSaveStudent(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            // Begin transaction
            session.beginTransaction();

            // Create Laptop object
            Laptop lap1 = new Laptop();
            lap1.setBrand("Apple Macbook Pro");
            lap1.setRam(32);


            Laptop lap2 = new Laptop();
            lap2.setBrand("Dell XPS");
            lap2.setRam(16);


            // Create Address object
            Address add1 = new Address();
            add1.setStreet("110 Wilkinson Road");
            add1.setCity("Freetown");

            // Create Student object
            Student student = new Student();
            student.setName("Mickey Singh");
            student.setGrade(90);
            student.setAddress(add1);
            student.setLaptops(Arrays.asList(lap1, lap2));

            lap1.setStudent(student);
            lap2.setStudent(student);

           // Save the laptop & student
           session.persist(lap1);
           session.persist(lap2);
           session.persist(student);
           logger.info("Student saved successfully with ID: {}", student.getId());

           // Load or Get the student by ID
            Student stud = session.get(Student.class, 1);
            logger.info("Student retrieved successfully : {}", stud); // display student



            // Commit transaction
            session.getTransaction().commit();

            // Using HQL to read specific student data
            List<Object[]> studentData = session.createQuery(
                            "select s.name, s.Address.city from Student s", Object[].class)  // Note: Student.class changed to Object[].class
                    .list();

            for (Object[] row : studentData) {
                System.out.println("Name: " + row[0] + ", City: " + row[1]);
            }

            // Using HQL to read specific student data
            List<Object[]> studentData2 = session.createQuery(
                            "select s.name, s.Address.city, l.brand " +
                                    "from Student s " +
                                    "join s.Laptops l", Object[].class)  // Note lowercase 'l' in 's.laptops'
                    .list();

            for (Object[] row : studentData2) {
                System.out.println("Name: " + row[0] + ", City: " + row[1] + ", Laptop: " + row[2]);
            }







        }catch (Exception e) {
            logger.error("Error in Hibernate operation", e);
        } finally {
            // 3. Clean up resources
            if (sessionFactory != null) {
                try {
                    sessionFactory.close();
                    logger.info("SessionFactory closed successfully");
                } catch (Exception e) {
                    logger.error("Error closing SessionFactory", e);
                }
            }
        }
    }
}