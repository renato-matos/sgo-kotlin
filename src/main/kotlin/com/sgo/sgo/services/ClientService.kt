package com.sgo.sgo.services

import com.sgo.sgo.data.ClientRepository
import com.sgo.sgo.entities.*
import com.sgo.sgo.entities.enums.EntityType
import com.sgo.sgo.entities.enums.PersonType
import com.sgo.sgo.exceptions.ResourceNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class ClientService {

    @Autowired
    lateinit var clientRepository: ClientRepository

    @Autowired
    lateinit var addressService: AddressService

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
        val addresses : MutableList<AddressOutputDTO> = mutableListOf()
        client.person.addresses.forEach {
            addresses.add(addressService.toOutputDTO(it))
        }

        return ClientOutputDTO(
                client.id,
                client.person.id,
                client.person.name,
                client.person.personType.name,
                client.person.document,
                client.person.rg,
                addresses,
                client.person.insertedOn,
                client.person.lastUpdate)
    }

    fun fromInputDTO(clientDTO: ClientInputDTO): Client {
        val client = Client(0)
        val personType : PersonType = enumValueOf(clientDTO.personType)
        client.person = Person(0,EntityType.CLIENT,personType,clientDTO.name, clientDTO.rg, clientDTO.document, Instant.now(),
                        Instant.now())
        val addresses : MutableList<Address> = mutableListOf()
        clientDTO.addresses.forEach {
            addresses.add(addressService.fromInputDTO(it))
        }
        client.person.addresses = addresses
        return client
    }

    fun findById(id: Long): ClientOutputDTO {
        val client : Client? = clientRepository.findByIdOrNull(id)
        if(client==null) {
            throw ResourceNotFoundException(id)
        } else {
            return toOutputDto(client)
        }


    }

    fun listClients(document: Long?, name: String?) : List<ClientOutputDTO>? {
        var clients: MutableList<ClientOutputDTO> = mutableListOf()
        if (document!=null) {
            val client : Client? = clientRepository.findByDocument(document)
            if (client != null) {
                clients.add(toOutputDto(client))
                return clients
            } else {
                return null
            }
        }

        if (name!=null) {
            val clientsFound : List<Client?> = clientRepository.findByName(name)
            if (clientsFound != null) {
                clientsFound.forEach {
                    clients.add(toOutputDto(it!!))
                }
                return clients
            } else {
                return null
            }
        }

        return listAll()
    }

}