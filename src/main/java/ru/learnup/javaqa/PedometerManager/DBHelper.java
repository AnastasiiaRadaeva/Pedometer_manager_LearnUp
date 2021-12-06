package ru.learnup.javaqa.PedometerManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import ru.learnup.javaqa.PedometerManager.entities.Day;

import java.util.List;

public class DBHelper {
    private SessionFactory sessionFactory; //открывает сессии работы с бд


    public DBHelper(String dbUrl, String dbUser, String dbPassword) {
        //        подготовка для hibernate
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        final Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        this.sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

    public List<Day> getAllPos() {
        try(Session session = sessionFactory.openSession()){
            final Query<Day> result = session.createQuery("from Day order by day asc", Day.class);
            return result.getResultList();
        }
    }

    public int getSteps(int day_num) {
        for (Day day : getAllPos()){
            if (day.getDay() == day_num){
                return day.getSteps();
            }
        }
        return -1;
    }

    public int getMaxDay() {
        int max = 0;
        int day_num = 0;
        for (Day day : getAllPos()){
            if (day.getSteps() >= max){
                max = day.getSteps();
                day_num = day.getDay();
            }
        }
        return day_num;
    }

    public void addDay(Day note) {
        int id = getIdent();
        note.setDay(++id);
        try(Session session = sessionFactory.openSession()){
            final Transaction transaction = session.beginTransaction();
            session.save(note);
            transaction.commit();
        }
    }

    public int updateDay(int day_num, int steps) {
        if (day_num > getIdent()){
            return -1;
        }
        try(Session session = sessionFactory.openSession()){
            final Transaction transaction = session.beginTransaction();
            for (Day day : getAllPos()) {
                if (day.getDay() == day_num){
                    day.setSteps(day.getSteps() + steps);
                    session.saveOrUpdate(day);
                    transaction.commit();
                    return 0;
                }
            }
        }
        return -1;
    }

    private int getIdent(){
        int day_num = 0;
        for (Day day : getAllPos()){
            if (day.getDay() > day_num){
                day_num = day.getDay();
            }
        }
        return day_num;
    }
}
