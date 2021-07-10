package com.yugotcha.junit.mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

/*책 10장 목 객체 사용 연습*/
/* https://tech.lattechiffon.com/ 의 JUnit5와 Mockito를 이용한 Mock Test(Java) 참조 */

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    User user;
    User result;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    public void saveNewNameSuccess() {
        //given
        user = User.builder().idx(1)
                .username("choucream")
                .email("yu.gotcha@gmail.com")
                .roles(Arrays.asList("USER", "ADMIN")).build();
        when(userRepository.save(any())).thenReturn(user);

        //when
        result = userService.createUser(User.builder().username("choucream").build());

        //then
        verify(userRepository, times(1)).save(any());
        assertThat(result, equalTo(user));
    }

    @Test
    public void findAllUsers() {
        // given
        List<User> users = Arrays.asList(User.builder().idx(1).username("choucream").build(),
                User.builder().idx(2).username("lattechiffon").build());

        when(userRepository.findAll()).thenReturn(users);

        // when
        List<User> result = userService.findAllUsers();

        // then
        verify(userRepository, times(1)).findAll();
        assertThat(result, equalTo(users));
    }
}