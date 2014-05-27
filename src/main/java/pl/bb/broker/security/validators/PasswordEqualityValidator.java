package pl.bb.broker.security.validators;

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
 * Time: 12:23
 * To change this template use File | Settings | File Templates.
 */

@FacesValidator(value = "passwordEqualityValidator")
public class PasswordEqualityValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value)
            throws ValidatorException {

        String pwd1 = (String) value;

        UIInput passwordField = (UIInput) component.getAttributes().get("password2");
        if(passwordField==null) {
            throw new IllegalArgumentException("Unable to find component!");
        }

        String pwd2 = (String) passwordField.getSubmittedValue();

        if(pwd1 == null || pwd1.isEmpty() || pwd2 == null || pwd2.isEmpty())
            return; //let required=true do its job

        if(!pwd1.equals(pwd2)) {
            passwordField.setValid(false);
            FacesMessage msg = new FacesMessage("Passwords are not equal");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);   //change
        }


    }
}
