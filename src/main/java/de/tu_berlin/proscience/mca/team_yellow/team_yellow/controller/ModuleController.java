package de.tu_berlin.proscience.mca.team_yellow.team_yellow.controller;

import de.tu_berlin.proscience.mca.team_yellow.team_yellow.model.Module;
import de.tu_berlin.proscience.mca.team_yellow.team_yellow.service.ModuleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class ModuleController {

    private ModuleService moduleService;

    public ModuleController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @GetMapping(path="/modules")
    public List<Module> getAllModules(){
        return moduleService.getAllModules();
    }



    @GetMapping(path="/modules/{id}")
    public Module getModuleById(@PathVariable Long id){
        Optional<Module> moduleOptional = moduleService.getModuleById(id);
        if (moduleOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return moduleOptional.get();
    }
    //getModulesByDegreeId


}
