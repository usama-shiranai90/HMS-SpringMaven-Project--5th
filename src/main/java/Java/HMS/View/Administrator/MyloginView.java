package Java.HMS.View.Administrator;

import Proxy.DatabaseByPass;
import Proxy.ProxyByPass;
import com.vaadin.annotations.Title;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.login.AbstractLogin;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.templatemodel.TemplateModel;

import java.util.Optional;

@Route("login")
@RouteAlias("")
@Title("Login Page")
@Tag("mylogin-view")
@JsModule("./src/JSFile/mylogin-view.js")
public class MyloginView extends PolymerTemplate<MyloginView.MyloginViewModel> {


    @Id("adminlogin")
    private LoginForm adminlogin;

    private DatabaseByPass byPass;  // creating proxy instance.

    public MyloginView() {
        byPass = new ProxyByPass();

        adminlogin.addLoginListener(this::getLogindata);

        adminlogin.addForgotPasswordListener(event ->
                Notification.show("Contact Help service").setPosition(Notification.Position.MIDDLE));
    }

    private void getLogindata(AbstractLogin.LoginEvent loginEvent) {
            try {

                boolean flag = byPass.authentication(loginEvent.getUsername(), loginEvent.getPassword());

                if (flag && getUI().isPresent()) {
                    getUI().get().navigate("dashboard");
                } else {
                    Notification.show(loginEvent.getUsername());
                    adminlogin.setError(true);
                    adminlogin.onEnabledStateChanged(true);

                    VaadinSession.getCurrent().setAttribute("userLoggedIn",true);
                    Object intendedPath = VaadinSession.getCurrent().getAttribute("intendedPath");
                    UI.getCurrent().navigate(Optional.ofNullable(intendedPath).map(Object::toString).orElse(""));

//                    getUI().get().navigate("");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

    }

    public void loginCredentialCheck(int userid ,final String pass) {


    }

    public LoginForm getAdminlogin() {
        return adminlogin;
    }

    public void setAdminlogin(LoginForm adminlogin) {
        this.adminlogin = adminlogin;
    }

    public interface MyloginViewModel extends TemplateModel {

    }

}
