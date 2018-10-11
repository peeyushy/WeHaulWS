package com.erstaticdata.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erstaticdata.exception.ResourceNotFoundException;
import com.erstaticdata.model.Client;
import com.erstaticdata.model.User;
import com.erstaticdata.repository.ClientRepository;
import com.erstaticdata.repository.UserRepository;

@RestController
@RequestMapping("/ERStaticData/client")
public class ClientController {

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	UserRepository userRepository;

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
	public Client updateClient(@PathVariable(value = "id") Long clientId, @Valid @RequestBody Client companyDetails) {

		Client client = clientRepository.findById(clientId)
				.orElseThrow(() -> new ResourceNotFoundException("Client", "id", clientId));
		List<User> users = new ArrayList<>();
		client.setClientname(companyDetails.getClientname());
		// once created client type can't be updated
		// client.setClienttype(companyDetails.getClienttype());
		client.setAddressline1(companyDetails.getAddressline1());
		client.setAddressline2(companyDetails.getAddressline2());
		client.setCity(companyDetails.getCity());
		client.setComments(companyDetails.getComments());
		client.setContactno(companyDetails.getContactno());
		client.setCountry(companyDetails.getCountry());
		client.setLastupdatedby(companyDetails.getLastupdatedby());
		client.setPostcode(companyDetails.getPostcode());
		client.setWebsite(companyDetails.getWebsite());
		client.setRevid(companyDetails.getRevid() + 1);
		client.setStatus(companyDetails.getStatus());
		if (companyDetails.getStatus().equalsIgnoreCase("DISABLED")) {
			for (User user : client.getUsers()) {
				user.setStatus("DISABLED");
				users.add(user);
			}
			client.getUsers().clear();
			client.getUsers().addAll(users);
		}

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