package pl.bb.broker.security.beans;

import pl.bb.broker.brokerdb.broker.entities.UsersEntity;
import pl.bb.broker.brokerdb.util.BrokerDBAuthUtil;
import pl.bb.broker.security.settings.SecuritySettings;
import pl.bb.broker.security.util.PasswordHasher;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 25.05.14
 * Time: 17:48
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean
@RequestScoped
public class RegisterBean {
    @Size(min = SecuritySettings.USERNAME_MIN, max = SecuritySettings.USERNAME_MAX)
    @Pattern(regexp = SecuritySettings.USERNAME_PATTERN)
    @NotNull
    private String username;

    @Pattern(regexp = SecuritySettings.PASSWORD_PATTERN)
    @Size(min = SecuritySettings.PASSWORD_MIN, max = SecuritySettings.PASSWORD_MAX)
    @NotNull
    private String password1;
    private String password2;

    @Size(min = 1, max = 40)
    @NotNull
    private String firstname;
    @Size(min = 1, max = 40)
    @NotNull
    private String surname;

    public String register() {
        FacesContext context = FacesContext.getCurrentInstance();
        UsersEntity user = new UsersEntity();
        user.setUsername(username);
        String hashPwd = PasswordHasher.hashPassword(username, password1);
        user.setPassword(hashPwd);
        user.setFirstname(firstname);
        user.setSurname(surname);
        BrokerDBAuthUtil.FACTORY.saveUser(user);
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Rejestracja zakończona powodzeniem!", null));
        return null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
