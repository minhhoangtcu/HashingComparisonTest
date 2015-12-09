package hashing.resolutionMethods;

import static org.junit.Assert.*;

import org.junit.Test;

public class HashTest {

	@Test
	public void divisionHashTest() {
		// 0 in utf-8 is 48
		assertEquals(48, Hash.divisionHash("0"));
		// A in utf-8 is 65
		assertEquals(65, Hash.divisionHash("A"));
		// V in utf-8 is 86
		assertEquals(86, Hash.divisionHash("V"));
		// 0 in utf-8 is 48
		assertEquals((48<<8)+(48), Hash.divisionHash("00"));
		// 0 in utf-8 is 48
		assertNotEquals((48<<16)+(48<<8)+48, Hash.divisionHash("0000"));
	}

}
