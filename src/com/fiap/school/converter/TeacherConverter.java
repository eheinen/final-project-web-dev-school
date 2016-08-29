package com.fiap.school.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.fiap.school.dao.jpa.GenericDao;
import com.fiap.school.entity.person.Teacher;

@FacesConverter("com.eheinen.school.TeacherConverter")
public class TeacherConverter implements Converter {

    private GenericDao<Teacher> teacherDao = new GenericDao<Teacher>(Teacher.class);

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof Teacher)) {
            return null;
        }

        return String.valueOf(((Teacher) value).getId());
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || !value.matches("\\d+")) {
            return null;
        }

        Teacher teacher = teacherDao.search(Integer.valueOf(value));

        if (teacher == null) {
            throw new ConverterException(new FacesMessage("Unknown operation ID: " + value));
        }

        return teacher;
    }

}