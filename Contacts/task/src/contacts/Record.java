package contacts;

import java.time.LocalDateTime;

public class Record {
    private String phoneNumber;
    private final LocalDateTime creationDate;
    private LocalDateTime lastEdit;
    private final boolean isPerson;

    Record(
            String phoneNumber,
            LocalDateTime creationDate,
            LocalDateTime lastEdit,
            boolean isPerson
    ) {
        this.phoneNumber = phoneNumber;
        this.creationDate = creationDate;
        this.lastEdit = lastEdit;
        this.isPerson = isPerson;
    }



    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getLastEdit() {
        return lastEdit;
    }

    public boolean isPerson() {
        return isPerson;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public void setLastEdit(LocalDateTime lastEdit) {
        this.lastEdit = lastEdit;
    }
}
