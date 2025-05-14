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
            student.setName("Tutu Tuts");
            student.setGrade(60);

           // Save the student
           session.persist(student);
           logger.info("Student saved successfully with ID: {}", student.getId());

            // Load or Get the student by ID
            Student stud1 = session.get(Student.class, 5);

            if (stud1 != null) {
                stud1.setName("Marish Jay");  // Modify data
                stud1.setGrade(88);

                // Merge (update) the student
                Student updatedStudent = session.merge(stud1);
                logger.info("Updated Student: {}", updatedStudent);
            } else {
                logger.info("Student not found!");
            }

            // Load or Get the student by ID
            Student stud2 = session.get(Student.class, 8);

            if (stud2 != null) {
                session.remove(stud2);  // Delete the student
                logger.info("Deleted Student: {}", stud2);
            } else {
                logger.info("Student not found!");
            }

            // Commit transaction
            session.getTransaction().commit();

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