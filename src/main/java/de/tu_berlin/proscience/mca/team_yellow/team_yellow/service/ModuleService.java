package de.tu_berlin.proscience.mca.team_yellow.team_yellow.service;

import de.tu_berlin.proscience.mca.team_yellow.team_yellow.model.Module;
import de.tu_berlin.proscience.mca.team_yellow.team_yellow.repository.ModuleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ModuleService {

    private ModuleRepository moduleRepository;


    // for identifying modules via ID in the future
    public Optional<Module> getModuleById(Long id) {
        return moduleRepository.findById(id);
    }

    // for overview of all modules
    public List<Module> getAllModules() {
        return moduleRepository.findAll();
    }
}
