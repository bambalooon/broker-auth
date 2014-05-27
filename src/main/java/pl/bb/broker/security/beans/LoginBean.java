package pl.bb.broker.security.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
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

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        redirect = context.getExternalContext().getRequestParameterMap().get("redirect");
        if(redirect==null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Redirect error!", null));
            return null;
        }
        try {
            Principal principal = request.getUserPrincipal();
            if(principal!=null) {
//                request.logout();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Not logged out: "+principal.getName(), null));
                return null;
            }
            request.login(username, password);
            return redirect+"?faces-redirect-true";
        } catch (ServletException e) { //don't catch? - redirects to error?
            String msg = e.getMessage();
            if(msg==null) msg = "";
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unknown login: "+msg, null));
            return null;
        }
    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.logout();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Logout succesful!", null));
            return null;
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Logout error", null));
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
