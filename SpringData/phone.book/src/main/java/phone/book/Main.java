package phone.book;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.List;

@SpringBootApplication
public class Main {
	public static void main(String[] args) {SpringApplication.run(Main.class, args);}

	@Bean
	public CommandLineRunner demo(ContactRepository contactRepository) {
		return args -> {

			//contactRepository.deleteAllInBatch();

			//contactRepository.save(new PersonContact("Мария", "Швецова", "MarijaShvetsova@pochta.org", "71234567890"));

			//contactRepository.saveAll(contacts);

			//System.out.println(contactRepository.findAll());

			//System.out.println(contactRepository.findById(27));

			//contactRepository.updatePhoneNumber(26, "0000000000");

			//contactRepository.updateEmail(26, "000@0000000.000");

			//contactRepository.deleteById(27);

		};
	}

	private static final List<PersonContact> contacts = List.of(
			new PersonContact("Денис", "Кораблёв", "DenisKorablev@pochta.org", "70987654321"),
			new PersonContact("Фёдор", "Курочкин", "FedorKurochkin@pochta.org", "70456123789"));
}
