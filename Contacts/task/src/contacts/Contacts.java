package contacts;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contacts {
    private final ArrayList<Record> phoneBook;

    Contacts() {
        this.phoneBook = new ArrayList<>();
    }

    public List<Record> getPhoneBook() {
        return this.phoneBook;
    }



    public void add(Record record) {
        phoneBook.add(record);
    }

    public void remove(int index) {
        if (index <= phoneBook.size()) {
            this.phoneBook.remove(index - 1);
            System.out.println("The record removed!");
        } else {
            System.out.println("No such record!");
        }
    }

    public void edit(Scanner scanner, Contacts contacts) {
        System.out.print("Select a record: ");
        int recordNumber = scanner.nextInt();
        scanner.nextLine();
        boolean flag = contacts.getPhoneBook().get(recordNumber - 1).isPerson();
        this.phoneBook.get(recordNumber - 1).setLastEdit(LocalDateTime.now());
        int pointer = 1;
        if (!flag) {
            pointer = -1;
        }
        switch (pointer) {
            case 1:
                System.out.print("Select a field (name, surname, birth, gender, number): ");
                String field = scanner.nextLine();
                switch (field) {
                    case "name":
                        System.out.print("Enter the name: ");
                        String name = scanner.nextLine();
                        ((PersonRecord) this.phoneBook.get(recordNumber - 1)).setName(name);
                        break;
                    case "surname":
                        System.out.print("Enter the surname: ");
                        String surname = scanner.nextLine();
                        ((PersonRecord)this.phoneBook.get(recordNumber - 1)).setSurname(surname);
                        break;
                    case "birth":
                        System.out.print("Enter the birth date: ");
                        LocalDate birthDate = null;
                        String birth = scanner.nextLine();
                        if (birth != null) {
                            try
                            {
                                birthDate = LocalDate.parse(birth);
                            } catch (DateTimeParseException ignored) { }
                        }
                        if (birthDate == null) {
                            System.out.println("Bad birth date!");
                        }
                        ((PersonRecord)this.phoneBook.get(recordNumber - 1)).setBirthDate(birthDate);
                        break;
                    case "gender":
                        System.out.print("Enter the gender (M, F): ");
                        String gender = scanner.nextLine();
                        if (!"M".equals(gender) && !"F".equals(gender)){
                            System.out.println("Bad gender!");
                            gender = "[no data]";
                        }
                        ((PersonRecord)this.phoneBook.get(recordNumber - 1)).setGender(gender);
                        break;
                    case "number":
                        System.out.print("Enter the number: ");
                        String number = scanner.nextLine();
                        if (checkNumber(number)) {
                            this.phoneBook.get(recordNumber - 1).setPhoneNumber(number);
                        } else {
                            System.out.println("Wrong number format!");
                            this.phoneBook.get(recordNumber - 1).setPhoneNumber("[no number]");
                        }
                        break;
                    default:
                        System.out.println("Wrong input!");
                        break;
                }
                break;

            case -1:
                System.out.println("Select a field (name, address, number):");
                String organizationField = scanner.nextLine();
                switch (organizationField) {
                    case "name":
                        System.out.println("Enter the organization name:");
                        String organizationName = scanner.nextLine();
                        ((OrganizationRecord)this.phoneBook.get(recordNumber - 1)).setOrganizationName(organizationName);
                        break;
                    case "address":
                        System.out.println("Enter the address:");
                        String address = scanner.nextLine();
                        ((OrganizationRecord)this.phoneBook.get(recordNumber - 1)).setAddress(address);
                        break;
                    case "number":
                        System.out.println("Enter the number:");
                        String phoneNumber = scanner.nextLine();
                        this.phoneBook.get(recordNumber - 1).setPhoneNumber(Contacts.preparePhoneField(phoneNumber));
                        break;
                    default:
                        System.out.println("Wrong input!");
                        break;
                }
                break;
            default:
                break;
        }

        System.out.println("The record updated!");
    }

    public int getSize() {
        return this.phoneBook.size();
    }

    public static boolean checkNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return false;
        }
        String regex = "(\\+?\\w+[ -]\\(\\w+\\)([ -]\\w+)*)|(\\+?(\\(\\w+\\))*([ -]\\w+)*)|(\\+?\\w+([ -]\\w{2,})*)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public static String preparePhoneField(String phoneNumber) {
        if (checkNumber(phoneNumber)) {
            return phoneNumber;
        } else {
            System.out.println("Wrong number format!");
            return "[no number]";
        }
    }
}

