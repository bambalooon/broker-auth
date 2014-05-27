package pl.bb.broker.security.beans;


import pl.bb.broker.brokerdb.broker.entities.CompaniesEntity;
import pl.bb.broker.brokerdb.broker.entities.UsersEntity;
import pl.bb.broker.brokerdb.util.BrokerDBUtil;
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
 * Date: 26.05.14
 * Time: 23:34
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean
@RequestScoped
public class RegisterCompanyBean {
    @Size(min = SecuritySettings.USERNAME_MIN, max = SecuritySettings.USERNAME_MAX)
    @Pattern(regexp = SecuritySettings.USERNAME_PATTERN)
    @NotNull
    private String username;

    @Pattern(regexp = SecuritySettings.PASSWORD_PATTERN)
    @Size(min = SecuritySettings.PASSWORD_MIN, max = SecuritySettings.PASSWORD_MAX)
    @NotNull
    private String password1;
    private String password2;

    @NotNull
    private String companyName;

    @Size(max = 100)
    private String address;

    @Size(min = 9, max = 12)
    @Pattern(regexp = SecuritySettings.PHONE_PATTERN)
    private String phone;

    public String registerCompany() {
        FacesContext context = FacesContext.getCurrentInstance();
        UsersEntity user = new UsersEntity();
        user.setUsername(username);
        String hashPwd = PasswordHasher.hashPassword(username, password1);
        user.setPassword(hashPwd);
        CompaniesEntity company = new CompaniesEntity();
        company.setCompanyname(companyName);
        company.setAddress(address);
        company.setPhone(phone);
        company.setUser(user);
        BrokerDBUtil.INSTANCE.saveCompany(company);
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Succesful company register!", null));
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
