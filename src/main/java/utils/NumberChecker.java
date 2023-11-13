package utils;

import org.hamcrest.Description;

import org.hamcrest.*;
public class NumberChecker extends TypeSafeMatcher<String> {

	public void describeTo(Description description) {
		description.appendText("expected numbers but got: ");
	}

	@Override
	protected boolean matchesSafely(String item) {
		try {
			Integer.parseInt(item);	//"123" --> TRUE //Abc200 --> False
			return true;
			
		}catch(Exception e) {
		return false;
	}
	}
	
	//returneaza implementarea
	public static Matcher<String> numbersOnly(){
		return new NumberChecker();
	}

}
