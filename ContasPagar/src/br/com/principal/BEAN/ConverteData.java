package br.com.principal.BEAN;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "converteData")
public class ConverteData implements Converter {

	private SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {

		try {
			return this.df.parse(arg2);
		} catch (Exception e) {
			return "";
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		try {
			this.df.setLenient(false); 
			return this.df.format((Date) arg2);
		} catch (Exception e) {
			return "";
		}
	}
	

}
