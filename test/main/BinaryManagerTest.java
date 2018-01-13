package main;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BinaryManagerTest {

	@Test
	public void convertToBinary() {
		BinaryManager manager = new BinaryManager(0, 0);
		List<Boolean> expected = new ArrayList<>();
		expected.add(Boolean.TRUE);
		expected.add(Boolean.TRUE);
		expected.add(Boolean.TRUE);
		expected.add(Boolean.FALSE);

		Assert.assertEquals(expected, manager.convertBinaryToListOfBoolean(7));
	}

}