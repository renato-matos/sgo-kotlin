package com.sgo.sgo.services

import com.sgo.sgo.data.AddressTypeRepository
import com.sgo.sgo.entities.domains.AddressType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AddressTypeService {

    @Autowired
    lateinit var addressTypeRepository: AddressTypeRepository

    fun findAll() : List<AddressType> {
        return addressTypeRepository.findAll()
    }

}