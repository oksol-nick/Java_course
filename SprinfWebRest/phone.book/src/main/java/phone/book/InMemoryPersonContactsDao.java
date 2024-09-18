package phone.book;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class InMemoryPersonContactsDao {

    private long contactId = 1L;
    private final Map<Long, PersonContact> contactsIdMap;

    public InMemoryPersonContactsDao() {
        this.contactsIdMap = new HashMap<>();
    }

    public PersonContact addContact(String name, String phoneNumber, String email) {
        PersonContact contact = new PersonContact(contactId, name, phoneNumber, email );
        contactsIdMap.put(contactId++, contact);
        return contact;
    }

    public PersonContact getContact(long contactId) {
        return Optional.ofNullable(contactsIdMap.get(contactId)).orElseThrow(()
                -> new IllegalArgumentException("Contact not found: " + contactId));
    }

    public Map<Long, PersonContact> getAllContacts() {
        return contactsIdMap;
    }

    public void changeContact(long contactId, String newName, String newPhoneNumber, String newEmail) {
        contactsIdMap.put(contactId, new PersonContact(contactId, newName, newPhoneNumber, newEmail));
    }
}
