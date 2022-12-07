package de.hsrm.mi.swt02.backend.api.userlogin.dtos;

import de.hsrm.mi.swt02.backend.domain.userlogin.Userlogin;

public record GetUserloginResponseDTO(
        String username) {
    
    public static GetUserloginResponseDTO from (Userlogin u) {
        return new GetUserloginResponseDTO(
            u.getUsername()
        );
    }
}
