package com.fiap.school.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.fiap.school.dao.jpa.GenericDao;
import com.fiap.school.entity.school.SchoolClass;

@FacesConverter("com.eheinen.school.SchoolClassConverter")
public class SchoolClassConverter implements Converter {

    private GenericDao<SchoolClass> schoolClassDao = new GenericDao<SchoolClass>(SchoolClass.class);

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof SchoolClass)) {
            return null;
        }

        return String.valueOf(((SchoolClass) value).getId());
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || !value.matches("\\d+")) {
            return null;
        }

        SchoolClass schoolClass = schoolClassDao.search(Integer.valueOf(value));

        if (schoolClass == null) {
            throw new ConverterException(new FacesMessage("Unknown operation ID: " + value));
        }

        return schoolClass;
    }

}