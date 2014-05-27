package pl.bb.broker.brokerauthentication.ws.authentication;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;


/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 23.05.14
 * Time: 12:06
 * To change this template use File | Settings | File Templates.
 */

@WebService(endpointInterface = "pl.bb.broker.brokerauthentication.ws.authentication.AuthenticationService")

public class AuthenticationServiceImpl implements AuthenticationService {
    @Resource
    WebServiceContext wsContext;

    @Override
    public String getHelloWorldAsString(String name) {
        return "Hello world "+name;
    }

    @Override
    public String getHelloUserAsString() {
        return "Hello "+wsContext.getUserPrincipal();
    }
}
