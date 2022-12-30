package kolokviumski.ispitni.zad2;

import java.util.*;


class Contact{
    private String name, number;

    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return name + " " + number;
    }
}

class DuplicateNumberException extends Exception{
    public DuplicateNumberException(String mess) {
        super(mess);
    }
}

class PhoneBook{

    Set<String> uniquePhoneNumbers;
    Map<String, Set<Contact>> contacts;
    Map<String,Set<Contact>> contactsByNumber;

    public PhoneBook(){
        uniquePhoneNumbers = new HashSet<>();
        contacts = new HashMap<>();
        contactsByNumber = new HashMap<>();
    }

    void addContact(String name, String number) throws DuplicateNumberException {

        Contact newContact = new Contact(name,number);

        if(uniquePhoneNumbers.contains(number)){
            throw new DuplicateNumberException("Duplicate number: " + number);
        }else {
            uniquePhoneNumbers.add(number);
        }

        contacts.putIfAbsent(name, new TreeSet<Contact>(Comparator.comparing(Contact::getName).thenComparing(Contact::getNumber)));
        contacts.get(name).add(newContact);


    }

    public void contactsByName(String name){
        if(!contacts.containsKey(name)){
            System.out.printf("NOT FOUND");
            return;
        }
        contacts.get(name).forEach(System.out::println);
    }

    public void contactsByNumber(String number){
        return;
    }


}






public class PhoneBookTest {

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split(":");
            try {
                phoneBook.addContact(parts[0], parts[1]);
            } catch (DuplicateNumberException e) {
                System.out.println(e.getMessage());
            }
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);
            String[] parts = line.split(":");
            if (parts[0].equals("NUM")) {
                phoneBook.contactsByNumber(parts[1]);
            } else {
                phoneBook.contactsByName(parts[1]);
            }
        }
    }

}

// Вашиот код овде


