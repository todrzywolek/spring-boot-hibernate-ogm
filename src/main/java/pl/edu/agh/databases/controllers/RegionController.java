package pl.edu.agh.databases.controllers;

import lombok.Data;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.databases.entities.Region;
import pl.edu.agh.databases.repositories.RegionRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/region")
public class RegionController {

    @Data
    public static class RegionDTO {
        private Integer regionID;
        private String regionDescription;
    }

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private DozerBeanMapper mapper;

    @Transactional
    @GetMapping
    public List<RegionDTO> getAll() {
        return regionRepository
                .findAll()
                .stream()
                .map(x -> mapper.map(x, RegionDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    @GetMapping("/{id}")
    public RegionDTO getByID(@PathVariable Integer id) {
        return mapper.map(regionRepository.findByID(id), RegionDTO.class);
    }

    @Transactional
    @PostMapping
    public RegionDTO addRegion(@RequestBody RegionDTO regionDTO) {
        Region region = mapper.map(regionDTO, Region.class);
        Region created = regionRepository.save(region);
        return mapper.map(created, RegionDTO.class);
    }

    @Transactional
    @PutMapping("/{id}")
    public RegionDTO updateRegion(@PathVariable Integer id, @RequestBody RegionDTO regionDTO) {
        Region region = regionRepository.findByID(id);
        regionDTO.setRegionID(region.getRegionID());
        mapper.map(regionDTO, region);
        Region saved = regionRepository.save(region);
        return mapper.map(saved, RegionDTO.class);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void deleteRegion(@PathVariable Integer id) {
        regionRepository.delete(id);
    }
}
