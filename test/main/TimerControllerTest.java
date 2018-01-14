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

	@Test
	public void calculateDurationInMillis() {
		assertEquals(2*60*1000, new TimerController().calculateDurationInMillis("2:00.0"));

		assertEquals(60*1000+59*1000+3*100, new TimerController().calculateDurationInMillis
				("1:59.3"));
		assertEquals(2*3600*1000+25*60*1000+12*1000+7*100, new TimerController()
				.calculateDurationInMillis
				("2:25:12.7"));
	}
}