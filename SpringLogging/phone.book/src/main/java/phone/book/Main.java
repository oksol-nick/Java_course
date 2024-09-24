package phone.book;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {

	public static void main(String[] args) throws InterruptedException {

		SpringApplication.run(Main.class, args);

	}

	@Bean
	public CommandLineRunner demo(ContactDao contactDao) {

		return args ->
		{
			contactDao.addContact(new PersonContact("Мария", "Швецова", "MarijaShvetsova@pochta.org", "71234567890"));
			//contactDao.addContact(new PersonContact("Денис", "Кораблёв", "DenisKorablev@pochta.org", "70987654321"));
			//contactDao.addContact(new PersonContact("Фёдор", "Курочкин", "FedorKurochkin@pochta.org", "70456123789"));

			//System.out.println(contactDao.getAllContacts()+"\n");

			//System.out.println(contactDao.getContactById(37));

			//contactDao.changeContactPhoneNumber(38, "1111111111");

			//contactDao.changeContactEmail(37, "0000@000000.00");

			//contactDao.deleteContactById(38);
		};
	}
}
