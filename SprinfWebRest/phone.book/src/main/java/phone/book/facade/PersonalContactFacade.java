package phone.book.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phone.book.*;

import java.util.Map;

@Service
public class PersonalContactFacade {
    private final InMemoryPersonContactsDao inMemoryPersonContactsDao;

    @Autowired
    public PersonalContactFacade(InMemoryPersonContactsDao inMemoryPersonContactsDao) {
        this.inMemoryPersonContactsDao = inMemoryPersonContactsDao;

    }

    public PersonContactDto createAccount(String name, String phoneNumber, String email) {
        PersonContact contact = inMemoryPersonContactsDao.addContact(name, phoneNumber, email);
        return new PersonContactDto(contact);
    }

    public PersonContactDto getContact(long contactId) {
        PersonContact contact = inMemoryPersonContactsDao.getContact(contactId);
        return new PersonContactDto(contact);
    }

    public ChangeContactResponse changeContact(long contactId, ChangeContactDto changeContactDto) {
        inMemoryPersonContactsDao.changeContact(contactId, changeContactDto.getNewName(),
                changeContactDto.getNewPhoneNumber(), changeContactDto.getNewEmail());
        return new ChangeContactResponse (ChangeContactResult.SUCCESS);
    }

    public Map<Long, PersonContact> getAllContacts() {
        return inMemoryPersonContactsDao.getAllContacts();
    }
}
