package com.fiap.school.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.fiap.school.dao.jpa.GenericDao;
import com.fiap.school.entity.school.Exam;

@FacesConverter("com.eheinen.school.ExamConverter")
public class ExamConverter implements Converter {

    private GenericDao<Exam> examDao = new GenericDao<Exam>(Exam.class);

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof Exam)) {
            return null;
        }

        return String.valueOf(((Exam) value).getId());
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || !value.matches("\\d+")) {
            return null;
        }

        Exam exam = examDao.search(Integer.valueOf(value));

        if (exam == null) {
            throw new ConverterException(new FacesMessage("Unknown operation ID: " + value));
        }

        return exam;
    }

}