package Model.paciente;

import Util.converters.JsonToMap;
import Util.webservice.WSConsumer;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import org.bson.types.ObjectId;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;









//PARA DESCARGAR AUDIO
//import com.mongodb.Block;
import com.mongodb.MongoClient;
//import com.mongodb.client.MongoDatabase;
//import com.mongodb.client.gridfs.*;
//import com.mongodb.client.gridfs.model.*;
//import org.bson.Document;
import org.bson.types.ObjectId;
import java.io.*;
//import java.nio.file.Files;
//import java.nio.charset.StandardCharsets;
//import static com.mongodb.client.model.Filters.eq;
import com.mongodb.*;
/*import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSDownloadStream;*/










@Named
@SessionScoped

public class PacienteIndexMB implements Serializable {





















    public static final String INDEX_PAGE = "/content/paciente/index.xhtml";
     private static final String CONSULTAR = "/content/paciente/pacienteIntentos.xhtml";
    private static final String CONSULTARP = "/content/paciente/Palabras.xhtml";
    private static final String EVALUAR = "/content/paciente/evaluar.xhtml";

    private List<JSONObject> pacientes;
    private List<JSONObject> intentos;
    private List<JSONObject> pacientesFilter;
    private HashMap pacienteSelected;
    private HashMap intentoSelected;


    private String nombre;
    private  String correo;

    public List<JSONObject> getIntentos() {
        return intentos;
    }

    public void setIntentos(List<JSONObject> intentos) {
        this.intentos = intentos;
    }

    private String contrasena;
    private  String genero;
    private String edo;
    private String gradoMaximoEstudios;
    private String lateralidad;
    private String correoEsp;
    private String fechaNacimiento;
    private String observaciones="sin observaciones";
    private Boolean raven;
    private Boolean palabras;
    private String id;
    private String palabra1;
    private String palabra2;
    private String palabra3;
    private String palabra4;
    private String palabra5;
    private String palabra6;
    private String palabra7;
    private String palabra8;
    private String palabra9;
    private String palabra10;
    private String aciertos;

    public HashMap getIntentoSelected() {
        return intentoSelected;
    }

    public void setIntentoSelected(HashMap intentoSelected) {
        this.intentoSelected = intentoSelected;
    }

    public String getPalabra1() {
        return palabra1;
    }

    public void setPalabra1(String palabra1) {
        this.palabra1 = palabra1;
    }

    public String getPalabra2() {
        return palabra2;
    }

    public void setPalabra2(String palabra2) {
        this.palabra2 = palabra2;
    }

    public String getPalabra3() {
        return palabra3;
    }

    public void setPalabra3(String palabra3) {
        this.palabra3 = palabra3;
    }

    public String getPalabra4() {
        return palabra4;
    }

    public void setPalabra4(String palabra4) {
        this.palabra4 = palabra4;
    }

    public String getPalabra5() {
        return palabra5;
    }

    public void setPalabra5(String palabra5) {
        this.palabra5 = palabra5;
    }

    public String getPalabra6() {
        return palabra6;
    }

    public void setPalabra6(String palabra6) {
        this.palabra6 = palabra6;
    }

    public String getPalabra7() {
        return palabra7;
    }

    public void setPalabra7(String palabra7) {
        this.palabra7 = palabra7;
    }

    public String getPalabra8() {
        return palabra8;
    }

    public void setPalabra8(String palabra8) {
        this.palabra8 = palabra8;
    }

    public String getPalabra9() {
        return palabra9;
    }

    public void setPalabra9(String palabra9) {
        this.palabra9 = palabra9;
    }

    public String getPalabra10() {
        return palabra10;
    }

    public void setPalabra10(String palabra10) {
        this.palabra10 = palabra10;
    }

    public String getAciertos() {
        return aciertos;
    }

