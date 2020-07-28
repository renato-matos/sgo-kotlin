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
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import javax.validation.Valid
import javax.validation.Validator

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
    @Operation(summary = "List clients")
    fun listAll(@RequestParam(value="name", required = false) name: String?,
                @RequestParam(value="document", required = false) document: Long?) : ResponseEntity<List<ClientOutputDTO>> {
        var clients = clientService.listAll()
        //TODO Implementar a lógica de filtro dentro do serviço
        if(name!=null) {
            clients = clients.filter { it.name == name }
        }
        if (document!=null) {
            clients = clients.filter { it.document == document }
        }
        return ResponseEntity.ok().body(clients)
    }

    @GetMapping("/{id}")
    @Operation(summary="Find a client by id")
    fun findById(@PathVariable id: Long) : ResponseEntity<ClientOutputDTO> {
        val client = clientService.findById(id)
        return ResponseEntity.ok().body(client)
    }

    @PostMapping
    @Operation(summary = "Insert a new client")
    fun insert(@Valid @RequestBody clientDTO : ClientInputDTO) : ResponseEntity<Void> {
        //TODO Incluir a validação dos campos no DTO de maneira que funcione
        val client = clientService.fromInputDTO(clientDTO)
        //TODO Incluir validação se o CPF já existe
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