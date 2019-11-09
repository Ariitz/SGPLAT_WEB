package Model.paciente;

import Model.general.LoginMB;
import Util.webservice.WSConsumer;
import org.primefaces.PrimeFaces;
import org.primefaces.json.JSONObject;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;
import java.util.Map;


import Model.paciente.PacienteIndexMB;

@Named
@RequestScoped
public class PacienteAdd implements Serializable {


    private String nombre;
    private  String correo;
    private String contrasena;
    private String genero;
    private String edo;
    private String gradoMaximoEstudios;
    private String lateralidad;
    private String correoEsp;
    private String fechaNacimiento;
    private String observaciones;


    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEdo() {
        return edo;
    }

    public void setEdo(String edo) {
        this.edo = edo;
    }

    public String getGradoMaximoEstudios() {
        return gradoMaximoEstudios;
    }

    public void setGradoMaximoEstudios(String gradoMaximoEstudios) {
        this.gradoMaximoEstudios = gradoMaximoEstudios;
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

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    private JSONObject paciente;



    @PostConstruct
    public void init(){

        paciente = new JSONObject();

    }


    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    Map<String, Object> sessionMap = externalContext.getSessionMap();

    public void add (List list){
        System.out.print(gradoMaximoEstudios);
        try{

            nombre = nombre.replace(" ", "%20");
            correo = correo.replace(" ", "%20");
            contrasena = contrasena.replace(" ", "%20");
            genero = genero.replace(" ", "%20");
            gradoMaximoEstudios = gradoMaximoEstudios.replace(" ", "%20");
            lateralidad = lateralidad.replace(" ", "%20");
            fechaNacimiento = fechaNacimiento.replace(" ", "%20");
            observaciones = observaciones.replace(" ", "%20");
            System.out.print(gradoMaximoEstudios);

            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession sessionMap = (HttpSession) facesContext.getExternalContext().getSession(true);

            correoEsp = sessionMap.getAttribute("especialista").toString();
            System.out.print(correoEsp);

            nombre =nombre.replace(" ","%20");



            String respuesta = WSConsumer.get("http://localhost:8080/rest/paciente/newP?nombre="+nombre+
                    "&correo="+ correo+
                    "&contrasena="+contrasena+"&correoEsp="+correoEsp+"&genero="
                    +genero+"&edo=activo&gradoMaximoEstudios="
                    +gradoMaximoEstudios+"&lateralidad="+lateralidad+
                    "&fechaNacimiento="+fechaNacimiento+"&observaciones="+observaciones);


            System.out.println(respuesta);
            System.out.print(gradoMaximoEstudios);

            JSONObject jsonObject = new JSONObject(respuesta);
            PrimeFaces.current().executeScript("PF('dlgAdd').hide()");
            list.add(jsonObject);

            setContrasena("");
            setCorreo("");
            setNombre("");
            setCorreoEsp("");
            setEdo("");
            setFechaNacimiento("");
            setGenero("");
            setGradoMaximoEstudios("");
            setLateralidad("");
            setObservaciones("");


        }
            catch (Exception e){
            System.out.print("error en agregar paciente");
            e.printStackTrace();
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Error al agregar paciente \nFalta algún dato o está mal. \nNota: no es el campo de observaciones"));
            }
    }

    public JSONObject getPaciente() {
        return paciente;
    }

    public String getNombre() {
        nombre = (String) sessionMap.get("nombrePac");
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

    public void setPaciente(JSONObject paciente) {
        this.paciente = paciente;
    }


}
