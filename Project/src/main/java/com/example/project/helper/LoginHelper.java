package com.example.project.helper;

import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author Ulfa
 */
public class LoginHelper {
    public static boolean getSamePassword (String password, String repassword){
        String pass = null;
        try {
            pass = EnkripHelper.hash256(password);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        String repass = null;
        try {
            repass = EnkripHelper.hash256(repassword);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pass.equals(repass);
    }
    
    public static boolean isValid(String passwordhere) {

        Pattern specailCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
        Pattern lowerCasePatten = Pattern.compile("[a-z ]");
        Pattern digitCasePatten = Pattern.compile("[0-9 ]");
//        errorList.clear();

        boolean flag=true;

        if (passwordhere.length() < 8) {
//            errorList.add("Password lenght must have alleast 8 character !!");
            flag=false;
        }
        if (!specailCharPatten.matcher(passwordhere).find()) {
//            errorList.add("Password must have atleast one specail character !!");
            flag=false;
        }
        if (!UpperCasePatten.matcher(passwordhere).find()) {
//            errorList.add("Password must have atleast one uppercase character !!");
            flag=false;
        }
        if (!lowerCasePatten.matcher(passwordhere).find()) {
//            errorList.add("Password must have atleast one lowercase character !!");
            flag=false;
        }
        if (!digitCasePatten.matcher(passwordhere).find()) {
//            errorList.add("Password must have atleast one digit character !!");
            flag=false;
        }
        return flag;
    }
}
