package com.fiap.school.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.fiap.school.dao.jpa.GenericDao;
import com.fiap.school.entity.school.KnowledgeArea;
import com.fiap.school.helper.Actions;
import com.fiap.school.helper.Contexts;
import com.fiap.school.helper.Messages;
import com.fiap.school.helper.Navigation;

@ManagedBean(name = "knowAreaMB")
@ViewScoped
public class KnowledgeAreaMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private KnowledgeArea knowledgeArea;
	private Messages messages;
	private Actions actions;

	public Actions getActions() {
		return actions;
	}

	public Messages getMessages() {
		return messages;
	}

	public KnowledgeArea getKnowledgeArea() {
		return knowledgeArea;
	}

	public void setKnowledgeArea(KnowledgeArea knowledgeArea) {
		this.knowledgeArea = knowledgeArea;
	}

	public KnowledgeAreaMB() {
		knowledgeArea = new KnowledgeArea();
		messages = new Messages();
		actions = new Actions();
		actions.setAddAction();
	}

	public String createKnowledgeArea() throws IOException {
		try {
			if (knowledgeArea == null || knowledgeArea.getName() == null || knowledgeArea.getName().isEmpty()) {
				messages.setRequiredFieldMessage();
			} else {
				GenericDao<KnowledgeArea> dao = new GenericDao<KnowledgeArea>(KnowledgeArea.class);
				if (knowledgeArea.getId() != 0) {
					dao.update(knowledgeArea);
					messages.setUpdateMessage("Knowledge Area");
				} else {
					dao.insert(knowledgeArea);
					messages.setInsertMessage("Knowledge Area");
				}
			}
		} catch (Exception er) {
			messages.setSomethingWentWrongMessage();
		}
		actions.setAddAction();
		knowledgeArea = new KnowledgeArea();
		return null;
	}

	public List<KnowledgeArea> getKnowledgeAreaList() {
		try {
			messages.resetMessage();
			return new GenericDao<KnowledgeArea>(KnowledgeArea.class).list();
		} catch (Exception er) {
			return null;
		}
	}

	public void removeKnowledgeArea() throws IOException {
		try {
			int id = Contexts.getParameterId();

			if (knowledgeArea != null) {
				GenericDao<KnowledgeArea> dao = new GenericDao<KnowledgeArea>(KnowledgeArea.class);
				dao.delete(id);
				messages.setDeleteMessage("Knowledge Area");
			}
		} catch (Exception er) {
			Navigation.redirectToPages("Home", "home");
		}
		knowledgeArea = new KnowledgeArea();
	}

	public String prepareToEdit() {
		try {
			int id = Contexts.getParameterId();

			if (knowledgeArea != null || id != 0) {
				GenericDao<KnowledgeArea> dao = new GenericDao<KnowledgeArea>(KnowledgeArea.class);
				knowledgeArea = dao.search(id);
			}
			messages.resetMessage();
			actions.setEditAction();
			return null;
		} catch (Exception er) {
			messages.setSomethingWentWrongMessage();
			return null;
		}
	}

}
