package es.expensecalculator.web.param;

import java.lang.reflect.Field;

/**
 * The Class ParamCommun.
 */
public class ParamCommun {

    private boolean buttonPressed = false;

    /**
     * Limpiar entity param.
     */
    public void limpiarEntityParam() {
        for (Field field : this.getClass().getDeclaredFields()) {
            try {
                field.set(this, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Checks if is filled entity param.
     * 
     * @return true, if is filled entity param
     */
    public boolean isFilledEntityParam() {
        for (Field field : this.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Object value = field.get(this);
                if (value instanceof String) {
                    String cadena = (String) value;
                    //if (cadena != null && cadena.trim().length() > 0) {
                    if (cadena.trim().length() > 0) {
                        return true;
                    }
                } else {
                    if (value != null) {
                        return true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean isButtonPressed() {
        return buttonPressed;
    }

    public void setButtonPressed(boolean buttonPressed) {
        this.buttonPressed = buttonPressed;
    }

}
