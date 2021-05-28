package de.ingrid.utils.uuid;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class UuidUtilTest {

	@Test
	public void uuidType3Test() {
		UUID foo = UuidUtil.uuidType3(UuidUtil.NAMESPACE_DNS, "foo");
		UUID bar = UuidUtil.uuidType3(UuidUtil.NAMESPACE_DNS, "bar");

		assertEquals("3f46ae03-c654-36b0-a55d-cd0aa042c9f2", foo.toString());
		assertEquals("b4a1f07b-64b1-3112-b69c-d89a0185c7c6", bar.toString());
	}
}

