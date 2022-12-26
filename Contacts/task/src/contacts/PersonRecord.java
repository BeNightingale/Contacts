package contacts;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PersonRecord extends Record{
    private String name;
    private String surname;
    private String gender;
    private LocalDate birthDate;


    PersonRecord(
            String phoneNumber,
            LocalDateTime creationDate,
            LocalDateTime lastEdit,
            boolean isPerson,
            String name,
            String surname,
            String gender,
            LocalDate birthDate
    ) {
            super(phoneNumber, creationDate, lastEdit, isPerson);
            this.name = name;
            this.surname = surname;
            this.gender = gender;
            this.birthDate = birthDate;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
            return this.surname;
    }

    public String getGender() {
        return gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getBirthDateString() {
        if (this.birthDate == null) {
            return "[no data]";
        } else {
            return birthDate.toString();
        }
    }

    public void setName(String name) {
            this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return String.format("Name: %s %nSurname: %s%nBirth date: %s%nGender: %s%nNumber: %s%nTime created: %s%nTime last edit: %s%n",
                this.getName(), this.getSurname(), this.getBirthDateString(),
                this.getGender(), this.getPhoneNumber(), this.getCreationDate().toString(),
                this.getLastEdit().toString());
    }
}
