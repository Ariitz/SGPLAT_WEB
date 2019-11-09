package Model.especialista;

import Util.webservice.WSConsumer;
import org.primefaces.PrimeFaces;
import org.primefaces.json.JSONObject;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

import Model.general.LoginMB;


@Named
@SessionScoped
public class EspecialistaUpdate implements Serializable {



    private LoginMB loginMB;
    private String correo;
    private String pwo;
    private String pwn;

    @PostConstruct
    public void init(){

    }

    public String update() throws Exception {

        try{
            correo = correo.replace(" ","%20");
            pwo = pwo.replace(" ","%20");
            pwn = pwn.replace(" ","%20");
            String respuesta = WSConsumer.get("http://localhost:8080/rest/Especialista/npw/"+correo+"/"+pwo+"/"+pwn);
            Boolean igual = respuesta.equals("contraseña cambiaba");
            if(igual){
                return "ok";
            }
            else {
                return null;
            }


        }
        catch (Exception e){
            System.out.print("error");
            e.printStackTrace();

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Revise sus datos"));
            return "error";
        }
    }



    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPwo() {
        return pwo;
    }

    public void setPwo(String pwo) {
        this.pwo = pwo;
    }

    public String getPwn() {
        return pwn;
    }

    public void setPwn(String pwn) {
        this.pwn = pwn;
    }
}
