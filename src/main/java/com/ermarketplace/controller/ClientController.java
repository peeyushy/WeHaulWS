package com.ermarketplace.controller;

import com.ermarketplace.exception.ResourceNotFoundException;
import com.ermarketplace.model.Client;
import com.ermarketplace.repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ERMarketPlace/client")
public class ClientController {

	@Autowired
	ClientRepository clientRepository;

	@GetMapping("/all")
	public List<Client> getAllClients() {
		return clientRepository.findAll();
	}

	@PostMapping("/create")
	public Client createClient(@Valid @RequestBody Client client) {
		return clientRepository.save(client);
	}

	@GetMapping("/id/{id}")
	public Client getClientById(@PathVariable(value = "id") Long clientId) {
		return clientRepository.findById(clientId)
				.orElseThrow(() -> new ResourceNotFoundException("Client", "id", clientId));
	}
	
	@GetMapping("/type/{clienttype}")
	public List<Client> getClientByType(@PathVariable(value = "clienttype") String clientType) {
		return clientRepository.findByclienttype(clientType);
	}

	@PutMapping("/id/{id}")
	public Client updateClient(@PathVariable(value = "id") Long clientId,
			@Valid @RequestBody Client companyDetails) {

		Client client = clientRepository.findById(clientId)
				.orElseThrow(() -> new ResourceNotFoundException("Client", "id", clientId));

		client.setClientname(companyDetails.getClientname());
		client.setClienttype(companyDetails.getClienttype());

		Client updatedClient = clientRepository.save(client);
		return updatedClient;
	}

	@DeleteMapping("/id/{id}")
	public ResponseEntity<?> deleteClient(@PathVariable(value = "id") Long clientId) {
		Client client = clientRepository.findById(clientId)
				.orElseThrow(() -> new ResourceNotFoundException("Client", "id", clientId));

		clientRepository.delete(client);

		return ResponseEntity.ok().build();
	}
}