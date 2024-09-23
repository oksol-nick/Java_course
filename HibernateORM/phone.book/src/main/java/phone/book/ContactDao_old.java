package phone.book;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ContactDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public ContactDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public long addContact(PersonContact contact) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            var id = (long) session.save(contact);
            transaction.commit();
            return id;
        }
    }

    public PersonContact getContactById(long id) {
        try (var session = sessionFactory.openSession()) {
            return session.get(PersonContact.class, id);
        }
    }

    public void deleteContactById(long id) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            var contact = getContactById(id);
            if(contact != null) {
                session.delete(contact);
            }
            transaction.commit();
        }
    }

    public void changeContactPhoneNumber (long id, String newPhoneNumber) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            var contact = getContactById(id);
            if(contact != null) {
                contact.setPhoneNumber(newPhoneNumber);
                session.update(contact);
            }
            transaction.commit();
        }
    }

    public void changeContactEmail (long id, String newEmail) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            var contact = getContactById(id);
            if(contact != null) {
                contact.setEmail(newEmail);
                session.update(contact);
            }
            transaction.commit();
        }
    }

    public List<PersonContact> getAllContacts() {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            var query = session.createQuery("FROM PersonContact", PersonContact.class);
            return query.getResultList();
        }
    }
}
