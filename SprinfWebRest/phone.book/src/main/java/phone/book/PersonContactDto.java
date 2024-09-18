package phone.book;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonContactDto {
    @JsonProperty("contactId")
    private final long contactId;
    @JsonProperty("name")
    private final String name;
    @JsonProperty("phoneNumber")
    private final String phoneNumber;
    @JsonProperty("email")
    private final String email;

    public PersonContactDto(PersonContact contact) {
        this.contactId = contact.getContactId();
        this.name = contact.getName();
        this.phoneNumber = contact.getPhoneNumber();
        this.email = contact.getEmail();
    }

    public long getContactId() {
        return contactId;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
}
