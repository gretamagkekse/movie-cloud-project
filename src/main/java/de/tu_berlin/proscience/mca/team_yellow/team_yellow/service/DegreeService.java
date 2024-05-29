package de.tu_berlin.proscience.mca.team_yellow.team_yellow.service;

import de.tu_berlin.proscience.mca.team_yellow.team_yellow.model.Degree;
import de.tu_berlin.proscience.mca.team_yellow.team_yellow.model.Rating;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DegreeService {
    private List<Degree> degrees = new ArrayList<>();
    private Long nextId = 1L;

    public List<Degree> getAllDegrees(){
        return degrees;
    }

    public Optional<Degree> getDegreeById(Long id){
        return degrees.stream().filter(degree -> degree.getId().equals(id)).findFirst();
    }

    private Long getNextEntryId() {
        return nextId++;
    }

}
