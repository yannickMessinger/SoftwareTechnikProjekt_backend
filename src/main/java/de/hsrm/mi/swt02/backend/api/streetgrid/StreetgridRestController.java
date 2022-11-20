package de.hsrm.mi.swt02.backend.api.streetgrid;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/streetgrid")
public class StreetgridRestController {

    @Autowired
    private StreetGridServiceImpl gridService;
    private Logger logger = LoggerFactory.getLogger(StreetgridRestController.class);

    @Operation(summary = "Post new Grid. Grid will be saved in Database as a String")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Grid was saved"),
    })
    @PostMapping("")
    public ResponseEntity<HttpStatus> postNewStreetGrid(@RequestBody String gridDataString) {
        logger.info(gridDataString);
        gridService.saveStreetGrid(gridDataString);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Get StreetGrid by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "StreetGrid was found"),
            @ApiResponse(responseCode = "500", description = "StreetGrid was not found and threw Exception internally")
    })
    @GetMapping("/{id}")
    public ResponseEntity<String> getStreetGridById(@PathVariable long id) {
        return new ResponseEntity<>(gridService.getStreetGridById(id).getGridData(), HttpStatus.OK);
    }

    @Operation(summary = "Get StreetGrid by LobbyId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "StreetGrid was found by LobbyId"),
            @ApiResponse(responseCode = "500", description = "StreetGrid was not found by LobbyId and threw Exception internally")
    })
    @GetMapping("/lobby/{id}")
    public ResponseEntity<String> getStreetGridByLobbyId(@PathVariable long id) {
        return new ResponseEntity<>(gridService.getStreetGridByLobbyId(id).getGridData(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteStreetGrid(@PathVariable("id") long id){
        gridService.deleteStreetGridById(id);
    }
}

