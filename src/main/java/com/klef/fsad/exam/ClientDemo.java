package com.klef.fsad.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class ClientDemo {
    public static void main(String[] args) {
        // Initialize SessionFactory
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");

        try (SessionFactory sessionFactory = cfg.buildSessionFactory()) {
            // Operation I: Insert a new record
            int insertedId = insertCourse(sessionFactory);
            System.out.println("\n-------------------------------------------");
            System.out.println("Operation I: Course Inserted with ID: " + insertedId);
            System.out.println("-------------------------------------------\n");

            // Operation II: View the record based on ID
            viewCourseById(sessionFactory, insertedId);

        } catch (Exception e) {
            System.err.println("Execution Error: " + e.getMessage());
        }
    }

    private static int insertCourse(SessionFactory sessionFactory) {
        int courseId = 0;

        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                Course course = new Course();
                course.setName("Enterprise Programming");
                course.setDescription("Advanced Java and Hibernate Development");
                course.setDate("2024-05-02");
                course.setStatus("Completed");

                session.persist(course);
                tx.commit();
                courseId = course.getId();
            } catch (Exception e) {
                if (tx != null) tx.rollback();
                throw e;
            }
        } catch (Exception e) {
            System.err.println("Insert Error: " + e.getMessage());
        }
        return courseId;
    }

    private static void viewCourseById(SessionFactory sessionFactory, int id) {
        try (Session session = sessionFactory.openSession()) {
            Course course = session.get(Course.class, id);

            System.out.println("-------------------------------------------");
            if (course != null) {
                System.out.println("Operation II: Course Details Found:");
                System.out.println("ID: " + course.getId());
                System.out.println("Name: " + course.getName());
                System.out.println("Description: " + course.getDescription());
                System.out.println("Date: " + course.getDate());
                System.out.println("Status: " + course.getStatus());
            } else {
                System.out.println("Operation II: Course not found with ID: " + id);
            }
            System.out.println("-------------------------------------------\n");
        } catch (Exception e) {
            System.err.println("View Error: " + e.getMessage());
        }
    }
}
