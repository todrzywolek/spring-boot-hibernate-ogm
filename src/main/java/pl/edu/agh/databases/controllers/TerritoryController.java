package pl.edu.agh.databases.controllers;

import lombok.Data;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.databases.entities.Territory;
import pl.edu.agh.databases.repositories.TerritoryRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/{id}")
public class TerritoryController {

    @Data
    public static class TerritoryDTO {
        private Integer territoryID;
        private String territoryDescription;
        private Integer regionID;
    }

    @Autowired
    private TerritoryRepository territoryRepository;

    @Autowired
    private DozerBeanMapper mapper;

    @Transactional
    @GetMapping
    public List<TerritoryDTO> getAll() {
        return territoryRepository
                .findAll()
                .stream()
                .map(x -> mapper.map(x, TerritoryDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    @GetMapping("/{id}")
    public TerritoryDTO getByID(@PathVariable Integer id) {
        return mapper.map(territoryRepository.findByID(id), TerritoryDTO.class);
    }

    @Transactional
    @PostMapping
    public TerritoryDTO create(@RequestBody TerritoryDTO territoryDTO) {
        Territory created = mapper.map(territoryDTO, Territory.class);
        Territory saved = territoryRepository.save(created);
        return mapper.map(saved, TerritoryDTO.class);
    }

    @Transactional
    @PutMapping("/{id}")
    public TerritoryDTO update(@PathVariable Integer id, @RequestBody TerritoryDTO territoryDTO) {
        Territory toUpdate = territoryRepository.findByID(id);
        territoryDTO.setTerritoryID(toUpdate.getTerritoryID());
        mapper.map(territoryDTO, toUpdate);
        Territory updated = territoryRepository.save(toUpdate);
        return mapper.map(updated, TerritoryDTO.class);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        territoryRepository.delete(id);
    }

    @Transactional
    @GetMapping("/{id}/employees")
    public List<EmployeeController.EmployeeDTO> getEmployees(@PathVariable Integer id) {
        return territoryRepository
                .findByID(id)
                .getEmployees()
                .stream()
                .map(x -> mapper.map(x, EmployeeController.EmployeeDTO.class))
                .collect(Collectors.toList());
    }
}
