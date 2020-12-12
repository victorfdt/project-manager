package nl.victorfdt.projectmanagerbackend.handler;

public class UniqueKeyViolationResponse {

    private String message;

    public UniqueKeyViolationResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
