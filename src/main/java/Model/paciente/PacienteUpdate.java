package Model.paciente;

import Model.general.LoginMB;
import Util.webservice.WSConsumer;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import Model.paciente.PacienteAdd;


@Named
@SessionScoped
public class PacienteUpdate implements Serializable {



    private LoginMB loginMB;
    private String nombre;
    private  String correo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getGen() {
        return genero;
    }

    public void setGen(String genero) {
        this.genero = genero;
    }

    public String getEdo() {
        return edo;
    }

    public void setEdo(String edo) {
        this.edo = edo;
    }

    public String getGme() {
        return gme;
    }

    public void setGme(String gme) {
        this.gme = gme;
    }

    public String getLateralidad() {
        return lateralidad;
    }

    public void setLateralidad(String lateralidad) {
        this.lateralidad = lateralidad;
    }

    public String getCorreoEsp() {
        return correoEsp;
    }

    public void setCorreoEsp(String correoEsp) {
        this.correoEsp = correoEsp;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    private String contrasena;
    private  String genero;
    private String edo;
    private String gme;
    private String lateralidad;
    private String correoEsp;
    private String fechaNac;
    private String observaciones;

    @PostConstruct
    public void init(){

    }


    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    Map<String, Object> sessionMap = externalContext.getSessionMap();

    public void update() throws Exception {


        PacienteAdd paciente = new PacienteAdd();

        nombre = paciente.getNombre();


        try{
            String respuesta = WSConsumer.get("http://localhost:8080/rest/paciente/update?"+nombre);


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

}
