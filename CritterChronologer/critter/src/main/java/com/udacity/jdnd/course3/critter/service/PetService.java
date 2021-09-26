package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PetService {

    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerRepository customerRepository;

    public Pet getPetById(long petId) {
        List<Pet> list = this.getAllPets();
        return petRepository.findPetById(petId);
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public Pet savePet(Pet pet, Long customerId) {
        Customer customer = customerRepository.findCustomerById(customerId);
        pet.setCustomer(customer);
        pet = petRepository.save(pet);
        customer.addNewPetToCustomer(pet);
        customerRepository.save(customer);
        return pet;
    }

    public List<Pet> getAllPetsByCustomerId(Long customerId) {
        return petRepository.findPetsByCustomerId(customerId);
    }

}
