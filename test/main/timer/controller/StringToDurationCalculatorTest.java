package main.timer.controller;

import main.timer.controller.StringToDurationCalculator;
import org.junit.Test;

import static org.junit.Assert.*;

public class StringToDurationCalculatorTest {

	@Test
	public void calculateDurationInMillis() {
		assertEquals(2*60*1000, StringToDurationCalculator.getMilliSeconds("2:00.0"));
		assertEquals(2*60*1000, StringToDurationCalculator.getMilliSeconds("85:00.0"));
		assertEquals(60*1000+59*1000+3*100, StringToDurationCalculator.getMilliSeconds("1:59.3"));
		assertEquals(2*3600*1000+25*60*1000+12*1000+7*100, StringToDurationCalculator.getMilliSeconds("2:25:12.7"));
	}
}