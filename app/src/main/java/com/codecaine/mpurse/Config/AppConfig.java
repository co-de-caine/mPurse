package com.codecaine.mpurse.config;

/**
 * Contains the configuration for the app
 *
 * @author Deepankar
 */
public class AppConfig {

    // Remote Server user Login url
    public static String REMOTE_URL_LOGIN = "http://teamdevopsmnnit.3eeweb.com/mpurse/Login.php";

    // Local Server user Login url
    public static String LOCAL_URL_LOGIN = "http://192.168.65.1/mpurse/Login.php";

    // Local Server user registration url
    public static String LOCAL_URL_REGISTER = "http://192.168.65.1/mpurse/register.php";

    //Local Server isUpdated url
    public static String LOCAL_URL_ISUPDATED = "http://192.168.65.1/mpurse/isupdated.php";

    //Local Server getUserUpdates url
    public static String LOCAL_URL_USERUPDATES = "http://192.168.65.1/mpurse/getuserupdates.php";

    //Local Server sendRequestForMoneyurl
    public static String LOCAL_URL_REQUESTMONEY = "http://192.168.65.1/mpurse/request.php";

}
