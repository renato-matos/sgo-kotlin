package com.sgo.sgo.services

import com.sgo.sgo.data.PhoneRepository
import com.sgo.sgo.data.PhoneTypeRepository
import com.sgo.sgo.entities.Phone
import com.sgo.sgo.entities.PhoneInputDTO
import com.sgo.sgo.entities.PhoneOutputDTO
import com.sgo.sgo.entities.domains.AddressType
import com.sgo.sgo.entities.domains.PhoneType
import com.sgo.sgo.exceptions.ResourceNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

@Service
class PhoneService {

    @Autowired
    lateinit var phoneTypeRepository: PhoneTypeRepository

    @Autowired
    lateinit var phoneRepository: PhoneRepository

    fun insert(phone: Phone) : Phone {
        return phoneRepository.save(phone)
    }

    fun toOutputDto(input: Phone) : PhoneOutputDTO {
        return PhoneOutputDTO(input.id, input.countryCode, input.ddd, input.phoneNumber, input.extension,
                input.contact, input.operator, input.phoneType.id, input.insertedOn, input.lastUpdate)
    }

    fun fromInputDto(input: PhoneInputDTO) : Phone {
        val phoneType : PhoneType = phoneTypeRepository.findByIdOrNull(input.phoneType)
                ?: throw ResourceNotFoundException(PhoneType::class.simpleName!!, input.phoneType.toLong())
        return Phone(input.countryCode, input.ddd, input.phoneNumber, input.extension,
                input.contact, input.operator, phoneType)
    }

}