package pl.bb.broker.brokerauthentication.ws.authentication;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 23.05.14
 * Time: 11:55
 * To change this template use File | Settings | File Templates.
 */

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public interface AuthenticationService {

    @WebMethod
    String getHelloWorldAsString(String name);

    @WebMethod
    String getHelloUserAsString();
}
