package es.expensecalculator.web.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("passwordValidator")
public class PasswordValidator implements Validator {

	private final String PATRON="(?=^.{8,15}$)(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&amp;*()_+}{&quot;:;'?/&gt;.&lt;,])(?!.*\\s).*$";
	
	
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        boolean invalid=true;
        	String password = (String)value;        	
        if (!password.matches(PATRON)) {
        	throw new ValidatorException(new FacesMessage("No cumple con el patrón de password!"));
        }
    }

}
