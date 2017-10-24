package edu.csupomona.cs480;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = App.class)

public class WebControllerTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	
	private MockMvc mvc;
	
	
	@Before
	public void setUp(){
		this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	//Byung Hun Lee simple string return test
	//Checks to see if mvc returns content of page /cs480/test
	@Test
	public void testTest() throws Exception{
		mvc.perform(MockMvcRequestBuilders.get("/cs480/test").contentType(MediaType.APPLICATION_JSON))
			.andExpect(content().string("Testing"));
	}
}