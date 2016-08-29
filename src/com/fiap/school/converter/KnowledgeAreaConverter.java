package com.fiap.school.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.fiap.school.dao.jpa.GenericDao;
import com.fiap.school.entity.school.KnowledgeArea;

@FacesConverter("com.eheinen.school.KnowledgeAreaConverter")
public class KnowledgeAreaConverter implements Converter {

    private GenericDao<KnowledgeArea> knowledgeAreaDao = new GenericDao<KnowledgeArea>(KnowledgeArea.class);

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof KnowledgeArea)) {
            return null;
        }

        return String.valueOf(((KnowledgeArea) value).getId());
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || !value.matches("\\d+")) {
            return null;
        }

        KnowledgeArea knowledgeArea = knowledgeAreaDao.search(Integer.valueOf(value));

        if (knowledgeArea == null) {
            throw new ConverterException(new FacesMessage("Unknown operation ID: " + value));
        }

        return knowledgeArea;
    }

}