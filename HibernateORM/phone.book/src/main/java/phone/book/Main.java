package phone.book;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import phone.book.config.ApplicationConfiguration;

public class Main {
	public static void main(String[] args) {

		var applicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
		var contactDao = applicationContext.getBean(ContactDao.class);

		//contactDao.addContact(new PersonContact("Мария", "Швецова", "MarijaShvetsova@pochta.org", "71234567890"));
		//contactDao.addContact(new PersonContact("Денис", "Кораблёв", "DenisKorablev@pochta.org", "70987654321"));
		//contactDao.addContact(new PersonContact("Фёдор", "Курочкин", "FedorKurochkin@pochta.org", "70456123789"));

		//System.out.println(contactDao.getAllContacts());

		//System.out.println(contactDao.getContactById(37));

		//contactDao.changeContactPhoneNumber(38, "1111111111");

		//contactDao.changeContactEmail(37, "0000@000000.00");

		//contactDao.deleteContactById(38);
	}
}
