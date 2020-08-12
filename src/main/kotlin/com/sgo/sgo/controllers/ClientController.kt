package com.sgo.sgo.controllers

import com.sgo.sgo.entities.ClientInputDTO
import com.sgo.sgo.entities.ClientOutputDTO
import com.sgo.sgo.services.PersonAddressService
import com.sgo.sgo.services.ClientService
import com.sgo.sgo.services.PersonService
import com.sgo.sgo.services.PhoneService
import com.sgo.sgo.utils.decodeParam
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.annotation.security.RolesAllowed
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
@RequestMapping("/clients")
class ClientController {

    @Autowired
    lateinit var clientService: ClientService

    @Autowired
    lateinit var personService: PersonService

    @Autowired
    lateinit var personAddressService: PersonAddressService

    @Autowired
    lateinit var phoneService: PhoneService

    @GetMapping
    @RolesAllowed("user")
    @Operation(summary = "List clients")
    fun listAll(@RequestParam(value="name", required = false) name: String?,
                @RequestParam(value="document", required = false) document: Long?) : ResponseEntity<List<ClientOutputDTO>> {
        val nameToBeFound: String? = if (name!=null) decodeParam(name) else null
        var clients = clientService.listClients(document, nameToBeFound)
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
    @Transactional
    fun insert(@Valid @RequestBody clientDTO : ClientInputDTO) : ResponseEntity<Void> {
        val client = clientService.fromInputDTO(clientDTO)
        val personInserted = personService.insert(client.person)
        client.person.personAddresses.forEach {
            it.person=personInserted
            personAddressService.insert(it)
        }
        client.person.phones.forEach {
            it.person=personInserted
            phoneService.insert(it)
        }
        val clientInserted = clientService.insert(client)
        val uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clientInserted.id).toUri()
        return ResponseEntity.created(uri).build()
    }

}