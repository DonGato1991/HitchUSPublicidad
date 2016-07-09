/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author JuanAndresCaspi
 */
public class UtilUI {

    private static final String userNameChars = "[\\w.\\-]+"; //letters, dots, hyphens
    private static final String alphaNums = "\\w+";
    private static final String dot = "\\.";
    

    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern email = Pattern.compile(EMAIL_PATTERN);

    /**
     * Validate hex with regular expression
     *
     * @param hex hex for validation
     * @return true valid hex, false invalid hex
     */
    public static boolean validate(String hex) {
        Matcher matcher;
        boolean success = false;
        matcher = email.matcher(hex);
        success = matcher.matches();
        return success;

    }

}
