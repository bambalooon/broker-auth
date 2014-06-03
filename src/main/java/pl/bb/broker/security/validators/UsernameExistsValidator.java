package pl.bb.broker.security.validators;

import pl.bb.broker.brokerdb.util.BrokerDBAuthUtil;
import pl.bb.broker.brokerdb.util.BrokerDBUtil;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 26.05.14
 * Time: 14:58
 * To change this template use File | Settings | File Templates.
 */

@FacesValidator(value = "usernameExistsValidator")
public class UsernameExistsValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value)
            throws ValidatorException {

        String username = (String) value;

        if(username == null || username.isEmpty())
            return; //let required=true do its job

        if(BrokerDBAuthUtil.FACTORY.userExists(username)) {
            FacesMessage msg = new FacesMessage("Username already exists");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);   //change
        }


    }
}
