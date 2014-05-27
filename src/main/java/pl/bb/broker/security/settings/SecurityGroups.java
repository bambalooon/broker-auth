package pl.bb.broker.security.settings;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 25.05.14
 * Time: 19:03
 * To change this template use File | Settings | File Templates.
 */
public enum SecurityGroups {
    CLIENT, COMPANY;

    public static final SecurityGroups STANDARD_USER_GROUP = CLIENT;
    public static final SecurityGroups STANDARD_COMPANY_GROUP = COMPANY;
}
