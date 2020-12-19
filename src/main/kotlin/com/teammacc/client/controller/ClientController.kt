package com.teammacc.client.controller

import org.springframework.http.HttpStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import com.teammacc.client.repository.ClientRepository
import com.teammacc.client.entity.Client
import javax.validation.Valid
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*", maxAge = 3600)
@RestController
@RequestMapping("/api")
class ClientController(@Autowired private val clientRepository : ClientRepository){
	
	@GetMapping("/client")
	fun getAllClient() : List<Client> = clientRepository.findAll()
	
	@PostMapping("/client")
	fun createClient(@Valid @RequestBody client : Client) : Client = clientRepository.save(client)
	
	@GetMapping("client/{clientId}")
	fun getClientById(@PathVariable clientId : Long) : ResponseEntity<Client> =
		
		clientRepository.findById(clientId).map {
			ResponseEntity.ok(it)
		}.orElse(ResponseEntity.notFound().build())
	
	@PutMapping("/client/{clientId}")
	fun updateClient(@PathVariable clientId : Long, @Valid @RequestBody updateClient : Client) : ResponseEntity<Client> =
		
		clientRepository.findById(clientId).map{
			val newClient = it.copy(name = updateClient.name, email = updateClient.email)
			ResponseEntity.ok().body(clientRepository.save(newClient))
		}.orElse(ResponseEntity.notFound().build())
	
	@DeleteMapping("/client/{clientId}")
    fun deleteClient(@PathVariable clientId : Long) : ResponseEntity<Void> =
		
		clientRepository.findById(clientId).map{
			clientRepository.delete(it)
			ResponseEntity<Void>(HttpStatus.OK)
		}.orElse(ResponseEntity.notFound().build())		
	
}







