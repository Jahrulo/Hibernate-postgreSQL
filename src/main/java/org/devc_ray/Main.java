package org.devc_ray;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            // 1. Set up hibernate
            SessionFactory sessionFactory = new Configuration()
                    .addAnnotatedClass(Student.class)
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

            // Create student object
            Student student = new Student();
            student.setName("Amish Karnani");
            student.setGrade(85);

            // Save the student
            session.persist(student);

            // Commit transaction
            session.getTransaction().commit();

            logger.info("Student saved successfully with ID: {}", student.getId());

            // Retrieve and display the saved student
            Student savedStudent = session.get(Student.class, student.getId());
            logger.info("Retrieved student: {}", savedStudent);
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