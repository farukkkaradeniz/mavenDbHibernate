/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavendbhibernate2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author faruk
 */
@SpringBootApplication
public class Application {

    Configuration cfg;
    SessionFactory sessionFactory;
    Session session;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

    private void initializeConnectionDb() {

        cfg = new Configuration().configure();
        sessionFactory = cfg.buildSessionFactory();
        if (session == null) {
            session = sessionFactory.openSession();
        } else {
            session = sessionFactory.getCurrentSession();
        }
        session.beginTransaction();

    }

    @Bean
    public CommandLineRunner run() {

        return args -> {
            initializeConnectionDb();

            Person person = new Person("faruk", "karadeniz");
            insertNewPerson(person);

        };

    }

    public void insertNewPerson(Person person) {

        session.save(person);
        session.getTransaction().commit();

    }

    public void updatePersonById(Person person, int id) {
        person.setId(id);
        session.update(person);
        session.getTransaction().commit();

    }

    public void deletePersonById(int id) {

        Person person = new Person();
        person.setId(id);
        session.delete(person);
        session.getTransaction().commit();

    }

}
