package Model.especialista;

import Util.webservice.WSConsumer;
import org.primefaces.PrimeFaces;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class EspecialistaAdd implements Serializable {


    private String nombre;
    private  String correo;
    private String contrasena;

    private JSONObject especialista;
    @PostConstruct
    public void init(){

        especialista = new JSONObject();

    }

    public void add (List list){
        try{
            nombre =nombre.replace(" ","%20");
            correo = correo.replace(" ","%20");
            contrasena = contrasena.replace(" ","%20");
            String respuesta = WSConsumer.get("http://localhost:8080/rest/especialista/"+nombre+"/"+correo+"/"+contrasena);

            JSONObject jsonObject = new JSONObject(respuesta);
            PrimeFaces.current().executeScript("PF('dlgAdd').hide()");
            list.add(jsonObject);
            setContrasena("");
            setCorreo("");
            setNombre("");
        }
            catch (Exception e){
            System.out.print("error esp add");
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Algún dato falta o está mal"));
            e.printStackTrace();

            }
    }

    public JSONObject getEspecialista() {
        return especialista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setEspecialista(JSONObject especialista) {
        this.especialista = especialista;
    }


}
