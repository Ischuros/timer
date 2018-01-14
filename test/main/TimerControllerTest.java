package main;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TimerControllerTest {

	@Test
	public void testParseValue1() {
		assertEquals("1:29:36.5", new TimerController().parseValue("129365"));
	}

	@Test
	public void testParseValue2() {
		assertEquals("36.5", new TimerController().parseValue("365"));
	}

	@Test
	public void testParseValue3() {
		assertEquals("0.5", new TimerController().parseValue("5"));
	}

	@Test
	public void testZeroRegex() {
		assertEquals("365", new TimerController().removeStartingZero("00000365"));
	}
}