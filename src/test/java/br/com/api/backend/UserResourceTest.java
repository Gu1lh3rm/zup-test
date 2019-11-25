package br.com.api.backend;

import br.com.api.backend.primary.domain.User;
import br.com.api.backend.primary.domain.enums.UserType;
import br.com.api.backend.primary.resources.UserResource;
import br.com.api.backend.primary.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.*;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ActiveProfiles({ "test" })
public class UserResourceTest {

    private MockMvc mvc;

    @Mock
    private UserResource userResource;

    @InjectMocks
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(userResource).build();
    }

    private UserTest userTest = new UserTest();

    public UserTest getUserTest() {
        return userTest;
    }


    public void configNewUser() {
        getUserTest().setUser(new User("Guilherme Monteiro Lourenco","Teste Unitario Guilherme",
                "guilhermemonteirolourenco@gmail.com",
                39771451855L,new Date(), UserType.PEOPLE));
        getUserTest().setUserEmail(getUserTest().getUser().getEmail());
    }

    public void configOtherUser() {
        getUserTest().setOtherUser(new User("Guilherme Lourenco","Unitario Guilherme",
                "guilhermemonteirolourenco@gmail.com",
                39771451855L,new Date(), UserType.PEOPLE));
        getUserTest().setUserEmail(getUserTest().getUser().getEmail());
    }

    @Test
    public void test1_createdUser() throws Exception {
        configNewUser();

        mvc.perform(MockMvcRequestBuilders.post("/users")
                .content(asJsonString(getUserTest().getUser()))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(get("/users/1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void assertionListContais(List<User> list) {
        Assertions.assertThat(
                list.stream().filter(c
                        -> c.getEmail().contains(getUserTest().getUser().getEmail()))
                        .findFirst()).isNotEmpty();
    }

    public void assertions() {
        Assertions.assertThat(getUserTest().getUser().getId()).isNotNull();
        Assertions.assertThat(getUserTest().getUser().getEmail()).isEqualTo(getUserTest().getUserEmail());
    }

    public void assertionsUpdate() {
        Assertions.assertThat(getUserTest().getUser().getId()).isNotNull();
        Assertions.assertThat(getUserTest().getUser().getEmail()).isEqualTo(getUserTest().getOtherUser().getEmail());
        Assertions.assertThat(getUserTest().getUser().getName()).isEqualTo(getUserTest().getOtherUser().getName());
        Assertions.assertThat(getUserTest().getUser().getDescription()).isEqualTo(getUserTest().getOtherUser().getDescription());
    }

}
