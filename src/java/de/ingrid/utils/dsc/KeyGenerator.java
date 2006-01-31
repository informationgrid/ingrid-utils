/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */
package de.ingrid.utils.dsc;

public class KeyGenerator {

	private static KeyGenerator fInstance;

	private int fCounter;

	private KeyGenerator() {
		fCounter = 0;
	}

	public static KeyGenerator getInstance() {
		synchronized (KeyGenerator.class) {
			if (fInstance == null) {
				fInstance = new KeyGenerator();
			}
		}
		return fInstance;
	}

	public int getNextKey() {
		synchronized (KeyGenerator.class) {
			return fCounter++;
		}
	}
}
