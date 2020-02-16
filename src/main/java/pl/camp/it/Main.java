package pl.camp.it;

public class Main {
    public static void main(String[] args) {
        DB.connect();

        Person person = new Person();
        person.setName("Mateusz");
        person.setSurname("Kowalski");
        person.setAge(30);

        DB.persistPerson2(person);
    }
}
