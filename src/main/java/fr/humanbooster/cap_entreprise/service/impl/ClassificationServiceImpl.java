package fr.humanbooster.cap_entreprise.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.humanbooster.cap_entreprise.business.Classification;
import fr.humanbooster.cap_entreprise.dao.ClassificationDao;
import fr.humanbooster.cap_entreprise.service.ClassificationService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClassificationServiceImpl implements ClassificationService {
	
	private final ClassificationDao classificationDao;
	
	@Override
	public List<Classification> recupererClassifications() {
		return classificationDao.findAll();
	}

}
