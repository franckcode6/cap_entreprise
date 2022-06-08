package fr.humanbooster.cap_entreprise.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import fr.humanbooster.cap_entreprise.business.Joueur;
import fr.humanbooster.cap_entreprise.service.JeuService;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AvisControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private JeuService jeuService;

	@Autowired
	private HttpSession httpSession;

	private static Joueur joueur;

	// @Test
	@Order(1)
	public void InitEtConnexion() {

		try {
			mockMvc.perform(MockMvcRequestBuilders.post("/inscription").accept(MediaType.TEXT_HTML)
					.param("pseudo", "Abbey").param("email", "a.g@hb.com").param("motDePasse", "glows")
					.param("dateDeNaissance", "1999-10-18")).andExpect(MockMvcResultMatchers.redirectedUrl("/"))
					.andExpect(status().isFound());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		try {
			ResultActions resultActions = mockMvc
					.perform(MockMvcRequestBuilders.post("/").accept(MediaType.TEXT_HTML).param("pseudo", "Abbey")
							.param("motDePasse", "glows"))
					.andExpect(MockMvcResultMatchers.redirectedUrl("/avis")).andExpect(status().isFound());
			MvcResult result = resultActions.andReturn();
			httpSession = result.getRequest().getSession();
			joueur = (Joueur) httpSession.getAttribute("joueur");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Test
	// ajouter pageable !!!!
	public void testerAvis() throws Exception {

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/avis");

		Pageable pageable = PageRequest.of(1, 5, Sort.by("dateEnvoi").descending());
		((ResultActions) ((MockHttpServletRequestBuilder) mockMvc
				.perform(MockMvcRequestBuilders.get("/avis").accept(MediaType.TEXT_HTML)))
				.param("pages", String.valueOf(pageable.getPageNumber()))).andExpect(status().isFound());

		mockMvc.perform(requestBuilder).andExpect(view().name("listeDesAvis")).andExpect(status().isOk());
	}

	// @Test
	public void testerAjoutAvis() throws Exception {

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/avis/ajout");

		mockMvc.perform(requestBuilder).andExpect(view().name("ajoutAvis")).andExpect(status().isOk());
	}

	// @Test
	@Order(2)
	public void testerAjoutAvisAvecSucces() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.post("/avis/ajout").accept(MediaType.TEXT_HTML)
				.sessionAttr("joueur", joueur).param("jeu", String.valueOf(jeuService.recupererJeux().get(0).getId()))
				.param("description", "test description").param("note", "15.0"))
				.andExpect(MockMvcResultMatchers.redirectedUrl("/avis")).andExpect(status().isFound());
	}

}
