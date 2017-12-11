package user;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

//XML风格  
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextHierarchy({
		@ContextConfiguration(name = "parent", locations = "classpath:spring/springContext.xml"),
		@ContextConfiguration(name = "child", locations = "classpath:spring/springMvc.xml") })
public class UserRegistFacadeTest {

	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testView() throws Exception {
		String requestBody = "{\"mobile\":\"12311\", \"password\":\"abc\"}";
		mockMvc.perform(MockMvcRequestBuilders
				.post("/userRegist/registByMobile")
				.contentType(MediaType.APPLICATION_JSON).content(requestBody)
				.accept(MediaType.APPLICATION_JSON)).andReturn();
	}
}
