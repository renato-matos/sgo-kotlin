package com.sgo.sgo.services

import com.sgo.sgo.data.AddressRepository
import com.sgo.sgo.data.AddressTypeRepository
import com.sgo.sgo.data.PersonAddressRepository
import com.sgo.sgo.entities.Address
import com.sgo.sgo.entities.PersonAddress
import com.sgo.sgo.entities.AddressInputDTO
import com.sgo.sgo.entities.AddressOutputDTO
import com.sgo.sgo.entities.domains.AddressType
import com.sgo.sgo.exceptions.ResourceNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class PersonAddressService {

    @Autowired
    lateinit var personAddressRepository: PersonAddressRepository

    @Autowired
    lateinit var addressRepository: AddressRepository

    @Autowired
    lateinit var addressTypeRepository: AddressTypeRepository

    fun insert(personAddress: PersonAddress) : PersonAddress {
        val addressInserted = addressRepository.save(personAddress.address)
        personAddress.address = addressInserted
        return personAddressRepository.save(personAddress)
    }

    fun toOutputDTO(personAddress: PersonAddress) : AddressOutputDTO {
        return AddressOutputDTO(personAddress.id,
                                personAddress.address.addressType.id,
                                personAddress.address.street,
                                personAddress.address.number,
                                personAddress.address.complement,
                                personAddress.address.neighborhood,
                                personAddress.address.city,
                                personAddress.address.state,
                                personAddress.address.country,
                                personAddress.address.zipCode,
                                personAddress.insertedOn,
                                personAddress.lastUpdate)
    }

    fun fromInputDTO(input: AddressInputDTO) : PersonAddress {
        val addressType : AddressType = addressTypeRepository.findByIdOrNull(input.addressType)
                ?: throw ResourceNotFoundException(AddressType::class.simpleName!!, input.addressType.toLong())
        return PersonAddress(Instant.now(), Instant.now(), Address(input.street, input.number, input.complement,
                input.neighborhood, input.city, input.state, input.country, input.zipCode, addressType))
    }

}