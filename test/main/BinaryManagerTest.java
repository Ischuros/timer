package main;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BinaryManagerTest {

	@Test
	public void convertToBinary() {
		BinaryManager manager = new BinaryManager(0, 0);
		List<Boolean> expected = new ArrayList<>();
		expected.add(Boolean.TRUE);
		expected.add(Boolean.TRUE);
		expected.add(Boolean.TRUE);
		expected.add(Boolean.FALSE);

		assertEquals(expected, manager.convertBinaryToListOfBoolean(7));
	}

	@Test
	public void testTimeSplitting1() {
		BinaryManager manager = new BinaryManager(120, 0);

		assertEquals(0, manager.getHoursUnity());
		assertEquals(0, manager.getMinutesDecade());
		assertEquals(2, manager.getMinutesUnity());
		assertEquals(0, manager.getSecondsDecade());
		assertEquals(0, manager.getSecondsUnity());
		assertEquals(0, manager.getTenthSeconds());
	}

	@Test
	public void testTimeSplitting2() {
		BinaryManager manager = new BinaryManager(119, 7);

		assertEquals(0, manager.getHoursUnity());
		assertEquals(0, manager.getMinutesDecade());
		assertEquals(1, manager.getMinutesUnity());
		assertEquals(5, manager.getSecondsDecade());
		assertEquals(9, manager.getSecondsUnity());
		assertEquals(7, manager.getTenthSeconds());
	}
}