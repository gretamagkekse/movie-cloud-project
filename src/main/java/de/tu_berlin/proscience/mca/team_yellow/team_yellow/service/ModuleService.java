package de.tu_berlin.proscience.mca.team_yellow.team_yellow.service;

import de.tu_berlin.proscience.mca.team_yellow.team_yellow.model.Module;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ModuleService {

    List<Module> modules = new ArrayList<>();


    // for identifying modules via ID in the future
    public Optional<Module> getModuleById(Long id) {
        return modules.stream()
                .filter(module -> module.getId().equals(id))
                .findFirst();
    }

    // for overview of all modules
    public List<Module> getAllModules() {
        return modules;
    }
}
