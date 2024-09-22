package phone.book;

import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Primary
public class ContactDao {

    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    public ContactDao(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    public PersonContact addContact(String name, String surname, String email, String phoneNumber) {

        long id;
        long maxId = namedJdbcTemplate.query("SELECT MAX(ID) FROM CONTACT", (rs, i) -> rs.getLong("MAX")).getFirst();

        if (maxId <= 0) {
            id = 1;
        } else {
            id = maxId + 1;
        }

        namedJdbcTemplate.update(
                "INSERT INTO CONTACT (ID, NAME, SURNAME, EMAIL, PHONE_NUMBER) VALUES(:id, :name, :surname, :email, :phoneNumber)",
                new MapSqlParameterSource()
                        .addValue("id", id)
                        .addValue("name", name)
                        .addValue("surname", surname)
                        .addValue("email", email)
                        .addValue("phoneNumber", phoneNumber)
        );
        return new PersonContact(id, name, surname, phoneNumber, email);
    }

    public PersonContact addContact(long id, String name, String surname, String email, String phoneNumber) {

        namedJdbcTemplate.update("INSERT INTO CONTACT (ID, NAME, SURNAME, EMAIL, PHONE_NUMBER) " +
                        "VALUES(:id, :name, :surname, :email, :phoneNumber)",
                new MapSqlParameterSource()
                        .addValue("id", id)
                        .addValue("name", name)
                        .addValue("surname", surname)
                        .addValue("email", email)
                        .addValue("phoneNumber", phoneNumber)
        );
        return new PersonContact(id, name, surname, phoneNumber, email);
    }

    public PersonContact getContactById(long id) throws EmptyResultDataAccessException {

        PersonContact foundContact = null;

        try {
            foundContact = namedJdbcTemplate.queryForObject(
                    "select * from contact where id= :id",
                    new MapSqlParameterSource("id", id),
                    (rs, i) -> new PersonContact(
                            rs.getLong("ID"),
                            rs.getString("NAME"),
                            rs.getString("SURNAME"),
                            rs.getString("EMAIL"),
                            rs.getString("PHONE_NUMBER")));
            } catch (EmptyResultDataAccessException e) {
                System.out.printf("Contact with id=%s not found.", id);
            }

        return foundContact;
    }

    public List<PersonContact> getAllContact() {
        return namedJdbcTemplate.query("SELECT * FROM CONTACT",
                (rs, i) -> new PersonContact(
                        rs.getLong("ID"),
                        rs.getString("NAME"),
                        rs.getString("SURNAME"),
                        rs.getString("EMAIL"),
                        rs.getString("PHONE_NUMBER")));
    }

    public String deleteContactById(long id) {
        if(getContactById(id) != null) {
            namedJdbcTemplate.update("DELETE FROM CONTACT WHERE id= :id",
                    new MapSqlParameterSource("id", id));
            return String.format("Contact id=%s deleted.", id);
        } else {
            return String.format("Contact id=%s not found.", id);
        }
    }

    public void changePhoneNumber(long id, String newPhoneNumber) {
        namedJdbcTemplate.update("UPDATE CONTACT SET PHONE_NUMBER = :newPhoneNumber WHERE ID = :id",
                new MapSqlParameterSource()
                        .addValue("id", id)
                        .addValue("newPhoneNumber", newPhoneNumber)
        );
    }

    public void changeEmail(long id, String newEmail) {
        namedJdbcTemplate.update("UPDATE CONTACT SET EMAIL = :newEmail WHERE ID = :id",
                new MapSqlParameterSource()
                        .addValue("id", id)
                        .addValue("newEmail", newEmail));
    }

    public List<PersonContact> addAllContacts (List<PersonContact> contacts) {

        long id;
        long maxId = namedJdbcTemplate.query("SELECT MAX(ID) FROM CONTACT",
                (rs, i) -> rs.getLong("MAX")).getFirst();

        if (maxId <= 0) {
            id = 0;
        } else {
            id = maxId;
        }

        var args = contacts.stream().map(contact -> new MapSqlParameterSource()
                        .addValue("id", id + contacts.indexOf(contact) + 1)
                        .addValue("name", contact.getName())
                        .addValue("surname", contact.getSurname())
                        .addValue("email", contact.getEmail())
                        .addValue("phoneNumber", contact.getPhoneNumber()))
                .toArray(MapSqlParameterSource[]::new);

        namedJdbcTemplate.batchUpdate(
                "INSERT INTO CONTACT (ID, NAME, SURNAME, EMAIL, " +
                        "PHONE_NUMBER) VALUES (:id, :name, :surname, :email, :phoneNumber)", args);

        return contacts;

    }
}
