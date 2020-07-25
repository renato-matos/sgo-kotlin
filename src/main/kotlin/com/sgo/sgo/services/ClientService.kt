package com.sgo.sgo.services

import com.sgo.sgo.data.ClientRepository
import com.sgo.sgo.entities.Client
import com.sgo.sgo.entities.ClientInputDTO
import com.sgo.sgo.entities.ClientOutputDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ClientService {

    @Autowired
    lateinit var clientRepository: ClientRepository

    fun listAll() : List<ClientOutputDTO> {
        val clients = clientRepository.findAll()
        val clientsDTO : MutableList<ClientOutputDTO> = mutableListOf()
        clients.forEach {
            clientsDTO.add(toOutputDto(it))
        }
        return clientsDTO
    }

    fun insert(client: Client) : Client {
        return clientRepository.save(client)
    }

    private fun toOutputDto(client: Client) : ClientOutputDTO {
        return ClientOutputDTO(
                client.id,
                client.person.id,
                client.person.name,
                client.person.personType.name,
                client.person.document,
                client.person.rg,
                client.person.addresses,
                client.person.insertedOn,
                client.person.lastUpdate)
    }

    fun fromDTO(clientDTO: ClientInputDTO): Client {
        val client = Client(0)
        client.person.addresses = clientDTO.addresses
        return client
    }

}