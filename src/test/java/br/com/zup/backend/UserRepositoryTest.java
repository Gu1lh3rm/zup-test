package br.com.zup.backend;

import br.com.zup.backend.primary.domain.User;
import br.com.zup.backend.primary.domain.enums.UserType;
import br.com.zup.backend.primary.repositories.UserRepository;
import br.com.zup.backend.primary.services.generics.GenericService;
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
@SpringBootTest(classes = BackendZupApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ActiveProfiles({ "test" })
public class UserRepositoryTest {

    @Autowired
    public UserRepository userRepository;

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
        getUserTest().setUser(this.userRepository.save(getUserTest().getUser()));
        assertions();
    }

    @Test
    public void test2_findTheUserWithAllMethods() {
        configNewUser();
        getUserTest().setUser(this.userRepository.findByEmail(getUserTest().getUser().getEmail()));
        assertions();
        getUserTest().setUser(this.userRepository.findById(getUserTest().getUser().getId()).orElse(null));
        assertions();
        getUserTest().setUser(this.userRepository.findBySocialCode(getUserTest().getUser().getSocialCode()));
        assertions();
        assertionListContais(this.userRepository.findAll());

    }
    @Test
    public void test3_updatedUser() {
        configNewUser();
        getUserTest().setUser(this.userRepository.findByEmail(getUserTest().getUser().getEmail()));
        configOtherUser();
        getUserTest().getUser().setDescription(getUserTest().getOtherUser().getDescription());
        getUserTest().getUser().setName(getUserTest().getOtherUser().getName());
        this.userRepository.save(getUserTest().getUser());
        assertionsUpdate();
    }

    @Test
    public void test4_deletedUser() {
        configNewUser();
        getUserTest().setUser(this.userRepository.findByEmail(getUserTest().getUser().getEmail()));
        this.userRepository.delete(getUserTest().getUser());
        assertionDeleted();
        test1_createdUser();
        this.userRepository.deleteById(getUserTest().getUser().getId());
        assertionDeleted();
    }

    public void assertionDeleted() {
        if(getUserTest().getUser() != null) {
            getUserTest().setUser(this.userRepository.findByEmail(getUserTest().getUser().getEmail()));

        }
        Assertions.assertThat(getUserTest().getUser()).isNull();

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
