package Model.paciente;

import Model.general.LoginMB;
import Util.webservice.WSConsumer;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;


@Named
@SessionScoped
public class PacientePW implements Serializable {



    private LoginMB loginMB;
    private String correo;
    private String pwo;
    private String pwn;

    @PostConstruct
    public void init(){

    }

    public void update() throws Exception {

        try{
            String respuesta = WSConsumer.get("http://localhost:8080/rest/Paciente/npw/"+correo+"/"+pwo+"/"+pwn);


        }
        catch (Exception e){
            System.out.print("error");
            e.printStackTrace();
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
