/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2018 wemove digital solutions GmbH
 * ==================================================
 * Licensed under the EUPL, Version 1.1 or – as soon they will be
 * approved by the European Commission - subsequent versions of the
 * EUPL (the "Licence");
 * 
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 * 
 * http://ec.europa.eu/idabc/eupl5
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 * **************************************************#
 */
/*
 * Copyright (c) 1997-2005 by media style GmbH
 * 
 * $Source: /cvs/asp-search/src/java/com/ms/aspsearch/PermissionDeniedException.java,v $
 */

package de.ingrid.utils.enumeration;

import junit.framework.TestCase;

public class DbEnumTest extends TestCase {

	public enum IdcPermission implements IDbEnum {
		// Entity Permissions
		WRITE_SINGLE("write", "Einzelberechtigung"),
		WRITE_TREE("write-tree", "Teilbaumberechtigung"),
		WRITE_SUBNODE("write-subnode", "Unterknotenberechtigung"),
		// NOT PERSISTABLE !!! used to tell frontend no write permission on entity, but on subtree ! 
		// (e.g. user not QA but has write-tree permission on entity in state Q)  
		DUMMY_WRITE_SUBTREE("write-subtree", "Unterbaumberechtigung"),

		// User Permissions (bound to group)
		CREATE_ROOT("create-root", "Root anlegen"),
		QUALITY_ASSURANCE("qa", "Qualitätssicherung");

		/**
		 * @param dbValue THIS IS ALSO THE client side STRING USED IN MAP TO IDENTIFY PERMISSION
		 * @param description arbitrary description
		 */
		IdcPermission(String dbValue, String description) {
			this.dbValue = dbValue;
			this.description = description;
		}
		/** THIS IS ALSO THE client side STRING USED IN MAP TO IDENTIFY PERMISSION !!! */
		public String getDbValue() {
			return dbValue;
		}
		public String toString() {
			return description;
		}
		String dbValue;
		String description;
	}

    public void testDbEnum() throws Exception {
		IdcPermission idcPerm = DbEnumUtil.mapDatabaseToEnumConst(IdcPermission.class, "write");
        assertEquals(idcPerm, IdcPermission.WRITE_SINGLE);

        idcPerm = DbEnumUtil.mapOrdinalToEnumConst(IdcPermission.class, 0);
        assertEquals(idcPerm, IdcPermission.WRITE_SINGLE);

        Object[] dbValues = DbEnumUtil.getDbValues(IdcPermission.class);
        assertEquals(dbValues.length, 6);
        assertEquals(dbValues[0].toString(), "write");
        assertEquals(dbValues[5].toString(), "qa");

        String[] dbValuesString = DbEnumUtil.getDbValuesAsString(IdcPermission.class);
        assertEquals(dbValues.length, 6);
        assertEquals(dbValuesString[0], "write");
        assertEquals(dbValuesString[5], "qa");

        String[] descriptions = DbEnumUtil.getDescriptions(IdcPermission.class);
        assertEquals(dbValues.length, 6);
        assertEquals(descriptions[0], "Einzelberechtigung");
        assertEquals(descriptions[5], "Qualitätssicherung");

        String names = DbEnumUtil.getSeparatedNames(
        		new IdcPermission[]{IdcPermission.WRITE_SINGLE, IdcPermission.DUMMY_WRITE_SUBTREE},
        		'#', "'");
        assertEquals(names, "'WRITE_SINGLE'#'DUMMY_WRITE_SUBTREE'");

        String ordinals = DbEnumUtil.getSeparatedOrdinals(
        		new IdcPermission[]{IdcPermission.WRITE_SINGLE, IdcPermission.DUMMY_WRITE_SUBTREE},
        		'#');
        assertEquals(ordinals, "0#3");
    }
}
