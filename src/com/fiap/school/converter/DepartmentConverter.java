package com.fiap.school.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.fiap.school.dao.jpa.GenericDao;
import com.fiap.school.entity.school.Department;

@FacesConverter("com.eheinen.school.DepartmentConverter")
public class DepartmentConverter implements Converter {

    private GenericDao<Department> departmentDao = new GenericDao<Department>(Department.class);

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof Department)) {
            return null;
        }

        return String.valueOf(((Department) value).getId());
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || !value.matches("\\d+")) {
            return null;
        }

        Department department = departmentDao.search(Integer.valueOf(value));

        if (department == null) {
            throw new ConverterException(new FacesMessage("Unknown operation ID: " + value));
        }

        return department;
    }

}