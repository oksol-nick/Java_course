package phone.book;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class ContactService {

    private final ContactDao contactDao;

    public ContactService(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    public static List<PersonContact> getAndParseContacts(File file) {
        List<PersonContact> contacts = null;
        try {
            if (file.exists()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                Scanner scanner = new Scanner(bufferedReader).useDelimiter("\\A");
                String result = scanner.hasNext() ? scanner.next() : "";
                contacts = new ArrayList<>();
                String[] lines = result.split("\r\n");
                for (int i = 0; i < lines.length; i++) {
                    contacts.add(convertLineToContact(lines[i]));
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return contacts;
    }

    private static PersonContact convertLineToContact(String line) {
        String[] tokens = line.split(",");
        return new PersonContact(tokens[0], tokens[1], tokens[2], tokens[3]);
    }

    public void loadAllContacts (File file) {

        var contacts = getAndParseContacts(file);

        contactDao.addAllContacts(contacts);
    }


}
