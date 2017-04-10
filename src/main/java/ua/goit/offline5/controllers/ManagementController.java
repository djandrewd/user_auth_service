package ua.goit.offline5.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.goit.offline5.services.UsersService;

/**
 * Controller for users management.
 * <p>
 * Created by andreymi on 4/3/2017.
 */
@RestController
@RequestMapping("/users")
public class ManagementController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ManagementController.class);

    private final UsersService usersService;

    @Autowired
    public ManagementController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> createUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            if (usersService.loadUser(username).isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User with name: " + username + " already exists!");
            }
            usersService.createUser(username, password);
            LOGGER.info("User: {} is created!", username);
            return ResponseEntity.ok("ok!");
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(value = "/remove", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> removeUser(@RequestParam("username") String username) {
        try {
            return usersService.removeUser(username).map(s -> {
                LOGGER.info("User: {} is removed!", username);
                return ResponseEntity.ok("ok!");
            }).orElse(ResponseEntity.badRequest().build());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
