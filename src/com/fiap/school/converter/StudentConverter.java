package com.fiap.school.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.fiap.school.dao.jpa.GenericDao;
import com.fiap.school.entity.person.Student;

@FacesConverter("com.eheinen.school.StudentConverter")
public class StudentConverter implements Converter {

    private GenericDao<Student> studentDao = new GenericDao<Student>(Student.class);

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof Student)) {
            return null;
        }

        return String.valueOf(((Student) value).getId());
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || !value.matches("\\d+")) {
            return null;
        }

        Student student = studentDao.search(Integer.valueOf(value));

        if (student == null) {
            throw new ConverterException(new FacesMessage("Unknown operation ID: " + value));
        }

        return student;
    }

}