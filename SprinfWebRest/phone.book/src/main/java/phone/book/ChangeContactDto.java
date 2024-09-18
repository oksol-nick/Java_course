package phone.book;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChangeContactDto {
    @JsonProperty("newName")
    private String newName;
    @JsonProperty("newPhoneNumber")
    private String newPhoneNumber;
    @JsonProperty("newEmail")
    private String newEmail;

    public ChangeContactDto() {
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getNewPhoneNumber() {
        return newPhoneNumber;
    }

    public void setNewPhoneNumber(String newPhoneNumber) {
        this.newPhoneNumber = newPhoneNumber;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }
}
