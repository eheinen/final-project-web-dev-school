package com.fiap.school.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.fiap.school.dao.jpa.GenericDao;
import com.fiap.school.entity.school.Discipline;

@FacesConverter("com.eheinen.school.DisciplineConverter")
public class DisciplineConverter implements Converter {

    private GenericDao<Discipline> disciplineDao = new GenericDao<Discipline>(Discipline.class);

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof Discipline)) {
            return null;
        }

        return String.valueOf(((Discipline) value).getId());
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || !value.matches("\\d+")) {
            return null;
        }

        Discipline discipline = disciplineDao.search(Integer.valueOf(value));

        if (discipline == null) {
            throw new ConverterException(new FacesMessage("Unknown operation ID: " + value));
        }

        return discipline;
    }

}