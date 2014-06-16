package pl.bb.broker.security.beans;

import pl.bb.broker.security.settings.SecurityGroups;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 24.05.14
 * Time: 15:10
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean
@RequestScoped
public class LoginBean {
    private String username = "";
    private String password = "";
    private String redirect = "";

    public LoginBean() {}

    @PostConstruct
    public void init() throws Exception {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        Principal principal = request.getUserPrincipal();
        if(principal!=null) {
            if(request.isUserInRole(SecurityGroups.COMPANY.toString())) {
                context.getExternalContext().redirect(SecurityGroups.COMPANY_REDIRECT);
            }
            else if(request.isUserInRole(SecurityGroups.CLIENT.toString())) {
                context.getExternalContext().redirect(SecurityGroups.CLIENT_REDIRECT);
            }
        }
    }

    public String login() throws Exception {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        redirect = context.getExternalContext().getRequestParameterMap().get("redirect");
        if(redirect==null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Błąd przekierowania!", null));
            return null;
        }
        try {
            Principal principal = request.getUserPrincipal();
            if(principal!=null) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Jesteś zalogowany/a: "+principal.getName(), null));
                return null;
            }
            request.login(username, password);
            principal = request.getUserPrincipal();
            if(request.isUserInRole(SecurityGroups.COMPANY.toString())) {
                return SecurityGroups.COMPANY_REDIRECT+"?faces-redirect-true";
            }
            else if(request.isUserInRole(SecurityGroups.CLIENT.toString())) {
                return SecurityGroups.CLIENT_REDIRECT+"?faces-redirect-true";
            }
            return redirect+"?faces-redirect-true";
        } catch (ServletException e) { //don't catch? - redirects to error?
            String msg = e.getMessage();
            if(msg==null) msg = "";
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne dane", null));
            return null;
        }
    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.logout();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Wylogowany!", null));
            return "../auth/login.xhtml?faces-redirect-true";
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd wylogowania", null));
            return null;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
