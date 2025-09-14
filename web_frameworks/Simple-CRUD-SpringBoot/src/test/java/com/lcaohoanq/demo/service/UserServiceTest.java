package com.lcaohoanq.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.lcaohoanq.demo.domain.user.User;
import com.lcaohoanq.demo.domain.user.UserDTO;
import com.lcaohoanq.demo.domain.user.UserRepository;
import com.lcaohoanq.demo.domain.user.UserService;
import com.lcaohoanq.demo.domain.user.UserServiceImpl;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class) // Enable Mockito in JUnit 5
public class UserServiceTest {

    //fake
    @Mock
    private UserRepository userRepository;

    //UserRepository will be injected into UserServiceImpl
    @InjectMocks
    //private UserService userService; //not use with interface
    private UserServiceImpl userService;

    @Test
    public void findAll_shouldReturnListUsers(){
        var user1 = new User();
        var user2 = new User();
        var user3 = new User();
        var userList = List.of(user1, user2, user3);

        when(this.userRepository.findAll()).thenReturn(userList);
        /*
        Bất cứ khi nào userRepository.findAll() được gọi trong test,
        nó sẽ không truy vấn DB thật, mà trả về list giả (userList) mà bạn đã setup.

        Nói cách khác:

        userRepository ở đây không kết nối database.

        findAll() trả về userList do bạn tự quy định → test chạy đúng như mong muốn.

        Vì vậy bạn không cần gọi save hay setup database trước.

        Cái này là tinh thần của unit test với Mockito:

        - Không đụng tới DB thật (chỉ mock).

        - Kiểm tra logic bên trong service/controller.
        */

        var result = this.userService.findAll();

        assertEquals(3, result.size());
    }

    @Test
    public void findAll_shouldReturnEmptyList_whenNoUser(){
        when(this.userRepository.findAll()).thenReturn(List.of());

        var result = this.userService.findAll();

        assertEquals(0, result.size());
    }

    @Test
    void findAll_shouldReturnPagedUsers() {
        // given
        Pageable pageable = PageRequest.of(0, 2);
        List<User> users = List.of(
            new User(1L, "hoang", "hoang@gmail.com", "hoangclw", "123456"),
            new User(2L, "test", "test@gmail.com", "tester", "654321")
        );
        Page<User> expectedPage = new PageImpl<>(users, pageable, users.size());

        when(userRepository.findAll(pageable)).thenReturn(expectedPage);

        // when
        Page<User> result = userService.findAll(pageable);

        // then
        assertThat(result).isEqualTo(expectedPage); // same page object
        assertThat(result.getContent()).hasSize(2).containsAll(users);

        verify(userRepository, times(1)).findAll(pageable);
    }

    @Test
    public void findById_shouldReturnUser_whenUserExists(){
        var user = new User(1L, "hoang", "test@gmail.com", "hoangclw", "123456");
        when(this.userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));
        var result = this.userService.findById(1L);
        assertEquals("hoangclw", result.getLogin());
    }

    @Test
    public void findById_shouldReturnNull_whenUserNotExists(){
        when(this.userRepository.findById(1L)).thenReturn(java.util.Optional.empty());
        var result = this.userService.findById(1L);
        assertNull(result);
    }

    @Test
    public void createUser_shouldReturnUser_whenEmailValid(){
        //AAA (Arrange, Act, Assert)
        // Arrange: chuan bi
        var inputUser = new User(null, "hoang", "hoang@gmail.com", "hoangclw", "123456");
        var outputUser = new User(1L, "hoang", "hoang@gmail.com", "hoangclw", "123456");
        when(this.userRepository.existsByEmail(inputUser.getEmail())).thenReturn(false);
        when(this.userRepository.save(any())).thenReturn(outputUser);

        // Act: hanh dong
        var result = this.userService.createUser(inputUser);

        // Assert: kiem tra
        assertEquals(1L, result.getId());
        assert(result.getId() != null && result.getId() == 1L);
        assert(result.getEmail().equals("hoang@gmail.com"));

    }

    @Test
    public void createUser_shouldThrowException_whenEmailInValid(){
        //AAA (Arrange, Act, Assert)
        // Arrange: chuan bi
        var inputUser = new User(null, "hoang", "hoang@gmail.com", "hoangclw", "123456");
        when(this.userRepository.existsByEmail(inputUser.getEmail())).thenReturn(true);

        // Act: hanh dong
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            this.userService.createUser(inputUser);
        });

        // Assert: kiem tra
        assertEquals("Email already in use", ex.getMessage());

    }

    @Test
    public void update_shouldUpdateFields_whenUserExists() {
        var existingUser = new User(1L, "hoang", "hoang@gmail.com", "hoangclw", "123456");
        var updateUserDTO = new UserDTO("mnhw.0612@gmail.com", "newlogin", "newpassword");

        // Mock findById trả về user
        when(this.userRepository.findById(1L)).thenReturn(Optional.of(existingUser));

        // Mock save trả lại chính user sau khi update
        when(this.userRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // Gọi service
        var result = this.userService.update(1L, updateUserDTO);

        // Kiểm tra ID giữ nguyên
        assertEquals(1L, result.getId());

        // Kiểm tra các field đã update
        assertEquals("newlogin", result.getLogin());
        assertEquals("newpassword", result.getPassword());
    }

    @Test
    public void update_shouldThrowException_whenUserNotExists() {
        var existingUser = new User(1L, "hoang", "hoang@gmail.com", "hoangclw", "123456");
        var updateUserDTO = new UserDTO("mnhw.0612@gmail.com", "newlogin", "newpassword");

        when (this.userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> {
            this.userService.update(existingUser.getId(), updateUserDTO);
        });

    }


        @Test
    public void delete_shouldThrowException_whenUserNotExists(){
        when(this.userRepository.existsById(1L)).thenReturn(false);

        Exception ex = assertThrows(java.util.NoSuchElementException.class, () -> {
            this.userService.delete(1L);
        });

        assertEquals("User not found with id: 1", ex.getMessage());
    }

    @Test
    public void delete_shouldDoNothing_whenUserExists(){
        when(this.userRepository.existsById(1L)).thenReturn(true);

        this.userService.delete(1L);

        //verify userRepository.deleteById(1L) is called once
        verify(this.userRepository, times(1)).deleteById(1L);
    }

}
