package de.tu_berlin.proscience.mca.team_yellow.team_yellow.service;

import de.tu_berlin.proscience.mca.team_yellow.team_yellow.model.Degree;
import de.tu_berlin.proscience.mca.team_yellow.team_yellow.model.Rating;
import de.tu_berlin.proscience.mca.team_yellow.team_yellow.repository.DegreeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DegreeService {
    private DegreeRepository degreeRepository;

    public List<Degree> getAllDegrees(){
        return degreeRepository.findAll();
    }

    public Optional<Degree> getDegreeById(Long id){
        return degreeRepository.findById(id);}

}
