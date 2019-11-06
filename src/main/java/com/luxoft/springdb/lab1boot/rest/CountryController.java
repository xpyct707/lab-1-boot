package com.luxoft.springdb.lab1boot.rest;

import com.luxoft.springdb.lab1boot.dao.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.luxoft.springdb.lab1boot.rest.CountryDto.toDomainObject;
import static com.luxoft.springdb.lab1boot.rest.CountryDto.toDto;
import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class CountryController {
    private final CountryRepository countryRepo;


    @GetMapping(value = "/countries")
    public List<CountryDto> getAll() {
        return countryRepo
                .findAll()
                .stream()
                .map(CountryDto::toDto)
                .collect(toList());
    }

    @GetMapping(value = "/countries/{id}")
    public CountryDto get(@PathVariable("id") int id) {
        return countryRepo
                .findById(id)
                .map(CountryDto::toDto)
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping(value = "/countries")
    public @ResponseBody CountryDto create(@RequestBody CountryDto dto) {
        return toDto(countryRepo.save(toDomainObject(dto)));
    }

    @DeleteMapping("/countries/{id}")
    public void delete(@PathVariable("id") int id) {
        countryRepo.deleteById(id);
    }

    @PutMapping("/countries/{id}/name")
    public void changeName(
            @PathVariable("id") int id,
            @RequestParam("name") String name) {
        countryRepo
                .findById(id)
                .map(country -> {
                    country.setName(name);
                    countryRepo.save(country);
                    return country;
                }).orElseThrow(NotFoundException::new);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotEnoughFunds(NotFoundException ex) {
        return ResponseEntity
                .badRequest()
                .body("Not found");
    }
}
