package com.teammacc.client.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank

@Entity(name = "client")
data class Client(
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	val id : Long = 0,
	
	@get: NotBlank
	val username : String = "",
	
	@get: NotBlank
	val password : String = "",
	
	@get: NotBlank
	val email : String = ""){
}