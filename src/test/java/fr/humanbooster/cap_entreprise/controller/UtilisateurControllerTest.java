package fr.humanbooster.cap_entreprise.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UtilisateurControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private UtilisateurController utilisateurController;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(utilisateurController).build();
	}

	@Test
	public void testerIndex() throws Exception {

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");

		mockMvc.perform(requestBuilder).andExpect(view().name("connexion")).andExpect(status().isOk());
	}

	@Test
	public void testerConnexionAvecSucces() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.post("/").accept(MediaType.TEXT_HTML).param("pseudo", "franck")
				.param("motDePasse", "12345")).andExpect(MockMvcResultMatchers.redirectedUrl("/admin"))
				.andExpect(status().isFound());
	}

	@Test
	public void testerInscriptionAvecSucces() throws Exception {

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/inscription");

		mockMvc.perform(requestBuilder).andExpect(view().name("inscriptionJoueur")).andExpect(status().isOk());
	}

	@Test
	public void testerInscriptionPostAvecSucces() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.post("/inscription").accept(MediaType.TEXT_HTML).param("pseudo", "Abbey")
				.param("email", "a.g@hb.com").param("motDePasse", "glows").param("dateDeNaissance", "1999-10-18"))
				.andExpect(MockMvcResultMatchers.redirectedUrl("/")).andExpect(status().isFound());
	}

	@Test
	public void testerDeconnexion() throws Exception {

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/deconnexion");

		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.redirectedUrl("/"))
				.andExpect(status().isFound());
	}

}