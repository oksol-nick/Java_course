package phone.book;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class ContactDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public ContactDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public long addContact(PersonContact contact) {
        log.info("Start creating new contact with data={}", contact);
        Session session = null;
        long id;

        try {
            session = sessionFactory.openSession();
            var transaction = session.beginTransaction();
            log.info("Создается контакт={}", contact);
            id = (long) session.save(contact);
            transaction.commit();
            log.info("Создан контакт={}", contact);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        return id;
    }

    public PersonContact getContactById(long id) {
        try (var session = sessionFactory.openSession()) {
            PersonContact contact =  session.get(PersonContact.class, id);
            log.info("Найден контакт={}", contact);
            return contact;
        }
    }

    public void deleteContactById(long id) {
        Session session = null;
        PersonContact contact = null;

        try {
            session = sessionFactory.openSession();
            var transaction = session.beginTransaction();
            contact = getContactById(id);
            log.info("Найден={}", contact);
            if(contact != null) {
                session.delete(contact);
            }
            log.info("Контакт={} удален", contact);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void changeContactPhoneNumber (long id, String newPhoneNumber) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            var transaction = session.beginTransaction();
            var contact = getContactById(id);
            if(contact != null) {
                log.info("Найден={}", contact);
                contact.setPhoneNumber(newPhoneNumber);
                session.update(contact);
                log.info("Номер телефона контакта {} изменен", contact);
            }
            transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
             }
            finally {
                if (session != null) {
                    session.close();
                }
            }
    }

    public void changeContactEmail (long id, String newEmail) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            var transaction = session.beginTransaction();
            var contact = getContactById(id);
            if(contact != null) {
                log.info("Найден={}", contact);
                contact.setEmail(newEmail);
                session.update(contact);
                log.info("Email контакта {} изменен", contact);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List<PersonContact> getAllContacts() {
        Session session = null;
        List<PersonContact> contacts = new ArrayList<>();
        try {
            session = sessionFactory.openSession();
            var transaction = session.beginTransaction();
            var query = session.createQuery("FROM PersonContact", PersonContact.class);
            transaction.commit();
            contacts = (List<PersonContact>) query.list();
            log.info("Найдено контактов:{}", contacts.size());


        } catch (Exception e) {
            e.printStackTrace();
            throw e;
         }
        finally {
            if (session != null) {
                session.close();
            }
        }
        return contacts;
    }
}
