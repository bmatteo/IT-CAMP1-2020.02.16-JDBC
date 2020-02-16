package pl.camp.it;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DB.connect();

        Person person = new Person();
        person.setName("Mateusz");
        person.setSurname("Kowalski");
        person.setAge(30);

        DB.persistPerson2(person);

        Person p2 = DB.getPersonById(30);

        System.out.println(p2);

        List<Person> mojaLista = DB.getAllPersons();

        System.out.println(mojaLista);

        DB.closeConnection();
    }
}
