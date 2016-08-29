package com.fiap.school.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.fiap.school.dao.jpa.GenericDao;
import com.fiap.school.entity.school.School;

@FacesConverter("com.eheinen.school.SchoolConverter")
public class SchoolConverter implements Converter {

    private GenericDao<School> schoolDao = new GenericDao<School>(School.class);

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof School)) {
            return null;
        }

        return String.valueOf(((School) value).getId());
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || !value.matches("\\d+")) {
            return null;
        }

        School school = schoolDao.search(Integer.valueOf(value));

        if (school == null) {
            throw new ConverterException(new FacesMessage("Unknown operation ID: " + value));
        }

        return school;
    }

}