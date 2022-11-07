//Се со цел да се подобри комуникацијата на факултетот потребно е да се направи систем за чување на контакти за секој студент.
//Да се креира класа Contact. За потребите на оваа класа да се дефинираат следниве методи:
//Contact(String date) - конструктор каде што date е датумот кога е креиран контактот даден во следниов формат YYYY-MM-DD
//isNewerThan(Contact c):boolean - метод кој враќа true доколку контактот е креиран подоцна од контактот c и обратно
//getType():String - метод кој враќа вредност "Email" или "Phone" во зависност од типот на контактот
//Од класата Contact не треба да може директно да се инстанцира објект.
//Од оваа класа се изведуваат класите EmailContact и PhoneContact.
//За класата EmailContact дополнително се чува e-маил кој што е од типот String. Да се дефинираат следниве методи:
//EmailContact(String date, String email) - конструктор
//getEmail():String - метод кој што го враќа е-маилот
//getType():String- имплементација на методот од класата Contact
//За класата PhoneContact дополнително се чува телефонски број кој што е од типот String и оператор кој што е енумерација и се дефинира на следниов начин enum Operator { VIP, ONE, TMOBILE }. За оваа класа да се дефинираат следниве методи:
//PhoneContact(String date, String phone) - конструктор
//getPhone():String - метод кој што го враќа телефонскиот број
//getOperator():Operator - метод кој што го враќа операторот (070, 071, 072 – TMOBILE, 075,076 – ONE, 077, 078 – VIP)
//getType():String- имплементација на методот од класата Contact
//*Забелешка: Сите телефонски броеви се во формат 07X/YYY-ZZZ каде што X има вредност {0,1,2,5,6,7,8}
//Потоа да се дефинира класата Student каде што се чува низа на контакти за секој студент
//Student(String firstName, String lastName, String city, int age, long index) – конструктор
//addEmailContact(String date, String email):void – метод што додава е-маил контакт во низата на контакти
//addPhoneContact(String date, String phone):void – метод што додава телефонски контакт во низата на контакти
//getEmailContacts():Contact[] – враќа низа на email контактите на студентот
//getPhoneContacts():Contact[] – враќа низа на phone контактите на студентот
//getCity():String - метод кој го враќа градот
//getFullName():String - метод кој го враќа целосното име на студентот во формат IME PREZIME
//getIndex():long - метод кој го враќа индексот на студентот
//getLatestContact():Contact – го враќа најновиот контакт (според датум) од студентот
//toString() – претставува JSON репрезентација на класата студент пр. {"ime":"Jovan", "prezime":"Jovanov", "vozrast":20, "grad":"Skopje", "indeks":101010, "telefonskiKontakti":["077/777-777", "078/888-888"], "emailKontakti":["jovan.jovanov@example.com", "jovanov@jovan.com", "jovan@jovanov.com"]}
//*Забелешка: Во класата Student да се чува само една низа од контакти Contact[], а не две низи одделно (PhoneContact[] и EmailContact[])
//*Напомена да не се користи instanceOf или getClass при имплементација на овие методи
//Дополнително да се дефинира класа Faculty. За оваа класа да се дефинираат следниве методи:
//Faculty(String name, Student [] students) – конструктор
//countStudentsFromCity(String cityName):int – враќа колку студенти има од даден град
//getStudent(long index):Student – метод кој го враќа студентот кој го има дадениот индекс
//getAverageNumberOfContacts():double – враќа просечен број на контакти по студент
//getStudentWithMostContacts():Student – метод кој го враќа студентот со најмногу контакти (доколку има повеќе студенти со ист број на контакти да го врати студентот со најголем индекс)
//toString() – претставува JSON репрезентација на класата Faculty пример: {"fakultet":"FINKI", "studenti":[STUDENT1, STUDENT2, ...]} каде што треба да има целосни информации за секој студент.

package laboratoriski.lab2.zad2;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

abstract class Contact{
    private String contactCreatedDate;
    private String typeOfContact;

    public Contact(String contactCreatedDate) {
        this.contactCreatedDate = contactCreatedDate;
    }

    public boolean isNewerThan(Contact c) throws ParseException {
        DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dateThis = date.parse(this.contactCreatedDate);
        DateFormat date2 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dateThat = date2.parse(c.contactCreatedDate);
        return dateThis.after(dateThat);
    }

    abstract public String getTypeOfContact();

    public void setTypeOfContact(String typeOfContact) {
        this.typeOfContact = typeOfContact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(contactCreatedDate, contact.contactCreatedDate) && Objects.equals(typeOfContact, contact.typeOfContact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactCreatedDate, typeOfContact);
    }

    abstract public String toString();
}

class EmailContact extends Contact{
    private String email;

