package pl.bb.broker.brokerauthentication.endpoint;

import pl.bb.broker.brokerauthentication.ws.authentication.AuthenticationServiceImpl;

import javax.xml.ws.Endpoint;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 23.05.14
 * Time: 12:08
 * To change this template use File | Settings | File Templates.
 */
public class AuthenticationPublisher {

    public static void main(String... args) {
        Endpoint.publish("http://localhost:8080/ws/hello", new AuthenticationServiceImpl());
    }
}
