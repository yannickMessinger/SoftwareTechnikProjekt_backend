package de.hsrm.mi.swt02.backend.api.userlogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import de.hsrm.mi.swt02.backend.api.userlogin.dtos.AddUserloginRequestDTO;
import de.hsrm.mi.swt02.backend.api.userlogin.service.UserloginServiceImpl;
import de.hsrm.mi.swt02.backend.domain.userlogin.Userlogin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
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
    @PostMapping("")
    public ResponseEntity<Long> postNewUser(
            @Schema(
                description = "User Dto (userName: '')",
                implementation = AddUserloginRequestDTO.class,
                required = true)
            @RequestBody AddUserloginRequestDTO uDto) {
        Userlogin u = userService.createUser(uDto.username(), uDto.password());
        AddUserloginRequestDTO test = new AddUserloginRequestDTO("suck", "dick");
        userService.createUser(test.username(), test.password());
        return new ResponseEntity<>(u.getId(), HttpStatus.OK);
    }
}
