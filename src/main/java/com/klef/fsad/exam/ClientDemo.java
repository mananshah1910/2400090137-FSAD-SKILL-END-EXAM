package com.klef.fsad.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.Date;
import java.util.Scanner;

public class ClientDemo {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // 1. Insert a new record
        insertCourse(sessionFactory);

        // 2. View record based on ID
        viewCourseById(sessionFactory);

        sessionFactory.close();
    }

    private static void insertCourse(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Course course = new Course();
        course.setName("Full Stack Application Development");
        course.setDescription("Advanced Java and Web Technologies");
        course.setDate(new Date());
        course.setStatus("Active");

        session.persist(course);
        transaction.commit();
        System.out.println("Course Inserted Successfully with ID: " + course.getId());
        session.close();
        
        // Storing the ID for demonstration of the second part
        latestInsertedId = course.getId();
    }

    private static int latestInsertedId = -1;

    private static void viewCourseById(SessionFactory sessionFactory) {
        if (latestInsertedId == -1) {
            System.out.println("No course inserted yet.");
            return;
        }
        
        System.out.println("Fetching Course with ID: " + latestInsertedId);
        
        Session session = sessionFactory.openSession();
        Course course = session.get(Course.class, latestInsertedId);

        if (course != null) {
            System.out.println("Course Details:");
            System.out.println("ID: " + course.getId());
            System.out.println("Name: " + course.getName());
            System.out.println("Description: " + course.getDescription());
            System.out.println("Date: " + course.getDate());
            System.out.println("Status: " + course.getStatus());
        } else {
            System.out.println("Course with ID " + latestInsertedId + " not found.");
        }
        session.close();
    }
}
