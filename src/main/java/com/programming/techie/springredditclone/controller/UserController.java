package com.programming.techie.springredditclone.controller;

import com.programming.techie.springredditclone.dto.UpdateUserName;
import com.programming.techie.springredditclone.model.User;
import com.programming.techie.springredditclone.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * update User By firstName.
     *
     * @param updatedUserName
     */
    @PutMapping("/api/user/edit-firstName")
    public ResponseEntity<String> updateFirstNameOfUser(@RequestBody() UpdateUserName updatedUserName) {
        if (updatedUserName.getFirstName() == null || updatedUserName.getFirstName().isEmpty())
            return status(HttpStatus.EXPECTATION_FAILED).body("Please Fill the 'firstName' with value");
        else
            userService.editUser(updatedUserName.getFirstName());
        return status(HttpStatus.OK).body("FirstName has updated Successfully.");
    }

    /**
     * List All Users .
     *
     * @Return FORBIDDEN in case of invalid Token.
     * Should only Admin
     */
    @GetMapping("/api/users/list")
    public ResponseEntity<List<User>> getAllUsers(/*@RequestHeader(name = "token") String token*/) {
        try {
//            getAuthorization(token);
            return status(HttpStatus.OK).body(userService.getAllUsers());
        } catch (Exception e) {
            return status(HttpStatus.FORBIDDEN).body(null);
        }
    }

}
