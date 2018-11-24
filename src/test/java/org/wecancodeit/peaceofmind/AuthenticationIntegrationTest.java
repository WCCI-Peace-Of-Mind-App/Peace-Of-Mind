package org.wecancodeit.peaceofmind;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import javax.servlet.ServletContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(classes = PeaceOfMindApplication.class)
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class AuthenticationIntegrationTest {
  @Autowired
  private WebApplicationContext webApplicationContext;
  private MockMvc mvc;
  @Before
  public void setup() throws Exception
  {
    mvc = MockMvcBuilders.webAppContextSetup(
        webApplicationContext
        ).apply(
          springSecurity()
        ).build();
  }
  
  @Test
  public void givenContext_whenServletContext_thenItProvidesUserController()
  {
    ServletContext servletContext = webApplicationContext.getServletContext();
    assertNotNull(servletContext);
    assertTrue(servletContext instanceof MockServletContext);
  }
  
  @Test
  public void loginAvailableForAll() throws Exception
  {
    mvc.perform(get("/login")).andExpect(status().isOk());
  }
  
  @Test
  public void mvcConfigAndSpringSecurityWiredCorrectly() throws Exception
  {
    String adminUsername = "admin";
    String adminPassword = "admin";
    String badUsername = "bad";
    String badPassword = "bad";

    // Ensure bad credentials can NOT log in
    mvc.perform(formLogin("/login").user(badUsername).password(badPassword)
        ).andDo(print()
        ).andExpect(status().isFound()
        ).andExpect(redirectedUrl("/login?error"));
  }
}
