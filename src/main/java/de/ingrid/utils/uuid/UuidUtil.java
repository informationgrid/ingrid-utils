package de.ingrid.utils.uuid;

import java.nio.ByteBuffer;
import java.util.UUID;

import static java.nio.charset.StandardCharsets.UTF_8;

public final class UuidUtil {

	// Namespaces defined in RFC 4122
	public static final String NAMESPACE_DNS = "6ba7b810-9dad-11d1-80b4-00c04fd430c8";
	public static final String NAMESPACE_URL = "6ba7b811-9dad-11d1-80b4-00c04fd430c8";
	public static final String NAMESPACE_OID = "6ba7b812-9dad-11d1-80b4-00c04fd430c8";
	public static final String NAMESPACE_X500 = "6ba7b814-9dad-11d1-80b4-00c04fd430c8";

	private UuidUtil() {
		// disable instantiation
	}

	/**
	 * Function to generate Type 3 UUIDs from strings.
	 *
	 * @param ns namespace to use for UUID generation
	 * @param s string for which to generate UUID
	 * @return UUID generated from the given namespace and string
	 */
	public static UUID uuidType3(String ns, String s) {
		UUID nsUuid = UUID.fromString(ns);
		byte[] bytes = s.getBytes(UTF_8);

		ByteBuffer buffer = ByteBuffer.wrap(new byte[16 + bytes.length]);
		buffer.putLong(nsUuid.getMostSignificantBits());
		buffer.putLong(nsUuid.getLeastSignificantBits());
		buffer.put(bytes);

		return UUID.nameUUIDFromBytes(buffer.array());
	}

}

