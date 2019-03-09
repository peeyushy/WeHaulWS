package com.wehaul.controller;

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

import com.wehaul.constants.AppConstants;
import com.wehaul.constants.AppConstants.Status;
import com.wehaul.exception.ResourceNotFoundException;
import com.wehaul.model.Client;
import com.wehaul.model.GetClient;
import com.wehaul.model.User;
import com.wehaul.repository.ClientRepository;
import com.wehaul.repository.UserRepository;

@RestController
@RequestMapping("/wehaul/client")
public class ClientController {

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	UserRepository userRepository;

	@GetMapping("/all")
	public List<Client> getAllClients() {
		return clientRepository.findAll();
	}
	
	@GetMapping("/search")
	public List<Client> getAllExceptAdminAndLoggedInClients() {
		//loggedinuser check TO-DO
		return clientRepository.findByclienttypeNot(AppConstants.ClientType.A);		
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
	public List<Client> getClientByType(@PathVariable(value = "clienttype") AppConstants.ClientType clientType) {
		return clientRepository.findByclienttype(clientType);
	}

	@PutMapping("/id/{id}")
	public Client updateClient(@PathVariable(value = "id") Long clientId, @Valid @RequestBody Client companyDetails) {

		Client client = clientRepository.findById(clientId)
				.orElseThrow(() -> new ResourceNotFoundException("Client", "id", clientId));
		List<User> users = new ArrayList<>();
		client.setClientname(companyDetails.getClientname());
		client.setClienttype(companyDetails.getClienttype());
		client.setAddress(companyDetails.getAddress());
		client.setComments(companyDetails.getComments());
		client.setEmail(companyDetails.getEmail());
		client.setContactno(companyDetails.getContactno());
		client.setLastupdatedby(companyDetails.getLastupdatedby());
		client.setCity(companyDetails.getCity());
		client.setVerified(companyDetails.isVerified());
		client.setRevid(companyDetails.getRevid() + 1);
		client.setActive(companyDetails.isActive());
		if (!companyDetails.isActive()) {
			for (User user : client.getUsers()) {
				user.setActive(false);
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