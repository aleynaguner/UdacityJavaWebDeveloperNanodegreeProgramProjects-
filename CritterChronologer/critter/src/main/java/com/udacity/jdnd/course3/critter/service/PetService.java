package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    @Autowired
    PetRepository petRepository;

    public Pet getPetById(long petId) {
        return petRepository.findPetById(petId);
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public Pet savePet(Pet pet) {
        return petRepository.save(pet);
    }

    public List<Pet> getAllPetsByCustomerId(Long customerId) {
        return petRepository.findPetsByCustomerId(customerId);
    }

}
