package com.hosp.hospms.controllers.v1;

import com.hosp.hospms.models.DTOs.people.PeopleDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/peoples")
@CrossOrigin( origins = "*")
public class PeopleController {

    @PostMapping
    public void create(@RequestBody PeopleDTO peopleDTO){

        System.out.println(peopleDTO.getClass().getName());
        System.out.println(peopleDTO.toString());
    }

}
