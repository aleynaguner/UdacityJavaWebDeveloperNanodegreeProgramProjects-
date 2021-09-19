package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    Pet findPetById(Long petId);
    List<Pet> findAll();
    Pet save(Pet pet);
    List<Pet> findPetsByCustomerId(Long customerId);
}
