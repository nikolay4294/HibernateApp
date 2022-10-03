package org.example;

import org.example.model.Actor;
import org.example.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class App {
    public static void main(String[] args) {

        //конфигурируем и указываем класс связанный с БД.
        //Так же автоматически подтягивается файл проперти.
//        Configuration configuration = new Configuration().addAnnotatedClass(Person.class).
//                addAnnotatedClass(Item.class);

        Configuration configuration = new Configuration().addAnnotatedClass(Movie.class).
                addAnnotatedClass(Actor.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {

            session.beginTransaction();

            Movie movie = new Movie("Джентельмены удачи", 1975);
            Actor actor = session.get(Actor.class, 2);

            movie.setActors(new ArrayList<>(Collections.singletonList(actor)));
            actor.getMovies().add(movie);

            session.save(movie);

            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
}
