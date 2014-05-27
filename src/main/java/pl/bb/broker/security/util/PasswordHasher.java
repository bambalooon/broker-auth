package pl.bb.broker.security.util;

import org.jboss.crypto.CryptoUtil;
import pl.bb.broker.security.settings.SecuritySettings;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 26.05.14
 * Time: 12:07
 * To change this template use File | Settings | File Templates.
 */
public class PasswordHasher {
    public static String hashPassword(String username, String password) {
        return CryptoUtil.createPasswordHash(SecuritySettings.hashAlgorithm,
                                             SecuritySettings.hashEncoding,
                                             SecuritySettings.hashCharset,
                                             username,
                                             password);
    }
}
