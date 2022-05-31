package fr.humanbooster.cap_entreprise.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.humanbooster.cap_entreprise.business.Editeur;
import fr.humanbooster.cap_entreprise.dao.EditeurDao;
import fr.humanbooster.cap_entreprise.service.EditeurService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EditeurServiceImpl implements EditeurService {

	private final EditeurDao editeurDao;

	@Override
	public List<Editeur> recupererEditeurs() {
		return editeurDao.findAll();
	}

}