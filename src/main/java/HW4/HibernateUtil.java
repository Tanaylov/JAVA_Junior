package HW4;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.File;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static final File fileHibernateCfg = new File("src\\main\\resources\\hibernate.cfg.xml");

    static {
        final StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources(ssr).buildMetadata().buildSessionFactory();
//            sessionFactory = new Configuration().configure(fileHibernateCfg).buildSessionFactory();
        } catch (Exception e) {
//            System.err.println(e.getMessage());
            StandardServiceRegistryBuilder.destroy(ssr);
        }
    }

    public static SessionFactory getSessionFactory() {return sessionFactory;}

    public static void close() {
        getSessionFactory().close();
    }
}
