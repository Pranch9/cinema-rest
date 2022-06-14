package ru.pranch.cinema.rest.v1.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.pranch.cinema.dao.UserDao;
import ru.pranch.cinema.dto.user.CreateUserDto;
import ru.pranch.cinema.model.User;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles(value = "test")
class UserControllerTest {
  private final MockMvc mockMvc;
  private final UserDao userDao;
  private final ObjectMapper objectMapper;

  @Autowired
  UserControllerTest(UserDao userDao, WebApplicationContext webApplicationContext, ObjectMapper objectMapper) {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    this.userDao = userDao;
    this.objectMapper = objectMapper;
  }

  @BeforeEach
  void setUp() {
    User user = new User();
    user.setCreationDate(LocalDateTime.now());
    user.setPassword("pswd");
    user.setUsername("user");
    user.setMail("user@user.net");
    userDao.save(user);
  }

  @AfterEach
  void tearDown() {
    userDao.findAll().forEach(user -> userDao.deleteById(user.getId()));
  }

  @Test
  void getUsers() throws Exception {
    mockMvc
      .perform(get("/api/v1/users"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$", Matchers.hasSize(1)));
  }

  @Test
  void getUser() throws Exception {
    User user = userDao.findByUsername("user").get();
    mockMvc
      .perform(get("/api/v1/users/{id}", user.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.username", Matchers.is("user")))
      .andExpect(jsonPath("$.mail", Matchers.is("user@user.net")));
  }

  @Test
  void addUser() throws Exception {
    Assertions.assertEquals(1, userDao.findAll().size());
    CreateUserDto createUserDto = new CreateUserDto();
    createUserDto.setMail("newmail@mail.net");
    createUserDto.setPassword("newpswd");
    createUserDto.setUsername("newuser");
    mockMvc
      .perform(post("/api/v1/users/control")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(objectMapper.writeValueAsBytes(createUserDto)))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.username", Matchers.is("newuser")))
      .andExpect(jsonPath("$.mail", Matchers.is("newmail@mail.net")));

    Assertions.assertEquals(2, userDao.findAll().size());
  }

  @Test
  void editUser() throws Exception {
    User user = userDao.findByUsername("user").get();
    CreateUserDto createUserDto = new CreateUserDto();
    createUserDto.setMail("newmail@mail.net");
    createUserDto.setPassword("newpswd");
    createUserDto.setUsername("newuser");
    mockMvc
      .perform(put("/api/v1/users/control/{id}", user.getId())
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(objectMapper.writeValueAsBytes(createUserDto)))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.username", Matchers.is("newuser")))
      .andExpect(jsonPath("$.mail", Matchers.is("newmail@mail.net")));
  }

  @Test
  void deleteUser() throws Exception {
    List<User> users = userDao.findAll();
    Assertions.assertEquals(1, users.size());
    mockMvc.perform(delete("/api/v1/users/control/{id}", users.get(0).getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    Assertions.assertTrue(userDao.findAll().isEmpty());
  }
}