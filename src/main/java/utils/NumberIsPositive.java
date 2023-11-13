package utils;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class NumberIsPositive extends TypeSafeMatcher<Integer> {
	
	public void describeTo(Description description) {
		description.appendText("positive number: ");
	}

	@Override
	protected boolean matchesSafely(Integer item) {
		return item >0;
	}
	
	//returneaza implementarea, nu cream obiect al clasei sau instanta, pur si simplu chemam clasa si metodele ei
	public static Matcher<Integer> positiveNumber(){
		return new NumberIsPositive();
	}

}
