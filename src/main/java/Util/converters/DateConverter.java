package Util.converters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



@FacesConverter(value = "dateConverter")
public class DateConverter implements Converter {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateConverter.class);

    private static final String DD_MMMM_YYYY = "dd/MMMM/yyyy hh:mm:ss";
    private SimpleDateFormat yyyyConvertor;

    public DateConverter() {
        yyyyConvertor = new SimpleDateFormat(DD_MMMM_YYYY);
        yyyyConvertor.setLenient(false);
    }


    @Override
    public String getAsString(FacesContext context, UIComponent component, Object entity) {
        return yyyyConvertor.format(entity);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String uuid) {
        Date result = null;
        try {
            if (uuid.equals("today")) {
                result = new Date();
            } else {
                result = (Date) yyyyConvertor.parseObject(uuid);
            }
        } catch (ParseException e) {
            LOGGER.error(e.getMessage());
            result = new Date();
        }
        return result.getTime();
    }



}



