package pl.jwrabel.trainings.javandwro3.java8;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by jakubwrabel on 15/05/2017.
 */
public class Streams {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(5);
		list.add(4);
		list.add(10);
		list.add(3);

		// ZAMIANA LISTY (KOLEKCJI) NA STRUMIEN
		list.stream();

		// ZAMIANA TABLICY NA STRUMIEN
		int[] array = new int[10];
		Arrays.stream(array);


		list.stream()
				.forEach(
						new Consumer<Integer>() {
							@Override
							public void accept(Integer x) {
								System.out.println(x);
							}
						}
				);
		// TO TO SAMO CO
		list.stream().forEach(x -> System.out.println(x));

		// JEDEN PARAMETR METODY, JEDNA OPERACJA W METODZIE
		list.stream().forEach(x -> System.out.println(x));
		list.stream().forEach(element -> System.out.println(element));


		list.stream()
				.forEach(
						new Consumer<Integer>() {
							@Override
							public void accept(Integer integer) {
								System.out.println(integer);
								System.out.println("Twice:" + integer);
							}
						}
				);

		// JEDEN PARAMETR, KILKA INSTRUKCJI
		list.stream().forEach(x -> {
			System.out.println(x);
			System.out.println("Twice:" + x);
		});
		// efekt identyczny do
		for (Integer integer : list) {
			System.out.println(integer);
			System.out.println("Twice:" + integer);
		}

		// WIELE PARAMETRÓW
		list.stream().sorted((x, y) -> {
			if (x > y) {
				return -1;
			}
			if (x < y) {
				return 1;
			}
			return 0;

		});

		list.stream().forEach(x -> {
			System.out.println(x);
			System.out.println("Twice:" + x);
		});

		list.forEach(x -> System.out.println(x));

		list.forEach(System.out::println);

		// FILTROWANIE
		System.out.println("Przefiltrowany strumień");

		list.stream().filter(x -> x >= 5).forEach(x -> System.out.println(x));

		// to samo bez lambd
		list.stream().filter(new Predicate<Integer>() {
			@Override
			public boolean test(Integer integer) {
				return integer > 10;
			}
		}).forEach(new Consumer<Integer>() {
			@Override
			public void accept(Integer integer) {
				System.out.println(integer);
			}
		});

		// === ZADANIE === utworzyć listę z liczbami od 1 do 100, wypisać nieparzyste
		System.out.println("--- wypisanie liczb nieparzystych ---");
		List<Integer> integers = new ArrayList<>();
		for (int i = 1; i <= 100; i++) {
			integers.add(i);
		}

		integers.stream().filter(x -> x % 2 == 1).forEach(x -> System.out.println(x));
		integers.stream().filter(x -> {
					if (x > 10) {
						if (x % 2 == 1) {
							return true;
						}
					}
					return false;
				}
		).forEach(x -> System.out.println(x));

		// Sortowanie domyślne
		integers.stream().sorted().forEach(x -> System.out.println(x));

		// Sortowanie z własnym Comparatorem
		System.out.println("--- SORTOWANIE ---");
		integers.stream().sorted((x, y) -> {
			if (x < y) {
				return 1;
			}
			if (x > y) {
				return -1;
			}
			return 0;

		}).forEach(x -> System.out.println(x));


		// Matchery
		boolean isAnyNumberLargerThan5 = integers.stream().anyMatch(x -> x > 5);
		boolean areAllElementsLessThan10 = integers.stream().allMatch(x -> x < 10);
		boolean isThereNoElementLessThan0 = integers.stream().noneMatch(x -> x < 0);

		boolean isNoneLessThan0 = true;
		for (Integer integer : list) {
			if (integer < 0) {
				isNoneLessThan0 = false;
				break;
			}
		}


		// Count
		long amountOfLessThan5 = list.stream().filter(x -> x < 5).count();

		// Peek
		System.out.println("--- PEEK ---");
		list.stream()
				.peek(x -> System.out.println(x))
				.filter(x -> x > 4)
				.forEach(x -> System.out.println("Greated than 4: " + x));

		// MAPOWANIE
		System.out.println("--- MAPOWANIE ---");
		list.stream().map(x -> "Number: " + x).forEach(x -> System.out.println(x));

		// MAX, MIN
		Optional<Integer> max = list.stream().max((x, y) -> {
			if (x < y) {
				return -1;
			}
			if (x > y) {
				return 1;
			}
			return 0;

		});
		Optional<Integer> min = list.stream().min((x, y) -> {
			if (x < y) {
				return -1;
			}
			if (x > y) {
				return 1;
			}
			return 0;

		});

		// COLLECTOR
		List<Integer> collect = list.stream().filter(x -> x < 5).collect(Collectors.toList());
		Set<Integer> collectSet = list.stream().filter(x -> x < 5).collect(Collectors.toSet());
		String collect1 = list.stream().filter(x -> x < 5).map(x -> "" + x).collect(Collectors.joining(","));


	}


	public static void consume(MyConsumer myConsumer) {
		double x = 20;
		double multiply = myConsumer.multiply(x);
		System.out.println("Multiplied: " + multiply);
	}
}
