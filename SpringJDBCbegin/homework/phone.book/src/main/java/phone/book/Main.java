package phone.book;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import phone.book.config.ApplicationConfiguration;

public class Main {
	public static void main(String[] args) {

		var applicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
		var contactDao = applicationContext.getBean(ContactDao.class);

		contactDao.addContact("Мария", "Швецова", "MarijaShvetsova@pochta.org", "71234567890");
		contactDao.addContact("Денис", "Кораблёв", "DenisKorablev@pochta.org", "70987654321");
		contactDao.addContact("Фёдор", "Курочкин", "FedorKurochkin@pochta.org", "70456123789");

		//contactDao.deleteContactById(2);

		//contactDao.changePhoneNumber(1, "1111111111");

		//contactDao.changeEmail(3, "0000@000000.00");

		//System.out.println(contactDao.getAllContact());

		//System.out.println(contactDao.getContactById(1));
	}
}
