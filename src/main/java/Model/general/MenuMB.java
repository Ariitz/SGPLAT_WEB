package Model.general;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class MenuMB  implements Serializable {
    public static final String SUBFIX ="/content";
    public static final String REDIRECT_3_1 = "/tercerTabla/pingLogger.xhtml";
    public static final String REDIRECT_3_2 = "/tercerTabla/traps.xhtml";
    public static final String REDIRECT_3_3 = "/tercerTabla/syslog.xhtml";
    public static final String REDIRECT_3_4 = "/tercerTabla/check.xhtml";

    public String redirectGeneric(String question){
        System.out.println("Redirect: " +SUBFIX+question );
        return SUBFIX+question;
    }

    public String redirectPingLogger(){
        return redirectGeneric(REDIRECT_3_1);
    }
    public String redirectTraps(){
        return redirectGeneric(REDIRECT_3_2);
    }
    public String redirectSyslog(){
        return redirectGeneric(REDIRECT_3_3);
    }
    public String redirectCheck(){
        return redirectGeneric(REDIRECT_3_4);
    }
}
