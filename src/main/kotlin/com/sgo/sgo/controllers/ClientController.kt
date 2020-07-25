package com.sgo.sgo.controllers

import com.sgo.sgo.entities.Client
import com.sgo.sgo.entities.ClientInputDTO
import com.sgo.sgo.entities.ClientOutputDTO
import com.sgo.sgo.services.ClientService
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

    @GetMapping
    @Operation(summary = "List all clients")
//    @ApiResponse(responseCode = "200", description = "Listed clients", content = [(Content(mediaType = "application/json",
//                schema = Schema(implementation = ClientDTO::class)))])
    fun listAll() : ResponseEntity<List<ClientOutputDTO>> {
        val clients = clientService.listAll()
        return ResponseEntity.ok().body(clients)
    }

    @PostMapping
    @Operation(summary = "Insert a new client")
    fun insert(@RequestBody clientDTO : ClientInputDTO) : ResponseEntity<Void> {
        val client = clientService.fromDTO(clientDTO)
        val clientInserted = clientService.insert(client)
        val uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clientInserted.id).toUri()
        return ResponseEntity.created(uri).build()
    }

}