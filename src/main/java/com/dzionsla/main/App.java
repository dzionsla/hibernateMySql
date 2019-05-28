package com.dzionsla.main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dzionsla.model.Student;
import com.dzionsla.persistance.HibernateUtil;

public class App {

	public static void main(String[] args) {

		Student student = new Student("Ram123123esh", "Fadat2312are", "rameshfadatare@javaguides.com");
        Student student1 = new Student("John", "Cena", "john@javaguides.com");
        Transaction transaction = null;
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            System.out.println("1");
            // save the student objects
            session.save(student);
            session.save(student1);
            System.out.println("2");
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }  
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List < Student > students = session.createQuery("from Student", Student.class).list();
            //students.forEach( s - > System.out.println(s.getFirstName()));
            for (Student std : students) {
            	System.out.println(std.getFirstName());
			}
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        
	}

}