    public void setAciertos(String aciertos) {
        this.aciertos = aciertos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<JSONObject> getPacientesFilter() {
        return pacientesFilter;
    }


    public void setPacientesFilter(List<JSONObject> pacientesFilter) {
        this.pacientesFilter = pacientesFilter;
    }

    private String especialista;

    @PostConstruct
    public void init(){
        pacientes = new ArrayList<JSONObject>();
        intentos = new ArrayList<JSONObject>();
        pacienteSelected = new HashMap();
        intentoSelected = new HashMap();
    }

    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();

    public void setEspecialista(String especialista) {
        this.especialista = especialista;
    }

    public String prepareIndex(){   String redirect = INDEX_PAGE;
        pacientes.clear();

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession sessionMap = (HttpSession) facesContext.getExternalContext().getSession(true);

        especialista = sessionMap.getAttribute("especialista").toString();
        System.out.print(especialista);

        try {

            especialista = especialista.replace(" ", "%20");

            //Consulta Web service
            String response =  WSConsumer.get("http://localhost:8080/rest/paciente/"+especialista+"/");
            //Crea un objeto a partir de la respuesta
            JSONArray jsonArray = new JSONArray(response);

            for (int i=0; i< jsonArray.length(); i++) {
                pacientes.add( jsonArray.getJSONObject(i) );

            }
        } catch (Exception e) {
            System.out.println("error catch paciente");
        }
        System.out.println(redirect);
        return redirect;
    }

    public String consultar(JSONObject selected){
        String redirect = CONSULTAR ;
        intentos.clear();
        System.out.println(pacienteSelected.get("correo"));
        System.out.println(pacienteSelected.get("id_paciente"));
        setPacienteSelected(JsonToMap.toMap(selected));
        try {
            String response = WSConsumer.get("http://localhost:8080/rest/intentos/fbp/"+pacienteSelected.get("correo")+"/raven");


            JSONArray jsonArray = new JSONArray(response);

            for (int i=0; i< jsonArray.length(); i++) {
                intentos.add( jsonArray.getJSONObject(i) );
            }

        }
        catch (Exception e){
            System.out.println("Error en el try-catch de consultar para ver intentos");
        }
        return  redirect;

    }

    public String consultarPalabras (JSONObject selected){
        String redirect = CONSULTARP ;
        intentos.clear();
        System.out.println(pacienteSelected.get("correo"));
        setPacienteSelected(JsonToMap.toMap(selected));
        try {
            String response = WSConsumer.get("http://localhost:8080/rest/intentos/fbp/"+pacienteSelected.get("correo")+"/palabras");


            JSONArray jsonArray = new JSONArray(response);

            for (int i=0; i< jsonArray.length(); i++) {
                intentos.add( jsonArray.getJSONObject(i) );
            }

        }
        catch (Exception e){
            System.out.println("Error en el try-catch de consultar para ver intentos");
        }
        return  redirect;

    }

    public void prepareUpdate(JSONObject selected){
        System.out.println(selected);
        setPacienteSelected(JsonToMap.toMap(selected));


    }


    public String update(){
        JSONObject jsonObject = new JSONObject(pacienteSelected);
        try {



            String respuesta = WSConsumer.get("http://localhost:8080/rest/paciente/update?nombre="+
                    pacienteSelected.get("nombre").toString().replace(" ", "%20")+
                    "&correo="+ pacienteSelected.get("correo").toString().replace(" ", "%20")+"&correoEsp="+
                    pacienteSelected.get("id_especialista").toString().replace(" ", "%20")
                    +"&edo="+pacienteSelected.get("estado").toString().replace(" ", "%20")+
                    "&genero="+pacienteSelected.get("genero").toString().replace(" ", "%20")+
                    "&gradoMaximoEstudios="
                    +pacienteSelected.get("gradoMaximoEstudios").toString().replace(" ", "%20")
                    +"&lateralidad="
                    +pacienteSelected.get("lateralidad").toString().replace(" ", "%20")+
                    "&fechaNacimiento="+pacienteSelected.get("fechaNacimiento").toString().replace(" ", "%20")+"&observaciones="
                    +pacienteSelected.get("observaciones").toString().replace(" ", "%20"));
            System.out.println(pacienteSelected);
            System.out.println(respuesta);
            //Guaedar
            return  prepareIndex();
        }
        catch (Exception e){

            System.out.println("error");
            String error="error";

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Algún dato no tiene el formato indicado"));
            return error;


        }

    }



    //Asignar a este paciente prueba de Raven

    public String asigRaven(boolean raven, boolean palabras){

        try {
            if (raven){
                String respuesta = WSConsumer.get("http://localhost:8080/rest//intento/newI?corPac="+pacienteSelected.get("correo")+"&nomPru=raven");

                System.out.println(respuesta);

                if(respuesta.equals("Este paciente tiene ya esta prueba asignada")){
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage("Este paciente ya tiene asignada la prueba de raven"));
                }
            }

            if (palabras){
                String respuesta = WSConsumer.get("http://localhost:8080/rest//intento/newI?corPac="+pacienteSelected.get("correo")+"&nomPru=palabras");

                if(respuesta.equals("Este paciente tiene ya esta prueba asignada")){
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage("Este paciente ya tiene asignada la prueba de 10 palabras"));
                }
                System.out.println(respuesta + "esta es la respuesta");
            }
            JSONObject jsonObject = new JSONObject(pacienteSelected);
            return consultar(jsonObject);
        }

