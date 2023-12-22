package coursework.backend.controllers;

import coursework.backend.models.User;
import coursework.backend.services.UserDAO;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@Tag(name = "Users", description = "Operations for interacting with users")
public class UserController {

  private final UserDAO userDAO;

  @Autowired
  public UserController(UserDAO userDAO) {
    this.userDAO = userDAO;
  }

  @GetMapping
  public ResponseEntity<List<User>> getAllUsers() {
    List<User> users = userDAO.getAllUsers();
    return ResponseEntity.ok(users);
  }

  @PostMapping
  public ResponseEntity<Void> createUser(@RequestBody User user) {
    userDAO.createUser(user);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PatchMapping("/{id}")
  public ResponseEntity<User> updateUser(
    @PathVariable Integer id,
    @RequestBody Map<String, Object> updates
  ) {
    User savedUser = userDAO.updateUser(id, updates);
    return ResponseEntity.ok(savedUser);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable int id) {
    userDAO.deleteUser(id);
    return ResponseEntity.noContent().build();
  }
}
