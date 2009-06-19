/**
 * 
 */
package de.ingrid.utils.udk;

/**
 * @author joachim
 *
 */
public class UtilsString {
	
	public static String transformNumberStrToIGCNumber(String val) {
		// x,xxx.xx
		if (val.matches("[-+]?[0-9]*(,[0-9][0-9][0-9])+[0-9]*\\.[0-9]+")) {
			return val.replaceAll(",", "");
		// x.xxx,xx
		} else if (val.matches("[-+]?[0-9]*(\\.[0-9][0-9][0-9])+[0-9]*\\,[0-9]+")){
			return val.replaceAll("\\.", "").replaceAll(",", "\\.");
		// x.xxx.xxx
		} else if (val.matches("(\\d)+(\\.[0][0][0])+")){
			return val.replaceAll("\\.", "");
		// xx,x
		} else if (val.matches("(\\d)+,(\\d)+")){
			return val.replaceAll(",", "\\.");
		} else {
			return val;		
		}
	}

}