    public EmailContact(String contactCreatedDate, String email) {
        super(contactCreatedDate);
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public String getTypeOfContact(){
        return "Email";
    }

    public String toString(){
        return "\""+email+"\"";
    }
}

class PhoneContact extends Contact{
    private String phone;
    public enum Operator{
        VIP,
        ONE,
        TMOBILE
    };
    private Operator operator;
    public PhoneContact(String contactCreatedDate, String phone) {
        super(contactCreatedDate);
        this.phone = phone;
        String operatorCode=phone.substring(2,3);
        if(operatorCode.equals("0") || operatorCode.equals("1") || operatorCode.equals("2") ) {
            this.operator=Operator.TMOBILE;
        }else if(operatorCode.equals("5") || operatorCode.equals("6")){
            this.operator=Operator.ONE;
        }else if(operatorCode.equals("7") || operatorCode.equals("8")) {
            this.operator=Operator.VIP;
        }
    }

    public String getPhone(){
        return phone;
    }

    public Operator getOperator() {
        return operator;
    }

    public String getTypeOfContact(){
        return "Phone";
    }

    public String toString(){
        return "\""+phone+"\"";
    }




}


class Student{
    private String firstName, lastName, city;
    private int age;
    private long index;
    private int numberOfContacts=0;

    private Contact [] contacts = new Contact[0];
    public Student(String firstName, String lastName, String city, int age, long index) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.age = age;
        this.index = index;
    }

    public void addEmailContact(String date, String email){
        Contact [] tmp = Arrays.copyOf(contacts, numberOfContacts+1);
        tmp[numberOfContacts] = new EmailContact(date, email);
        contacts = tmp;
        numberOfContacts++;

    }

    public void addPhoneContact(String date, String phone){
        Contact [] tmp = Arrays.copyOf(contacts,numberOfContacts+1);
        tmp[numberOfContacts] = new PhoneContact(date, phone);
        contacts = tmp;
        numberOfContacts++;
    }

    public int countEmailContacts(){
        int counter=0;
        for(int i=0;i< numberOfContacts;i++){
            if(contacts[i].getTypeOfContact().equals("Email")){
                counter++;
            }
        }
        return counter;
    }

    public Contact[] getEmailContacts(){
        Contact [] emailContacts = new EmailContact[countEmailContacts()];
        int j=0;
        for(int i=0;i<numberOfContacts;i++){
            if(contacts[i].getTypeOfContact().equals("Email")){
                emailContacts[j] = contacts[i];
                j++;
            }
        }
        return emailContacts;
    }

    public int getNumberOfContacts(){
        return numberOfContacts;
    }
    public Contact[] getPhoneContacts(){
        Contact[] phoneContacts = new PhoneContact[(numberOfContacts - countEmailContacts())];
        int j=0;
        for(int i=0;i<numberOfContacts;i++){
            if(contacts[i].getTypeOfContact().equals("Phone")){
                phoneContacts[j] = contacts[i];
                j++;
            }
        }
        return phoneContacts;
    }

    public String getCity(){
        return city;
    }

    public String getFullName(){
        return firstName.toUpperCase() + " " + lastName.toUpperCase();
    }

    public long getIndex(){
        return index;
    }

    public Contact getLatestContact() throws ParseException {
        Contact newestContact = contacts[0];
        for(int i=1;i<numberOfContacts;i++){
            if(contacts[i].isNewerThan(newestContact)){
                newestContact=contacts[i];
            }
        }
        return newestContact;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"ime\":\"").append(firstName).append("\", \"prezime\":\"").append(lastName);
        sb.append("\", \"vozrast\":").append(age).append(", \"grad\":\"").append(city);
        sb.append("\", \"indeks\":").append(index).append(", ");
        sb.append("\"telefonskiKontakti\":").append(Arrays.toString(getPhoneContacts()));
        sb.append(", \"emailKontakti\":").append(Arrays.toString(getEmailContacts())).append("}");

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age && index == student.index && numberOfContacts == student.numberOfContacts && Objects.equals(firstName, student.firstName) && Objects.equals(lastName, student.lastName) && Objects.equals(city, student.city) && Arrays.equals(contacts, student.contacts);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(firstName, lastName, city, age, index, numberOfContacts);
        result = 31 * result + Arrays.hashCode(contacts);
        return result;
    }
}

class Faculty{
    private String name;
    private Student[] students;

    public Faculty(String name, Student[] students) {
        this.name = name;
        this.students = students;
    }

    public int countStudentsFromCity(String cityName) {
        int counter = 0;
        for(int i = 0; i<students.length; i++){
            if(students[i].getCity().equals(cityName)){
                counter++;
            }
        }
        return counter;
    }

