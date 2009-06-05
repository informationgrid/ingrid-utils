package de.ingrid.utils.messages;
import java.util.Map;
import java.util.TreeMap;


public class SortedCategorizedKeys {

	public static Map getSortedCategorizedKeys(CategorizedKeys catKeys,
			String category) {

		Map map = new TreeMap();
		String[] keysForCategory = catKeys.getKeysForCategory(category);
		for (int i = 0; i < keysForCategory.length; i++) {
			String keyForCategory = keysForCategory[i];
			String value = catKeys.getString(keyForCategory);
			map.put(value, keyForCategory);
		}
		return map;
	}
}
