package fr.humanbooster.cap_entreprise.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.humanbooster.cap_entreprise.business.Plateforme;
import fr.humanbooster.cap_entreprise.dao.PlateformeDao;
import fr.humanbooster.cap_entreprise.service.PlateformeService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PlateformeServiceImpl implements PlateformeService {

	private final PlateformeDao plateformeDao;

	@Override
	public List<Plateforme> recupererPlateformes() {
		return plateformeDao.findAll();
	}

}
