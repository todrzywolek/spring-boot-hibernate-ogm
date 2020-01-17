package pl.edu.agh.databases.controllers;

import lombok.Data;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.databases.entities.Shipper;
import pl.edu.agh.databases.repositories.ShipperRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shipper")
public class ShipperController {

    @Data
    public static class ShipperDTO {
        private Integer shipperID;
        private String companyName;
        private String phone;
    }

    @Autowired
    private ShipperRepository shipperRepository;

    @Autowired
    private DozerBeanMapper mapper;

    @Transactional
    @GetMapping
    public List<ShipperDTO> getAll() {
        return shipperRepository
                .findAll()
                .stream()
                .map(x -> mapper.map(x, ShipperDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    @GetMapping("/{id}")
    public ShipperDTO getByID(@PathVariable Integer id) {
        return mapper.map(shipperRepository.findByID(id), ShipperDTO.class);
    }

    @Transactional
    @PostMapping
    public ShipperDTO createCategory(@RequestBody ShipperDTO shipperDTO) {
        Shipper shipper = mapper.map(shipperDTO, Shipper.class);
        Shipper created = shipperRepository.save(shipper);
        return mapper.map(created, ShipperDTO.class);
    }

    @Transactional
    @PutMapping("/{id}")
    public ShipperDTO updateCategory(@PathVariable Integer id, @RequestBody ShipperDTO shipperDTO) {
        Shipper shipper = shipperRepository.findByID(id);
        shipperDTO.setShipperID(shipper.getShipperID());
        mapper.map(shipperDTO, shipper);
        Shipper saved = shipperRepository.save(shipper);
        return mapper.map(saved, ShipperDTO.class);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Integer id) {
        shipperRepository.delete(id);
    }
}
