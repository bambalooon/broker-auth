package pl.bb.broker.security.settings;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 25.05.14
 * Time: 18:59
 * To change this template use File | Settings | File Templates.
 */
public class SecuritySettings {
    public static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,40})";
    public static final String USERNAME_PATTERN = "^[a-zA-Z\\d_]{3,40}$";
    public static final String PHONE_PATTERN = "^(\\+?[0-9]{11}|[0-9]{9})$";
    public static final int PASSWORD_MIN = 6;
    public static final int PASSWORD_MAX = 40;
    public static final int USERNAME_MIN = 3;
    public static final int USERNAME_MAX = 40;
    public static final int PASSWORDHASH_LEN = 44;
    public static final String hashAlgorithm = "SHA-256";
    public static final String hashEncoding = "BASE64";
    public static final String hashCharset = "UTF-8";
}
