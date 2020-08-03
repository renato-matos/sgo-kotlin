package com.sgo.sgo.controllers

import com.sgo.sgo.entities.ClientInputDTO
import com.sgo.sgo.entities.ClientOutputDTO
import com.sgo.sgo.entities.SupplierInputDTO
import com.sgo.sgo.entities.SupplierOutputDTO
import com.sgo.sgo.services.ClientService
import com.sgo.sgo.services.PersonAddressService
import com.sgo.sgo.services.PersonService
import com.sgo.sgo.services.SupplierService
import com.sgo.sgo.utils.decodeParam
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
@RequestMapping("/suppliers")
class SupplierController {

    @Autowired
    lateinit var supplierService: SupplierService

    @Autowired
    lateinit var personService: PersonService

    @Autowired
    lateinit var personAddressService: PersonAddressService

    @GetMapping
    @Operation(summary = "List suppliers")
    fun listAll(@RequestParam(value = "name", required = false) name: String?,
                @RequestParam(value = "document", required = false) document: Long?): ResponseEntity<List<SupplierOutputDTO>> {
        val nameToBeFound: String? = if (name != null) decodeParam(name) else null
        var suppliers = supplierService.listSuppliers(document, nameToBeFound)
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
        val supplierInserted = supplierService.insert(supplier)
        val uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(supplierInserted.id).toUri()
        return ResponseEntity.created(uri).build()
    }
}