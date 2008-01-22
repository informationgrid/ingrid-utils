/**
 * 
 */
package de.ingrid.utils.udk;

/**
 * @author joachim
 * 
 */
public class CodeListEntry {
	Long domainId;

	Long codeListId;

	Long langId;

	String value;

	public CodeListEntry(String value, Long codeListId, Long domainId, Long langId) {
		this.value = value;
		this.codeListId = codeListId;
		this.domainId = domainId;
		this.langId = langId;
	}

	/**
	 * @return the codeListId
	 */
	public Long getCodeListId() {
		return codeListId;
	}

	/**
	 * @param codeListId
	 *            the codeListId to set
	 */
	public void setCodeListId(Long codeListId) {
		this.codeListId = codeListId;
	}

	/**
	 * @return the domainId
	 */
	public Long getDomainId() {
		return domainId;
	}

	/**
	 * @param domainId
	 *            the domainId to set
	 */
	public void setDomainId(Long domainId) {
		this.domainId = domainId;
	}

	/**
	 * @return the langId
	 */
	public Long getLangId() {
		return langId;
	}

	/**
	 * @param langId
	 *            the langId to set
	 */
	public void setLangId(Long langId) {
		this.langId = langId;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
