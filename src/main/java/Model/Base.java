package Model;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class Base implements Serializable {


    @PostConstruct
    public void init(){

    }
}
