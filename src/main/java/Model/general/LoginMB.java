package Model.general;


import Util.webservice.WSConsumer;
import com.mongodb.DBObject;
import org.primefaces.json.JSONObject;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@Named
@SessionScoped


public class LoginMB implements Serializable {

    private static final String ON_LOGIN_ERROR = "";
    private static final String ON_LOGIN_OK = "/content/main.xhtml";
    private static final String LOGOUT = "/content/index.xhtml";

    private String user;
    private String password;

    private DBObject userData;
    private HttpSession sessionMap;


    @PostConstruct
    public void init() {
        System.out.println("Init");
        FacesContext facesContext = FacesContext.getCurrentInstance();
        sessionMap = (HttpSession) facesContext.getExternalContext().getSession(true);
    }




    public String login() {
        String redirect = ON_LOGIN_ERROR;





        try {
            user = user.replace(" ", "%20");
            password = password.replace(" ", "%20");
            //Consulta Web service
           String response =  WSConsumer.get("http://localhost:8080/rest/LogEspecialista/"+user+"/"+password+"");

           System.out.println("especialista");
           System.out.println(user);
           //Crea un objeto a partir de la respuesta
            JSONObject jsonObject = new JSONObject(response);
            //Obtenemos las propiedades  del objeto con la respuesta
            Boolean respuesta = jsonObject.getBoolean("response");
            //Logica

            if (respuesta) {
                redirect = ON_LOGIN_OK;
                sessionMap.setAttribute("especialista", user);
            }


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error del catch login");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Revise sus datos"));
        }

        System.out.println("especialista fuera del try-catch");
        System.out.println(sessionMap.getAttribute("especialista"));

        System.out.println(redirect);


        return redirect;

    }

    public String correoEspecialista(){

        String especialista = (String) sessionMap.getAttribute("especialista");
        return  especialista;
    }

    public String logOut(){
        System.out.println(sessionMap.getAttribute("especialista"));
        sessionMap.removeAttribute("especialista");
        System.out.println(sessionMap.getAttribute("especialista"));
        String redirect = LOGOUT;
        return  redirect;
    }



    public String getUser() {


        return user;
    }



    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
