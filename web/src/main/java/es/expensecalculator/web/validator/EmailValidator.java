package es.expensecalculator.web.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("emailValidator")
public class EmailValidator implements Validator {
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\." +
            "[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*" +
            "(\\.[A-Za-z]{2,})$";
    private Pattern pattern;
    private Matcher matcher;

    public void validate(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {
        pattern = java.util.regex.Pattern.compile(EMAIL_PATTERN);
        if (value != null && value.toString().trim().length() > 0) {
            matcher = pattern.matcher(value.toString());
            if (!matcher.matches()) {

                FacesMessage msg =
                        new FacesMessage("Debe introducir un formato de email correcto",
                                "Debe introducir un formato de email correcto");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);

            }
        }

    }

}