        catch (Exception e){
            System.out.println("Error en asignar pruebas");

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Error al asignar las pruebas\n" +
                            "Puede ser que el paciente ya tenga asignada esta prueba\n" +
                            "de no ser así, intentelo de nuevo"));
            return "error";
        }

    }

    //Asignar a este paciente prueba de Raven

    public String asigPalabras(boolean raven, boolean palabras){

        try {
            if (raven){
                String respuesta = WSConsumer.get("http://localhost:8080/rest//intento/newI?corPac="+pacienteSelected.get("correo")+"&nomPru=raven");

                System.out.println(respuesta);

                if(respuesta.equals("Este paciente tiene ya esta prueba asignada")){
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage("Este paciente ya tiene asignada la prueba de raven"));
                }
            }

            if (palabras){
                String respuesta = WSConsumer.get("http://localhost:8080/rest//intento/newI?corPac="+pacienteSelected.get("correo")+"&nomPru=palabras");

                if(respuesta.equals("Este paciente tiene ya esta prueba asignada")){
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage("Este paciente ya tiene asignada la prueba de 10 palabras"));
                }
                System.out.println(respuesta + "esta es la respuesta");
            }
            JSONObject jsonObject = new JSONObject(pacienteSelected);
            return consultarPalabras(jsonObject);
        }

        catch (Exception e){
            System.out.println("Error en asignar pruebas");

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Error al asignar las pruebas\n" +
                            "Puede ser que el paciente ya tenga asignada esta prueba\n" +
                            "de no ser así, intentelo de nuevo"));
            return "error";
        }

    }


    public void palabras(Integer id){
        if (id==1){
            setPalabra1("luz");
            setPalabra2("camino");
            setPalabra3("vida");
            setPalabra4("público");
            setPalabra5("llave");
            setPalabra6("café");
            setPalabra7("libro");
            setPalabra8("tiempo");
            setPalabra9("pez");
            setPalabra10("boca");
            return;
        }
        if (id==2){
            setPalabra1("azúcar");
            setPalabra2("cama");
            setPalabra3("foto");
            setPalabra4("película");
            setPalabra5("naranja");
            setPalabra6("hoja");
            setPalabra7("peluche");
            setPalabra8("dormitorio");
            setPalabra9("casa");
            setPalabra10("mano");
            return;
        }

        if (id==3){
            setPalabra1("dato");
            setPalabra2("mesa");
            setPalabra3("maíz");
            setPalabra4("luna");
            setPalabra5("azul");
            setPalabra6("calle");
            setPalabra7("saco");
            setPalabra8("reloj");
            setPalabra9("nariz");
            setPalabra10("libro");
            return;
        }

        if (id==4){
            setPalabra1("vela");
            setPalabra2("pueblo");
            setPalabra3("hombre");
            setPalabra4("humo");
            setPalabra5("bola");
            setPalabra6("nombre");
            setPalabra7("mes");
            setPalabra8("trabajo");
            setPalabra9("talón");
            setPalabra10("peso");
            return;
        }

        if(id==5){
            setPalabra1("correo");
            setPalabra2("barco");
            setPalabra3("verano");
            setPalabra4("granja");
            setPalabra5("casa");
            setPalabra6("teléfono");
            setPalabra7("escuela");
            setPalabra8("fuego");
            setPalabra9("pluma");
            setPalabra10("buzón");
            return;}


        if(id==6){
            setPalabra1("luz");
            setPalabra2("amigo");
            setPalabra3("hora");
            setPalabra4("juicio");
            setPalabra5("fé");
            setPalabra6("cofre");
            setPalabra7("lana");
            setPalabra8("número");
            setPalabra9("sol");
            setPalabra10("adentro");
            return;}

        if(id==7){
            setPalabra1("café");
            setPalabra2("signo");
            setPalabra3("cerrar");
            setPalabra4("deseo");
            setPalabra5("ojos");
            setPalabra6("reloj");
            setPalabra7("día");
            setPalabra8("persona");
            setPalabra9("espacio");
            setPalabra10("cuervo");
            return;}

        if(id==8){
            setPalabra1("hombro");
            setPalabra2("semana");
            setPalabra3("imagen");
            setPalabra4("memoria");
            setPalabra5("palabra");
            setPalabra6("dibujo");
            setPalabra7("signo");
            setPalabra8("figura");
            setPalabra9("historia");
            setPalabra10("salud");
            return;}

    }

    public String evaluar(JSONObject selected){

        setIntentoSelected(JsonToMap.toMap(selected));
        System.out.print(intentoSelected);
        if(Integer.parseInt(intentoSelected.get("tiempo").toString())==0){
            palabras(8);
        }
        else{
            palabras(Integer.parseInt(intentoSelected.get("tiempo").toString()));
        }
        String redirect = EVALUAR;


        return redirect;
    }


    public String calificar(){
        System.out.print(aciertos);
        try {
            String respuesta = WSConsumer.get("http://localhost:8080/rest/intento/upP?id="+intentoSelected.get("id_intento")+"&aciertos="+
                    aciertos);
            System.out.print(aciertos);
            setAciertos("");
            System.out.print(aciertos);
            System.out.print(respuesta);
            if (respuesta.equals("numero de aciertos no valido")){
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Numero de aciertos no valido, ingrese numeros solo del 0-10"));

            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("No ingreso aciertos, intentelo de nuevo"));
            e.printStackTrace();
        }
        return INDEX_PAGE;
    }

    public String descargar(){
        //PARA DESCARGAR AUDIO

        ObjectId fileId = new ObjectId(pacienteSelected.get("_id").toString());

        try{
            Mongo mongo = new MongoClient("localhost", 27017);
            DB db = mongo.getDB("sgplat");

            DBCollection collection = db.getCollection("intento");

            /*GridFSDownloadStream downloadStream;
            downloadStream = gridFSBucket.openDownloadStream(fileId);
            int fileLength = (int) downloadStream.getGridFSFile().getLength();
            byte[] bytesToWriteTo = new byte[fileLength];
            downloadStream.read(bytesToWriteTo);
            downloadStream.close();

            System.out.println(new String(bytesToWriteTo, StandardCharsets.UTF_8));*/
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return INDEX_PAGE;
    }

    public List<JSONObject> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<JSONObject> pacientes) {
        this.pacientes = pacientes;
    }

    public HashMap getPacienteSelected() {
        return pacienteSelected;
    }

    public void setPacienteSelected(HashMap pacienteSelected) {
        this.pacienteSelected = pacienteSelected;
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

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Boolean getRaven() {
        return raven;
    }

    public void setRaven(Boolean raven) {
        this.raven = raven;
    }

    public Boolean getPalabras() {
        return palabras;
    }

    public void setPalabras(Boolean palabras) {
        this.palabras = palabras;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEspecialista() {
        return especialista;
    }
}
