package com.teammacc.client.controller

import org.json.JSONObject
import org.springframework.http.HttpStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import com.teammacc.client.repository.ClientRepository
import khttp.post as httpPost
import com.teammacc.client.entity.Client
import javax.validation.Valid
import org.springframework.web.bind.annotation.*


@CrossOrigin("*", maxAge = 3600)
@RestController
@RequestMapping("/")
class ClientController(@Autowired private val clientRepository : ClientRepository){
	
	@GetMapping("/")
	fun getAllClient() : List<Client> = clientRepository.findAll()
	
//	@PostMapping("/client")
//	fun createClient(@Valid @RequestBody client : Client) : Client = clientRepository.save(client)
	
	@PostMapping("/")
	fun createClient(@Valid @RequestBody client : Client) {
		
		clientRepository.save(client);
		
		val username = client.username;
		val password = client.password;
		
		httpPost(url = "http://sso:8080/register", json = mapOf("userName" to username, "password" to password))

	// AMBIENTE LOCAL
	/*	httpPost(url = "http://localhost:8082/sso/register",
				 json = mapOf("userName" to username, "password" to password))*/
		
	} 
	
	@GetMapping("/{clientId}")
	fun getClientById(@PathVariable clientId : Long) : ResponseEntity<Client> =
		
		clientRepository.findById(clientId).map {
			ResponseEntity.ok(it)
		}.orElse(ResponseEntity.notFound().build())
	
	@PutMapping("/{clientId}")
	fun updateClient(@PathVariable clientId : Long, @Valid @RequestBody updateClient : Client) : ResponseEntity<Client> =
		
		clientRepository.findById(clientId).map{
			val newClient = it.copy(username = updateClient.username, email = updateClient.email)
			ResponseEntity.ok().body(clientRepository.save(newClient))
		}.orElse(ResponseEntity.notFound().build())
	
	@DeleteMapping("/{clientId}")
    fun deleteClient(@PathVariable clientId : Long) : ResponseEntity<Void> =
		
		clientRepository.findById(clientId).map{
			clientRepository.delete(it)
			ResponseEntity<Void>(HttpStatus.OK)
		}.orElse(ResponseEntity.notFound().build())		
	
			
}







