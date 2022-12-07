package de.hsrm.mi.swt02.backend.api.userlogin;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import de.hsrm.mi.swt02.backend.api.userlogin.dtos.AddUserloginRequestDTO;
import de.hsrm.mi.swt02.backend.api.userlogin.dtos.GetUserloginResponseDTO;
import de.hsrm.mi.swt02.backend.api.userlogin.service.UserloginServiceImpl;
import de.hsrm.mi.swt02.backend.domain.userlogin.Userlogin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/user")
public class UserloginRestController {
    
    @Autowired
    UserloginServiceImpl userService;

    @Operation(summary = "Posting a new Userlogin to the DB")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User was created"),
        @ApiResponse(responseCode = "400", description = "User was not created")})
    @PostMapping("/register")
    public ResponseEntity<Long> postNewUser(
            @Schema(
                description = "User Dto (userName: '')",
                implementation = AddUserloginRequestDTO.class,
                required = true)
            @RequestBody AddUserloginRequestDTO uDto) {
        Userlogin u = userService.createUser(uDto.username(), uDto.password());
        return new ResponseEntity<>(u.getId(), HttpStatus.OK);
    }

    @Operation(summary = "Get user by username and password")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "user found"),
        @ApiResponse(responseCode = "404", description = "user not found")})
    @PostMapping("")
    public ResponseEntity<Long> getUser(
        @Schema(
            description = "Userlogin", 
            implementation = AddUserloginRequestDTO.class,
            required = true)
        @RequestBody AddUserloginRequestDTO uDto) {
            Optional<Userlogin> u = userService.findUserByUsernameAndPassword(uDto.username(), uDto.password());
            if (u != null){
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
}
