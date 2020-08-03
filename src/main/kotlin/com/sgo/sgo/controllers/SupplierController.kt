package com.sgo.sgo.controllers

import com.sgo.sgo.entities.ClientOutputDTO
import com.sgo.sgo.entities.SupplierOutputDTO
import com.sgo.sgo.services.ClientService
import com.sgo.sgo.services.PersonAddressService
import com.sgo.sgo.services.PersonService
import com.sgo.sgo.services.SupplierService
import com.sgo.sgo.utils.decodeParam
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/suppliers")
class SupplierController {

    @Autowired
    lateinit var supplierService: SupplierService

    @GetMapping
    @Operation(summary = "List suppliers")
    fun listAll(@RequestParam(value = "name", required = false) name: String?,
                @RequestParam(value = "document", required = false) document: Long?): ResponseEntity<List<SupplierOutputDTO>> {
        val nameToBeFound: String? = if (name != null) decodeParam(name) else null
        var suppliers = supplierService.listSuppliers(document, nameToBeFound)
        return ResponseEntity.ok().body(suppliers)
    }
}