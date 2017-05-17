package pl.jwrabel.trainings.javandwro3.java8;


import java.util.*;
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
		System.out.println("Wypisanie osób z użyciem joining (Stworzenie jednego Stringa z listy osób)");
		String text = personList.stream()
				.map(p -> p.getFirstName() + " " + p.getLastName())
				.collect(Collectors.joining("\n", "START\n", "\nKONIEC"));
		System.out.println(text);

		// 5. z użyciem summary statistics
		// podać średnią długość nazwisk osób
		IntSummaryStatistics intSummaryStatistics
				= personList.stream().mapToInt(p -> p.getLastName().length()).summaryStatistics();
		double average = intSummaryStatistics.getAverage();
		System.out.println("Srednia dlugosc nazwiska: " + average);

		// 6. Wypisać osoby w postaci
		// WARSZAWA
		// 	Adam Nowak
		// WROCŁAW
		//  Piotr Kowalski
		// 	Adam Kowalski
		System.out.println("----- WYPISANIE OSÓB Z MIASTA ------");

		Map<String, List<Person>> citiesPeopleMap = personList.stream().collect(Collectors.groupingBy(p -> p.getCity()));


		Set<Map.Entry<String, List<Person>>> entries = citiesPeopleMap.entrySet();
		for (Map.Entry<String, List<Person>> entry : entries) {
			System.out.println(entry.getKey().toUpperCase());

			List<Person> people = entry.getValue();
			for (Person person : people) {
				System.out.println("\t" + person.getFirstName() + " " + person.getLastName());
			}
		}
		// TO TO SAMO CO TO:
		citiesPeopleMap.forEach((key, value) -> {
			System.out.println(key.toUpperCase());
			value.forEach(p -> System.out.println("\t" + p.getFirstName() + " " + p.getLastName()));
		});

		// A CAŁE ZADANIE TO TO SAMO
		personList.stream()
				.collect(Collectors.groupingBy(p -> p.getCity()))
				.forEach((key, value) -> {
					System.out.println(key.toUpperCase());
					value.forEach(p -> System.out.println("\t" + p.getFirstName() + " " + p.getLastName()));
				});

		// 7. wypisać osoby
		// WROCŁAW -> 2 osoby
		// WARSZAWA -> 1 osoba
		personList.stream()
				.collect(Collectors.groupingBy(p -> p.getCity()))
				.forEach((city, peoples) -> {
					System.out.println("Miasto: " + city);
					System.out.println("Liczba osób: " + peoples.size());
				});

		// LUB
		Set<String> citiesNames = personList.stream().map(p -> p.getCity()).collect(Collectors.toSet());

		citiesNames.forEach(city -> {
			System.out.println("Miasto: " + city);

			long numberOfPeopleFromCity = personList.stream().filter(p -> p.getCity().equals(city)).count();
			System.out.println("Liczba osób: " + numberOfPeopleFromCity);
		});

		// 8.
		// ADAM -> 2 miasta
		// PIOTR -> 1 miast
		System.out.println("-------------------");
		personList.stream()
				.collect(Collectors.groupingBy(p -> p.getFirstName()))
				.forEach((firstName, peoples) -> {
					System.out.println("IMIĘ: " + firstName);
					long citiesNumber = peoples.stream().map(p -> p.getCity()).distinct().count();
					System.out.println("Liczba miast: " + citiesNumber);
				});

	}
}
