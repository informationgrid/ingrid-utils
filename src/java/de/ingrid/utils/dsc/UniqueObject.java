package de.ingrid.utils.dsc;


public class UniqueObject {

	private int fId = KeyGenerator.getInstance().getNextKey();

	public int getId() {
		return fId;
	}

}