    public Student getStudent(long index){
        for(int i=0;i<students.length;i++){
            if(students[i].getIndex()==index){
                return students[i];
            }
        }
        return null;
    }
    public double getAverageNumberOfContacts(){
        double totalContacts=0.0;
        for(int i=0;i<students.length;i++){
            totalContacts += students[i].getNumberOfContacts();
        }
        return totalContacts/students.length;
    }

    public Student getStudentWithMostContacts(){
        Student studentWithMostContacts = students[0];
        for(int i=0;i<students.length;i++){
            if(students[i].getNumberOfContacts() > studentWithMostContacts.getNumberOfContacts()){
                studentWithMostContacts = students[i];
            }else if(students[i].getNumberOfContacts() == studentWithMostContacts.getNumberOfContacts()){
                if(students[i].getIndex()>studentWithMostContacts.getIndex()){
                    studentWithMostContacts = students[i];
                }
            }
        }
        return  studentWithMostContacts;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"fakultet\":\"").append(name).append("\", \"studenti\":");
        sb.append(Arrays.toString(students)).append("}");
        return sb.toString();
    }


}



















































public class ContactsTester {

    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);

        int tests = scanner.nextInt();
        Faculty faculty = null;

        int rvalue = 0;
        long rindex = -1;

        DecimalFormat df = new DecimalFormat("0.00");

        for (int t = 0; t < tests; t++) {

            rvalue++;
            String operation = scanner.next();

            switch (operation) {
                case "CREATE_FACULTY": {
                    String name = scanner.nextLine().trim();
                    int N = scanner.nextInt();

                    Student[] students = new Student[N];

                    for (int i = 0; i < N; i++) {
                        rvalue++;

                        String firstName = scanner.next();
                        String lastName = scanner.next();
                        String city = scanner.next();
                        int age = scanner.nextInt();
                        long index = scanner.nextLong();

                        if ((rindex == -1) || (rvalue % 13 == 0))
                            rindex = index;

                        Student student = new Student(firstName, lastName, city,
                                age, index);
                        students[i] = student;
                    }

                    faculty = new Faculty(name, students);
                    break;
                }

                case "ADD_EMAIL_CONTACT": {
                    long index = scanner.nextInt();
                    String date = scanner.next();
                    String email = scanner.next();

                    rvalue++;

                    if ((rindex == -1) || (rvalue % 3 == 0))
                        rindex = index;

                    faculty.getStudent(index).addEmailContact(date, email);
                    break;
                }

                case "ADD_PHONE_CONTACT": {
                    long index = scanner.nextInt();
                    String date = scanner.next();
                    String phone = scanner.next();

                    rvalue++;

                    if ((rindex == -1) || (rvalue % 3 == 0))
                        rindex = index;

                    faculty.getStudent(index).addPhoneContact(date, phone);
                    break;
                }

                case "CHECK_SIMPLE": {
                    System.out.println("Average number of contacts: "
                            + df.format(faculty.getAverageNumberOfContacts()));

                    rvalue++;

                    String city = faculty.getStudent(rindex).getCity();
                    System.out.println("Number of students from " + city + ": "
                            + faculty.countStudentsFromCity(city));

                    break;
                }

                case "CHECK_DATES": {

                    rvalue++;

                    System.out.print("Latest contact: ");
                    Contact latestContact = faculty.getStudent(rindex)
                            .getLatestContact();
                    if (latestContact.getTypeOfContact().equals("Email"))
                        System.out.println(((EmailContact) latestContact)
                                .getEmail());
                    if (latestContact.getTypeOfContact().equals("Phone"))
                        System.out.println(((PhoneContact) latestContact)
                                .getPhone()
                                + " ("
                                + ((PhoneContact) latestContact).getOperator()
                                .toString() + ")");

                    if (faculty.getStudent(rindex).getEmailContacts().length > 0
                            && faculty.getStudent(rindex).getPhoneContacts().length > 0) {
                        System.out.print("Number of email and phone contacts: ");
                        System.out
                                .println(faculty.getStudent(rindex)
                                        .getEmailContacts().length
                                        + " "
                                        + faculty.getStudent(rindex)
                                        .getPhoneContacts().length);

                        System.out.print("Comparing dates: ");
                        int posEmail = rvalue
                                % faculty.getStudent(rindex).getEmailContacts().length;
                        int posPhone = rvalue
                                % faculty.getStudent(rindex).getPhoneContacts().length;

                        System.out.println(faculty.getStudent(rindex)
                                .getEmailContacts()[posEmail].isNewerThan(faculty
                                .getStudent(rindex).getPhoneContacts()[posPhone]));
                    }

                    break;
                }

                case "PRINT_FACULTY_METHODS": {
                    System.out.println("Faculty: " + faculty.toString());
                    System.out.println("Student with most contacts: "
                            + faculty.getStudentWithMostContacts().toString());
                    break;
                }

            }

        }

        scanner.close();
    }
}


