package org.esupportail.example.web.converters;

import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static org.springframework.util.StringUtils.hasText;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

import org.esupportail.example.domain.beans.User;
import org.esupportail.example.domain.services.UserService;

@Named
public class UserConverter implements Converter {

	@Inject
	private UserService userService;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) throws ConverterException {
        if (!hasText(value)) {
            return null;
        }
		User u = userService.getUser(value);
		if (u == null) {
			throw new ConverterException(new FacesMessage(SEVERITY_ERROR, "Erreur de conversion getAsObject", "L'objet [" + value + "] n'existe pas!"));
		}
		return u;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) throws ConverterException {
        if (value == null) {
            return "";
        } else if (value instanceof String) {
            if (!hasText((String) value)) {
                return "";
            }
        }
		if (!(value instanceof User)) {
			throw new ConverterException(new FacesMessage(SEVERITY_ERROR, "Erreur de conversion getAsString", "L'objet [" + value + "] n'est pas un User"));
		}
		User u = (User) value;
		return u.getId();
	}
	
	
	
}
