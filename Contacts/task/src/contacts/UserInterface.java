package contacts;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    public static PersonRecord createPersonRecord(Scanner scanner) {
        System.out.print("Enter the name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the surname: ");
        String surname = scanner.nextLine();
        System.out.print("Enter the birth date: ");
        String birth = scanner.nextLine();
        LocalDate birthDate = null;
        if (birth != null && !birth.isEmpty()) {
            try
            {
                birthDate = LocalDate.parse(birth);
            } catch (DateTimeParseException ignored) { }
        }
        if (birthDate == null) {
            System.out.println("Bad birth date!");
        }
        System.out.print("Enter the gender (M, F): ");
        String gender = scanner.nextLine();
        if (gender == null || gender.isEmpty() || (!"M".equals(gender) && !"F".equals(gender))){
            System.out.println("Bad gender!");
            gender = "[no data]";
        }
        System.out.print("Enter the number:  ");
        String phoneNumber = scanner.nextLine();
        LocalDateTime creationDate = LocalDateTime.now();
        LocalDateTime lastEdit = LocalDateTime.now();

        return new PersonRecord(Contacts.preparePhoneField(phoneNumber), creationDate, lastEdit, true, name, surname, gender, birthDate);

    }

    public static OrganizationRecord createOrganizationRecord(Scanner scanner) {
        System.out.print("Enter the organization name: ");
        String organizationName = scanner.nextLine();
        System.out.print("Enter the address: ");
        String address = scanner.nextLine();
        System.out.print("Enter the number: ");
        String phoneNumber = scanner.nextLine();
        LocalDateTime creationDate = LocalDateTime.now();
        LocalDateTime lastEdit = LocalDateTime.now();

        return new OrganizationRecord(Contacts.preparePhoneField(phoneNumber), creationDate, lastEdit, false, organizationName, address);
    }

    public static void action(Scanner scanner, Contacts contacts) {
        String action;
        String recordType;
        boolean flag = true;
        while (flag) {
            System.out.print("Enter action (add, remove, edit, count, info, exit): ");
            action = scanner.nextLine();
            switch (action) {
                case "add":
                    System.out.print("Enter the type (person, organization): ");
                    recordType = scanner.nextLine();
                    if ("person".equals(recordType)) {
                        contacts.add(createPersonRecord(scanner));
                    } else if ("organization".equals(recordType)) {
                        contacts.add(createOrganizationRecord(scanner));
                    } else {
                        System.out.println("wrong action");
                    }
                    System.out.println("The record added.");
                    break;
                case "remove":
                    if (contacts.getSize() == 0) {
                        System.out.println("No records to remove!");
                    } else {
                        list(contacts);
                        System.out.println("Select a record:");
                        int index = scanner.nextInt();
                        scanner.nextLine();
                        contacts.remove(index);
                    }
                    break;
                case "edit":
                    if (contacts.getSize() == 0) {
                        System.out.println("No records to edit!");
                    } else {
                        list(contacts);
                        contacts.edit(scanner, contacts);
                    }
                    break;
                case "count":
                    System.out.printf("The Phone Book has %d records.%n", contacts.getSize());
                    break;
                case "info":
                    list(contacts);
                    System.out.println("Enter index to show info:");
                    int index = scanner.nextInt();
                    scanner.nextLine();
                    showRecord(contacts, index);
                    break;
                case "exit":
                    System.out.println();
                    flag = false;
                    break;
                default:
                    System.out.println("Wrong input!");
                    break;
            }
            System.out.println();
        }
    }

    public static void list(Contacts contacts) {
        if (contacts.getSize() == 0) {
            System.out.println("No record to list!");
        } else {
            List<Record> book = contacts.getPhoneBook();
            for (int i = 0; i < contacts.getSize(); i++) {
                if (book.get(i).isPerson()) {
                    PersonRecord person = (PersonRecord)book.get(i);
                    System.out.printf("%d. %s %s%n", (i + 1), person.getName(), person.getSurname());
                } else {
                    OrganizationRecord organization = (OrganizationRecord) book.get(i);
                    System.out.printf("%d. %s%n", (i + 1), organization.getOrganizationName());
                }
            }
        }
    }

    public static void showRecord(Contacts contacts, int index) {
        System.out.print(contacts.getPhoneBook().get(index - 1).toString());
    }
}


