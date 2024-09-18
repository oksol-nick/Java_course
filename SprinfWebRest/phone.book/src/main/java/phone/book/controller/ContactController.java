package phone.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import phone.book.ChangeContactDto;
import phone.book.ChangeContactResponse;
import phone.book.PersonContact;
import phone.book.PersonContactDto;
import phone.book.facade.PersonalContactFacade;

import java.util.Map;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final PersonalContactFacade personalContactFacade;

    @Autowired
    public ContactController(PersonalContactFacade personalContactFacade) {
        this.personalContactFacade = personalContactFacade;
    }

    @PostMapping
    public PersonContactDto createContact(
            @RequestParam String name,
            @RequestParam String phoneNumber,
            @RequestParam String email) {
        return personalContactFacade.createAccount(name, phoneNumber, email);
    }

    @GetMapping("/{contactId}")
    public PersonContactDto getAccount(
            @PathVariable long contactId
    ) {
        return personalContactFacade.getContact(contactId);
    }

    @PutMapping("/{contactId}")
    public ChangeContactResponse changeContact(
            @PathVariable("contactId") long contactId,
            @RequestBody ChangeContactDto changeContactDto
    ) {
        return personalContactFacade.changeContact(contactId, changeContactDto);
    }

    @GetMapping("")
    public Map<Long, PersonContact> getContacts() {
        return personalContactFacade.getAllContacts();
    }
}
