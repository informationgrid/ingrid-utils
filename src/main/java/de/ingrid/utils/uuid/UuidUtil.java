/*-
 * **************************************************-
 * InGrid Utils
 * ==================================================
 * Copyright (C) 2014 - 2025 wemove digital solutions GmbH
 * ==================================================
 * Licensed under the EUPL, Version 1.2 or – as soon they will be
 * approved by the European Commission - subsequent versions of the
 * EUPL (the "Licence");
 * 
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 * 
 * https://joinup.ec.europa.eu/software/page/eupl
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 * **************************************************#
 */
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

