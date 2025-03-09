package lab.anubis.anubiscms.features.user.controller;

import lab.anubis.anubiscms.features.user.dto.UserDTO;
import lab.anubis.anubiscms.features.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/users")
@PreAuthorize("hasRole('CLIENT_ADMIN')")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return new ResponseEntity<>(
                userService.getAllUsers(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(Long userId){
        return new ResponseEntity<>(
                userService.getUserbyId(userId),
                HttpStatus.OK
        );
    }

    @PutMapping("/update-role")
    public ResponseEntity<String> updateUserRole(@RequestParam Long userId,
                                                 @RequestParam String roleName){
        userService.updateUserRole(userId, roleName);
        return new ResponseEntity<>(
                "Role de cet utilisateur a été mis à jour",
                HttpStatus.OK
        );
    }

}
