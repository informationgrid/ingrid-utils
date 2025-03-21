/*
 * **************************************************-
 * ingrid-utils
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
package de.ingrid.utils.enumeration;

/**
 * Class with static Utility methods handling Enumerations !
 * 
 * @author Martin Maidhof
 */
public class DbEnumUtil {

	/**
	 * Map a database value (can be a String etc., ALSO NULL) to an Enumeration Constant.
	 * NOTICE: The Enumeration Type must implement IDbEnum !!!
	 * 
	 * @param <T>
	 * @param enumType
	 *            Enumeration Type Class, e.g. WorkState.class
	 * @param dbValue
	 *            the database value, also works with null dbValue
	 * @return an Enumeration Constant, e.g. WorkState.V or null, if no mapping
	 *         Enumeration Constant can be found
	 */
	static public <T extends Enum<T>> T mapDatabaseToEnumConst(
			Class<T> enumType, Object dbValue) {
		T[] myEnumConsts = enumType.getEnumConstants();
		T result = null;

		Object currDbValue;
		for (int i = 0; i < myEnumConsts.length; i++) {
			if (!(myEnumConsts[i] instanceof IDbEnum)) {
				break;
			}
			currDbValue = ((IDbEnum) myEnumConsts[i]).getDbValue();
			if (currDbValue == null) {
				if (dbValue == null) {
					result = myEnumConsts[i];
					break;
				}
			} else if (dbValue != null) {
				if (currDbValue.equals(dbValue)) {
					result = myEnumConsts[i];
					break;
				}
			}
		}

		return result;
	}

	/**
	 * Map an int value to an Enumeration Constant.
	 * @param <T>
	 * @param enumType
	 *            Enumeration Type Class, e.g. WorkState.class
	 * @param ordinalValue
	 *            the int value of the Enumeration Constant (ordinal())
	 * @return an Enumeration Constant, e.g. WorkState.V or null, if no mapping
	 *         Enumeration Constant can be found
	 */
	static public <T extends Enum<T>> T mapOrdinalToEnumConst(
			Class<T> enumType, int ordinalValue) {
		T[] myEnumConsts = enumType.getEnumConstants();
		T result = null;
		for (int i = 0; i < myEnumConsts.length; i++) {
			if (myEnumConsts[i].ordinal() == ordinalValue) {
				result = myEnumConsts[i];				
				break;
			}
		}

		return result;
	}

	/**
	 * Get all Descriptions (toString()) of the Enumeration Constants of an
	 * Enumeration Type.
	 * 
	 * @param <T>
	 * @param enumType
	 *            e.g. WorkState.class
	 * @return
	 */
	static public <T extends Enum<T>> String[] getDescriptions(Class<T> enumType) {
		T[] myEnumConsts = enumType.getEnumConstants();
		String[] ret = new String[myEnumConsts.length];
		for (int i = 0; i < myEnumConsts.length; i++) {
			ret[i] = myEnumConsts[i].toString();
		}
		return ret;
	}

	/**
	 * Get all database values of the Enumeration Constants of an Enumeration
	 * Type. NOTICE: The Enumeration Type must implement IDbEnum !!!
	 * 
	 * @param <T>
	 * @param enumType
	 *            e.g. WorkState.class
	 * @return
	 */
	static public <T extends Enum<T>> Object[] getDbValues(Class<T> enumType) {
		T[] myEnumConsts = enumType.getEnumConstants();
		Object[] ret = new Object[myEnumConsts.length];

		for (int i = 0; i < myEnumConsts.length; i++) {
			if (myEnumConsts[i] instanceof IDbEnum) {
				ret[i] = ((IDbEnum) myEnumConsts[i]).getDbValue();
			} else {
				ret[i] = null;
			}
		}
		return ret;
	}

	/**
	 * Get all database values of the Enumeration Constants of an Enumeration
	 * Type AS Strings ! NOTICE: The Enumeration Type must implement IDbEnum
	 * !!!
	 * 
	 * @param <T>
	 * @param enumType
	 *            e.g. WorkState.class
	 * @return
	 */
	static public <T extends Enum<T>> String[] getDbValuesAsString(
			Class<T> enumType) {
		Object[] dbValues = DbEnumUtil.getDbValues(enumType);
		String[] ret = new String[dbValues.length];

		for (int i = 0; i < dbValues.length; i++) {
			ret[i] = null;
			if (dbValues[i] != null) {
				ret[i] = dbValues[i].toString();
			}
		}
		return ret;
	}

	/**
	 * Returns the ordinals of the Enumeration Constants in the passed array separated by the passed separator.
	 * @param <T> the Enumeration Type
	 * @param enumConsts an Array of Enumeration Constants of Type T
	 * @param separator the character separating the ordinals
	 * @return
	 */
	static public <T extends Enum<T>> String getSeparatedOrdinals(
			T[] enumConsts,
			char separator) {
		
		StringBuffer result = new StringBuffer("");
		for (int i = 0; i < enumConsts.length; i++) {
			if (i != 0) {
				result.append(separator);
			}
			result.append(enumConsts[i].ordinal());
		}
		return result.toString();
	}

	/**
	 * Returns the names of the Enumeration Constants in the passed array separated by the passed separator and
	 * each name enclosed by the passed "enclosor".
	 * @param <T> the Enumeration Type
	 * @param enumConsts an Array of Enumeration Constants of Type T
	 * @param separator the character separating the names
	 * @param enclosor the string enclosing each name, pass empty string if no enclosing wanted 
	 * @return
	 */
	static public <T extends Enum<T>> String getSeparatedNames(
			T[] enumConsts,
			char separator,
			String enclosor) {
		
		StringBuffer result = new StringBuffer("");
		for (int i = 0; i < enumConsts.length; i++) {
			if (i != 0) {
				result.append(separator);
			}
			result.append(enclosor);
			result.append(enumConsts[i].name());
			result.append(enclosor);
		}
		return result.toString();
	}
}
