package com.sgo.sgo.services

import com.sgo.sgo.data.AddressRepository
import com.sgo.sgo.entities.Address
import com.sgo.sgo.entities.AddressInputDTO
import com.sgo.sgo.entities.AddressOutputDTO
import com.sgo.sgo.entities.enums.AddressType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class AddressService {

    @Autowired
    lateinit var addressRepository: AddressRepository

    fun insert(address: Address) : Address {
        return addressRepository.save(address)
    }

    fun toOutputDTO(address: Address) : AddressOutputDTO {
        return AddressOutputDTO(address.id,
                                address.addressType.toString(),
                                address.street,
                                address.number,
                                address.complement,
                                address.neighborhood,
                                address.city,
                                address.state,
                                address.country,
                                address.zipCode,
                                address.insertedOn,
                                address.lastUpdate)
    }

    fun fromInputDTO(input: AddressInputDTO) : Address {
        val addressType : AddressType = enumValueOf(input.addressType)
        return Address(0, addressType, input.street, input.number, input.complement, input.neighborhood,
                    input.city, input.state, input.country, input.zipCode, Instant.now(), Instant.now())
    }

}