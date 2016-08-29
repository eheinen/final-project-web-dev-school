package com.fiap.school.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.fiap.school.dao.jpa.GenericDao;
import com.fiap.school.entity.school.Course;

@FacesConverter("com.eheinen.school.CourseConverter")
public class CourseConverter implements Converter {

    private GenericDao<Course> courseDao = new GenericDao<Course>(Course.class);

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof Course)) {
            return null;
        }

        return String.valueOf(((Course) value).getId());
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || !value.matches("\\d+")) {
            return null;
        }

        Course course = courseDao.search(Integer.valueOf(value));

        if (course == null) {
            throw new ConverterException(new FacesMessage("Unknown operation ID: " + value));
        }

        return course;
    }

}