package phone.book.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;
import phone.book.PersonContact;

@org.springframework.context.annotation.Configuration
public class HibernateConfiguration {

    @Bean
    public SessionFactory sessionFactory() {
        var configuration = new Configuration()
                .addAnnotatedClass(PersonContact.class)
                .configure();
        return configuration.buildSessionFactory();
    }
    }
