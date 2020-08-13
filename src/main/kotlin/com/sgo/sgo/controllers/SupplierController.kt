package com.sgo.sgo.controllers

import com.sgo.sgo.entities.ClientInputDTO
import com.sgo.sgo.entities.ClientOutputDTO
import com.sgo.sgo.entities.SupplierInputDTO
import com.sgo.sgo.entities.SupplierOutputDTO
import com.sgo.sgo.services.*
import com.sgo.sgo.utils.decodeParam
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
@RequestMapping("/suppliers")
@Tag(name = "Suppliers", description = "Manage suppliers")
class SupplierController {

    @Autowired
    lateinit var supplierService: SupplierService

    @Autowired
    lateinit var personService: PersonService

    @Autowired
    lateinit var personAddressService: PersonAddressService

    @Autowired
    lateinit var phoneService: PhoneService

    @GetMapping
    @Operation(summary = "List suppliers")
    fun listAll(@RequestParam(value = "name", required = false) name: String?,
                @RequestParam(value = "document", required = false) document: Long?): ResponseEntity<List<SupplierOutputDTO>> {
        val nameToBeFound: String? = if (name != null) decodeParam(name) else null
        val suppliers = supplierService.listSuppliers(document, nameToBeFound)
        return ResponseEntity.ok().body(suppliers)
    }

    @GetMapping("/{id}")
    @Operation(summary="Find a supplier by id")
    fun findById(@PathVariable id: Long) : ResponseEntity<SupplierOutputDTO> {
        val supplier = supplierService.findById(id)
        return ResponseEntity.ok().body(supplier)
    }

    @PostMapping
    @Operation(summary = "Insert a new supplier")
    @Transactional
    fun insert(@Valid @RequestBody supplierDTO : SupplierInputDTO) : ResponseEntity<Void> {
        val supplier = supplierService.fromInputDTO(supplierDTO)
        val personInserted = personService.insert(supplier.person)
        supplier.person.personAddresses.forEach {
            it.person=personInserted
            personAddressService.insert(it)
        }
        supplier.person.phones.forEach {
            it.person=personInserted
            phoneService.insert(it)
        }
        val supplierInserted = supplierService.insert(supplier)
        val uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(supplierInserted.id).toUri()
        return ResponseEntity.created(uri).build()
    }
}