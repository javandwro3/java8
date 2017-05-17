package pl.jwrabel.trainings.javandwro3.java8;

import java.util.List;
import java.util.Optional;

/**
 * Created by jakubwrabel on 21.03.2017.
 */
public class OptionalTest {
	public static void main(String[] args) {
		String userName = userName().orElse("UNDEFINED");

		System.out.println(userName.length());

//		System.out.println(userName().orElse("Undefined"));

//		splitToLetters(Optional.ofNullable("s"));


		// metodę do liczenia miejsca zerowego funkcji kwadratowej
		// deltaX = b^2 - 4ac
		// x1 = (-b - sqrt(delta))/2a
		// x2 = (-b + sqrt(delta))/2a
	}

	//	public static Optional<List<Double>> findZeroValues(Optional<Double> a, Optional<Double> b, Optional<Double> c) {
	public static Optional<List<Double>> findZeroValues(Double a, Optional<Double> b, Optional<Double> c) {
		Optional<Double> optA = Optional.ofNullable(a);
		int x = 1;
		Integer xObj = 1;


//		double a1 = a.orElseThrow(() -> new IllegalArgumentException("A cannot be null"));

		// TO TO SAMO CO
//		if(a.isPresent()){
//			double a1 = a.get();
//		} else {
//			throw new IllegalArgumentException();
//		}

		return null;
	}

	public static Optional<String> userName() {
		return Optional.of("Adam Kowalski");
//		return Optional.ofNullable(null);
	}

	public static String[] splitToLetters(String text) {
		if (text == null) {
			text = "";
		}

//		return text.orElse("").split("");
//		return text.orElseThrow(() -> new IllegalArgumentException("MUSISZ PODAC TEKST")).split("");
		return new String[10];
	}
}
