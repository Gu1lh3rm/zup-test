package br.com.api.backend;

import br.com.api.backend.primary.domain.User;
import br.com.api.backend.primary.domain.enums.UserType;
import br.com.api.backend.primary.services.UserService;
import org.assertj.core.api.Assertions;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApiApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ActiveProfiles({ "test" })
public class UserServiceTest {

    @Autowired
    public UserService userService;

    public ExpectedException thrown = ExpectedException.none();

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
    public void test1_createdUser() {
        configNewUser();
        getUserTest().setUser(this.userService.save(getUserTest().getUser()));
        assertions();
    }

    @Test
    public void test2_findTheUserWithAllMethods() {
        configNewUser();
        getUserTest().setUser(this.userService.findByEmail(getUserTest().getUser().getEmail()));
        assertions();
        getUserTest().setUser(this.userService.findById(getUserTest().getUser().getId()));
        assertions();
        getUserTest().setUser(this.userService.findBySocialCode(getUserTest().getUser().getSocialCode()));
        assertions();
        assertionListContais(this.userService.findAll());

    }
    @Test
    public void test3_updatedUser() {
        configNewUser();
        getUserTest().setUser(this.userService.findByEmail(getUserTest().getUser().getEmail()));
        configOtherUser();
        getUserTest().getUser().setDescription(getUserTest().getOtherUser().getDescription());
        getUserTest().getUser().setName(getUserTest().getOtherUser().getName());
        this.userService.update(getUserTest().getUser().getId(), getUserTest().getUser());
        assertionsUpdate();
    }

    @Test
    public void test4_deletedUser() {
        configNewUser();
        getUserTest().setUser(this.userService.findByEmail(getUserTest().getUser().getEmail()));
        this.userService.delete(getUserTest().getUser().getId(), getUserTest().getUser());
        assertionDeleted();
    }

    @Test
    public void test5_findAllForCriteria() {
        test1_createdUser();
        assertionListContais(this.userService.findAll("name", "like", "Guilherme"));
        assertionListContais(this.userService.findAll("deleted", "equal", "false"));
        assertionListNoContais(this.userService.findAll("deleted", "equal", "true"));
        assertionListNoContais(this.userService.findAll("id", ">", getUserTest().getUser().getId().toString()));
        assertionListNoContais(this.userService.findAll("id", "<", getUserTest().getUser().getId().toString()));
        assertionListContais(this.userService.findAll("id", ">=", getUserTest().getUser().getId().toString()));
        assertionListContais(this.userService.findAll("id", "<=", getUserTest().getUser().getId().toString()));
    }

    public void assertionDeleted() {
        if(getUserTest().getUser() != null) {
            getUserTest().setUser(this.userService.findByEmail(getUserTest().getUser().getEmail()));

        }
        Assertions.assertThat(getUserTest().getUser()).isNull();

    }


    public void assertionListContais(List<User> list) {
        Assertions.assertThat(
                list.stream().filter(c
                        -> c.getEmail().contains(getUserTest().getUser().getEmail()))
                        .findFirst()).isNotEmpty();
    }

    public void assertionListNoContais(List<User> list) {
        Assertions.assertThat(
                list.stream().filter(c
                        -> c.getEmail().contains(getUserTest().getUser().getEmail()))
                        .findFirst()).isEmpty();
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
