package pl.jwrabel.trainings.javandwro3.java8;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jakubwrabel on 18.03.2017.
 */
public class LambdaPersons {
	public static void main(String[] args) {
		List<Person> personList = new ArrayList<>();
		personList.add(new Person("Adam", "Nowak", "Wrocław"));
		personList.add(new Person("Adam", "Kowalski", "Warszawa"));
		personList.add(new Person("Jerzy", "Polański", "Warszawa"));
		personList.add(new Person("Piotr", "Mickiewicz", "Sosnowiec"));
		personList.add(new Person("Jan", "Kowalski", "Wrocław"));


		// WYPISANIE TYLKO OSÓB Z WARSZAWY
		System.out.println("--- WYPISANIE OSÓB Z WARSZAWY ---");
		personList.stream().filter(p -> p.getCity().equals("Warszawa")).forEach(x -> System.out.println(x));

		// WYPISANIE TYLKO OSÓB Z WARSZAWY W FORMACIE (NAZWISKO Imię, Miasto)
		// KOWALSKI Adam, Warszawa
		System.out.println("--- WYPISANIE OSÓB Z WARSZAWY w podanym formacie ---");
		personList.stream()
				.filter(p -> p.getCity().equals("Warszawa"))
				.map(p -> p.getLastName().toUpperCase() + " " + p.getFirstName() + ", " + p.getCity())
				.forEach(x -> System.out.println(x));

		// POSORTOWAC OSOBY PO NAZWISKACH
		System.out.println("--- OSOBY POSORTOWANE PO NAZWISKACH ---");
		personList.stream()
				.sorted((p1, p2) -> p1.getLastName().compareTo(p2.getLastName()))
//				.sorted((p1, p2) -> {
//							return p1.getLastName().compareTo(p2.getLastName());
//						}
//				)
				.forEach(x -> System.out.println(x));

		// POSORTOWAC OSOBY PO Nazwisku, Imieniu, Mieście
		System.out.println("--- OSOBY POSORTOWANE PO NAZWISKU, IMIENIU, MIESCIE ---");
		personList.stream().sorted((p1, p2) -> {

//			"ABC".equals("BCD") TO TO SAMO CO "ABC".compareTo("BCD") == 0

			if (!p1.getLastName().equals(p2.getLastName())) {
				return p1.getLastName().compareTo(p2.getLastName());
			}
			// to to samo co
//			if (p1.getLastName().compareTo(p2.getLastName()) != 0) {
//				return p1.getLastName().compareTo(p2.getLastName());
//			}

			if (!p1.getFirstName().equals(p2.getFirstName())) {
				return p1.getFirstName().compareTo(p2.getFirstName());
			}

			return p1.getCity().compareTo(p2.getCity());


		}).forEach(x -> System.out.println(x));

		// 4. z użyciem collectora joining wypisać osoby (stworzyć Stringa)
		// START
		// Adam Kowalski
		// ...
		// Piotr Kowalski
		// KONIEC
		System.out.println("Wypisanie osób z użyciem joining");
		String text = personList.stream()
				.map(p -> p.getFirstName() + " " + p.getLastName())
				.collect(Collectors.joining("\n", "START\n", "\nKONIEC"));
		System.out.println(text);


	}
}
