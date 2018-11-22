package org.wecancodeit.peaceofmind;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(NewAccountController.class)
public class NewAccountMockMvcTest {

	@Resource
	private MockMvc mvc;
	
	@MockBean
	private AddressRepository addressRepo;
	
	@MockBean
	private PhoneRepository phoneRepo;
	
	@MockBean
	private EmailRepository emailRepo;
	
	@MockBean
	private ContactInfoRepository contactInfoRepo;
	
	@MockBean
	private NonMedicalUserRepository nonMedRepo;
	
	@MockBean
	private PatientRepository patientRepo;
	
	@MockBean
	private MedicalUserRepository medUserRepo;
	
	
	
	
	@Test
	public void onAddNonMedShouldRouteToAddPatientSite() throws Exception {
		mvc.perform(post("/new-user/caregiver")
			.param("firstName", "name")
			.param("lastName", "name")
			.param("userName", "username")
			.param("password", "pass")
			.param("relationshipWithPatient", "relation")
			.param("streetAddress", "street")
			.param("secondaryField", "2")
			.param("city", "city")
			.param("state", "state")
			.param("zipCode", "zip")
			.param("aType", "home")
			.param("phoneNumber", "phone")
			.param("pType", "work")
			.param("emailAddress", "123")
			.param("eType", "play"))
				.andExpect(redirectedUrl("/new-user/add-patient"));	
		}
	
	@Test
	public void shouldBeOkAndRouteToAddPatientSite() throws Exception {
		
	}
	
	
	@Test
	public void onAddPatientShouldRouteToAddMedSite() throws Exception {
		mvc.perform(post("/new-user/patient"))
			.andExpect(redirectedUrl("/new-user/add-medical"));
	}
	
}
