package fr.humanbooster.cap_entreprise.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.humanbooster.cap_entreprise.business.Genre;
import fr.humanbooster.cap_entreprise.dao.GenreDao;
import fr.humanbooster.cap_entreprise.service.GenreService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {

	private final GenreDao genreDao;

	@Override
	public List<Genre> recupererGenres() {
		return genreDao.findAll();
	}

}
