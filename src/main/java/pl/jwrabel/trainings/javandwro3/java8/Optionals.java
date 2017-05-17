package pl.jwrabel.trainings.javandwro3.java8;

import java.awt.Point;
import java.util.Optional;

/**
 * Created by jakubwrabel on 21.03.2017.
 */
public class Optionals {
	public static void main(String[] args) {
		// TWORZENIE OPTIONAL
		// 1. Optional.empty()
		Optional<Object> emptyOptional = Optional.empty();

		// 2. Optional.of(...)
		String a = "A";
		Optional<String> optional2 = Optional.of(a);
		Point pointNull = null;
//        Optional<Point> point1 = Optional.of(null); // wywali, bo pointNull === null



		// 3. Optional.ofNullable(...)
		Optional<String> optional3 = Optional.ofNullable("ab");
		Optional<Point> optional31 = Optional.ofNullable(pointNull);



		String nullString = null;
		String ab = new String();

		Optional<String> testOptional = Optional.ofNullable(" ");

		// METODY OPTIONALI
		// 1. isPresent
		String s = "abc";
		Optional<String> stringOptional = Optional.of(s);

		stringOptional.isPresent(); // true

		String sNull = null;
		Optional<String> nullStringOpt = Optional.ofNullable(nullString);
		nullStringOpt.isPresent(); // false

		// 2. get
		String s1 = stringOptional.get(); // wywali się dla null

		// 3. orElse
		String myString = "ABC"; // może być null
		Optional<String> myStringOptional = Optional.ofNullable(myString);

		String orElse = myStringOptional.orElse("DEFAULT");


		// 4. orElseGet
		String value3 = testOptional.orElseGet(() -> System.lineSeparator());


		// 5. orElseThrow
		String valu4 = testOptional.orElseThrow(() -> new IllegalArgumentException());

		// 6. filter
		String value5 = testOptional.filter(x -> !x.isEmpty()).orElse("BLA");


		String firstName = "Axxxxxx";
		Optional<String> firstName1 = Optional.ofNullable(firstName);
		firstName1.filter(x -> x.startsWith("A")).orElse("Default");

//        if(firstName != null){
//            if(firstName.startsWith("A")){
//                return firstName;
//            }
//            return "Default";
//        }
//        return "Default";

//		 7. map
//		Optional<Double> bytes = pointOptional.map(x -> x.getX());

//
//		// 8. ifPresent(Consumer)
//		testOptional.ifPresent(x -> System.out.println(x));
//
//
//		// 9. flatMap (map na metodzie zwracającej nulla)
////        Optional<U> b = Optional.ofNullable(Optional.of("B")).flatMap(x -> x.get());


	}
}
