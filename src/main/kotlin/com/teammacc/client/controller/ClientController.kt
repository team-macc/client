package com.teammacc.client.controller

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.beans.factory.annotation.Autowired

import org.springframework.web.bind.annotation.GetMapping

import org.springframework.web.bind.annotation.PostMapping
import javax.validation.Valid
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.http.HttpStatus
import com.teammacc.client.repository.ClientRepository
import com.teammacc.client.entity.Client

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







