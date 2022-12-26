package contacts;

import java.time.LocalDateTime;

public class OrganizationRecord extends Record {
    private String organizationName;
    private String address;

    OrganizationRecord(
            String phoneNumber,
            LocalDateTime creationDate,
            LocalDateTime lastEdit,
            boolean isPerson,
            String organizationName,
            String address
    ) {
        super(phoneNumber, creationDate, lastEdit, isPerson);
        this.organizationName = organizationName;
        this.address = address;
    }

    public String getOrganizationName() {
        return this.organizationName;
    }

    public String getAddress() {
        return address;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("Organization name: %s %nAddress: %s%nNumber: %s%nTime created: %s%nTime last edit: %s%n",
                this.getOrganizationName(), this.getAddress(),
                this.getPhoneNumber(), this.getCreationDate().toString(),
                this.getLastEdit().toString());
    }
}


