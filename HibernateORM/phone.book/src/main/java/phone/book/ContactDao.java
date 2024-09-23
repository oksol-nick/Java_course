package phone.book;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ContactDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public ContactDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public long addContact(PersonContact contact) {
        Session session = null;
        long id;

        try {
            session = sessionFactory.openSession();
            var transaction = session.beginTransaction();
            id = (long) session.save(contact);
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
        return id;
    }

    public PersonContact getContactById(long id) {
        try (var session = sessionFactory.openSession()) {
            return session.get(PersonContact.class, id);
        }
    }

    public void deleteContactById(long id) {
        Session session = null;
        PersonContact contact = null;

        try {
            session = sessionFactory.openSession();
            var transaction = session.beginTransaction();
            contact = getContactById(id);
            if(contact != null) {
                session.delete(contact);
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

    public void changeContactPhoneNumber (long id, String newPhoneNumber) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            var transaction = session.beginTransaction();
            var contact = getContactById(id);
            if(contact != null) {
                contact.setPhoneNumber(newPhoneNumber);
                session.update(contact); }
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
                contact.setEmail(newEmail);
                session.update(contact);
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
