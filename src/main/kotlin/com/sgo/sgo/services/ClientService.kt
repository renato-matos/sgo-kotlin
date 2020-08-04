package com.sgo.sgo.services

import com.sgo.sgo.data.ClientRepository
import com.sgo.sgo.entities.*
import com.sgo.sgo.entities.domains.EntityType
import com.sgo.sgo.entities.domains.PersonType
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
    lateinit var personAddressService: PersonAddressService

    @Autowired
    lateinit var phoneService: PhoneService

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
        client.person.personAddresses.forEach {
            addresses.add(personAddressService.toOutputDTO(it))
        }
        val phones : MutableList<PhoneOutputDTO> = mutableListOf()
        client.person.phones.forEach {
            phones.add(phoneService.toOutputDto(it))
        }
        return ClientOutputDTO(
                client.id,
                client.person.id,
                client.person.name,
                client.person.personType.name,
                client.person.document,
                client.person.rg,
                addresses,
                phones,
                client.person.insertedOn,
                client.person.lastUpdate)
    }

    fun fromInputDTO(clientDTO: ClientInputDTO): Client {
        val client = Client(0)
        val personType : PersonType = enumValueOf(clientDTO.personType)
        client.person = Person(0,EntityType.CLIENT,personType,clientDTO.name, clientDTO.rg, clientDTO.document, Instant.now(),
                        Instant.now())
        val personAddresses : MutableList<PersonAddress> = mutableListOf()
        clientDTO.addresses.forEach {
            personAddresses.add(personAddressService.fromInputDTO(it))
        }
        client.person.personAddresses = personAddresses
        val phones : MutableList<Phone> = mutableListOf()
        clientDTO.phones.forEach {
            phones.add(phoneService.fromInputDto(it))
        }
        client.person.phones = phones
        return client
    }

    fun findById(id: Long): ClientOutputDTO {
        val client : Client? = clientRepository.findByIdOrNull(id)
        if(client==null) {
            throw ResourceNotFoundException(Client::class.simpleName!!, id)
        } else {
            return toOutputDto(client)
        }
    }

    fun listClients(document: Long?, name: String?) : List<ClientOutputDTO>? {
        var clients: MutableList<ClientOutputDTO> = mutableListOf()
        if (document!=null) {
            val client : Client? = clientRepository.findByDocument(document)
            return if (client != null) {
                clients.add(toOutputDto(client))
                clients
            } else {
                null
            }
        }

        if (name!=null) {
            val clientsFound : List<Client?> = clientRepository.findByName("%$name%")
            return if (clientsFound != null) {
                clientsFound.forEach {
                    clients.add(toOutputDto(it!!))
                }
                clients
            } else {
                null
            }
        }
        return listAll()
    }
}