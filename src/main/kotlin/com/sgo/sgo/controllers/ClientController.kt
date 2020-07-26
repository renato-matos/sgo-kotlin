package com.sgo.sgo.controllers

import com.sgo.sgo.entities.Client
import com.sgo.sgo.entities.ClientInputDTO
import com.sgo.sgo.entities.ClientOutputDTO
import com.sgo.sgo.services.AddressService
import com.sgo.sgo.services.ClientService
import com.sgo.sgo.services.PersonService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI

@RestController
@RequestMapping("/clients")
class ClientController {

    @Autowired
    lateinit var clientService: ClientService

    @Autowired
    lateinit var personService: PersonService

    @Autowired
    lateinit var addressService: AddressService

    @GetMapping
    @Operation(summary = "List all clients")
    fun listAll() : ResponseEntity<List<ClientOutputDTO>> {
        val clients = clientService.listAll()
        return ResponseEntity.ok().body(clients)
    }

    @PostMapping
    @Operation(summary = "Insert a new client")
    fun insert(@RequestBody clientDTO : ClientInputDTO) : ResponseEntity<Void> {
        val client = clientService.fromInputDTO(clientDTO)
        val personInserted = personService.insert(client.person)
        client.person.addresses.forEach {
            it.person=personInserted
            addressService.insert(it)
        }
        val clientInserted = clientService.insert(client)
        val uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clientInserted.id).toUri()
        return ResponseEntity.created(uri).build()
    }

}