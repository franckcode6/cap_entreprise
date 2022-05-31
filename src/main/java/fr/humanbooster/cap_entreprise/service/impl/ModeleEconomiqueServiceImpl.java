package fr.humanbooster.cap_entreprise.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.humanbooster.cap_entreprise.business.ModeleEconomique;
import fr.humanbooster.cap_entreprise.dao.ModeleEconomiqueDao;
import fr.humanbooster.cap_entreprise.service.ModeleEconomiqueService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ModeleEconomiqueServiceImpl implements ModeleEconomiqueService {

	private final ModeleEconomiqueDao modeleEconomiqueDao;

	@Override
	public List<ModeleEconomique> recupererModeleEconomiques() {
		return modeleEconomiqueDao.findAll();
	}

}
