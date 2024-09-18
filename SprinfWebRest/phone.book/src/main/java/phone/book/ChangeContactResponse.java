package phone.book;

public class ChangeContactResponse {

    private final ChangeContactResult changeContactResult;
    public ChangeContactResponse(ChangeContactResult changeContactResult) {
        this.changeContactResult = changeContactResult;
    }
    public ChangeContactResult getChangeContactResul() {
        return changeContactResult;
    }
}
