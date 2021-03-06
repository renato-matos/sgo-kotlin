package com.sgo.sgo.services

import com.sgo.sgo.data.ClientRepository
import com.sgo.sgo.data.SupplierRepository
import com.sgo.sgo.entities.*
import com.sgo.sgo.entities.domains.EntityType
import com.sgo.sgo.entities.domains.PersonType
import com.sgo.sgo.exceptions.ResourceNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class SupplierService {

    @Autowired
    lateinit var supplierRepository: SupplierRepository

    @Autowired
    lateinit var personAddressService: PersonAddressService

    @Autowired
    lateinit var phoneService: PhoneService

    fun listAll() : List<SupplierOutputDTO> {
        val suppliers = supplierRepository.findAll()
        val suppliersDTO : MutableList<SupplierOutputDTO> = mutableListOf()
        suppliers.forEach {
            suppliersDTO.add(toOutputDto(it))
        }
        return suppliersDTO
    }

    fun insert(supplier: Supplier) : Supplier {
        return supplierRepository.save(supplier)
    }

    private fun toOutputDto(supplier: Supplier) : SupplierOutputDTO {
        val addresses : MutableList<AddressOutputDTO> = mutableListOf()
        supplier.person.personAddresses.forEach {
            addresses.add(personAddressService.toOutputDTO(it))
        }

        val phones : MutableList<PhoneOutputDTO> = mutableListOf()
        supplier.person.phones.forEach {
            phones.add(phoneService.toOutputDto(it))
        }

        return SupplierOutputDTO(supplier.id, supplier.person.id, supplier.person.name,
                supplier.person.personType.toString(), supplier.person.document, supplier.person.rg,
                supplier.activitySegment, supplier.contact, supplier.ccm, supplier.ie, supplier.email,
                supplier.site, supplier.comments, addresses, phones, supplier.person.insertedOn, supplier.person.lastUpdate)
    }

    fun fromInputDTO(supplierDTO: SupplierInputDTO): Supplier {
        val supplier = Supplier(0, supplierDTO.activeSegment, supplierDTO.contact,
                supplierDTO.ccm, supplierDTO.ie, supplierDTO.email, supplierDTO.site, supplierDTO.comments)
        val personType : PersonType = enumValueOf(supplierDTO.personType)
        supplier.person = Person(0,EntityType.CLIENT,personType,supplierDTO.name, supplierDTO.rg, supplierDTO.document,
                Instant.now(),Instant.now())
        val personAddresses : MutableList<PersonAddress> = mutableListOf()
        supplierDTO.addresses.forEach {
            personAddresses.add(personAddressService.fromInputDTO(it))
        }
        val phones : MutableList<Phone> = mutableListOf()
        supplierDTO.phones.forEach {
            phones.add(phoneService.fromInputDto(it))
        }
        supplier.person.personAddresses = personAddresses
        supplier.person.phones = phones
        return supplier
    }

    fun findById(id: Long): SupplierOutputDTO {
        val supplier : Supplier? = supplierRepository.findByIdOrNull(id)
        if(supplier==null) {
            throw ResourceNotFoundException(Supplier::class.simpleName!!, id)
        } else {
            return toOutputDto(supplier)
        }
    }

    fun listSuppliers(document: Long?, name: String?) : List<SupplierOutputDTO>? {
        var suppliers: MutableList<SupplierOutputDTO> = mutableListOf()
        if (document!=null) {
            val supplier : Supplier? = supplierRepository.findByDocument(document)
            return if (supplier != null) {
                suppliers.add(toOutputDto(supplier))
                suppliers
            } else {
                null
            }
        }

        if (name!=null) {
            val suppliersFound : List<Supplier?> = supplierRepository.findByName("%$name%")
            return run {
                suppliersFound.forEach {
                    suppliers.add(toOutputDto(it!!))
                }
                suppliers
            }
        }
        return listAll()
    }
}