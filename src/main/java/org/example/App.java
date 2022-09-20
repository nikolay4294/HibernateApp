package org.example;


import org.example.model.Film;
import org.example.model.Item;
import org.example.model.Person;
import org.example.model.Producer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        //конфигурируем и указываем класс связанный с БД.
        //Так же автоматически подтягивается файл проперти.
//        Configuration configuration = new Configuration().addAnnotatedClass(Person.class).
//                addAnnotatedClass(Item.class);

        Configuration configuration = new Configuration().addAnnotatedClass(Producer.class).
                  addAnnotatedClass(Film.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

//            Person person = session.get(Person.class, 4);
//            Item item = session.get(Item.class, 1);
//            item.getOwner().getItems().remove(item);
//
//            item.setOwner(person);
//            person.getItems().add(item);

               Producer producer = session.get(Producer.class, 5);
               List<Film> films = producer.getFilms();

               for (Film f : films)
                   session.remove(f);

               producer.getFilms().clear();

               session.save(producer);


            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }
}
