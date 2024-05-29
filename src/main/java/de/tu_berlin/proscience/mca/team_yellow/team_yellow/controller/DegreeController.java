package de.tu_berlin.proscience.mca.team_yellow.team_yellow.controller;

import de.tu_berlin.proscience.mca.team_yellow.team_yellow.model.Degree;
import de.tu_berlin.proscience.mca.team_yellow.team_yellow.service.DegreeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class DegreeController {

    private DegreeService degreeService;

    public DegreeController(DegreeService degreeService) {
        this.degreeService = degreeService;
    }

    @GetMapping(path="/degrees")
    public List<Degree> getAllDegrees(){
       return degreeService.getAllDegrees();
    }

    @GetMapping(path="/degrees/{id}")
    public Degree getDegreeById(@PathVariable Long id){
        Optional<Degree> degree = degreeService.getDegreeById(id);
        if(degree.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return degree.get();
    }
}
