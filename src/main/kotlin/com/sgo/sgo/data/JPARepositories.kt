package com.sgo.sgo.data

import com.sgo.sgo.entities.Address
import com.sgo.sgo.entities.Client
import com.sgo.sgo.entities.Person
import com.sgo.sgo.entities.Supplier
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface AddressRepository : JpaRepository<Address, Long>

interface PersonRepository : JpaRepository<Person, Long> {
    fun findByDocument(document: Long) : Person?
}

interface ClientRepository : JpaRepository<Client, Long> {
    @Query("select c from Client c JOIN c.person p where p.document = ?1")
    fun findByDocument(document: Long) : Client?

    @Query("select c from Client c JOIN c.person p where p.name = ?1")
    fun findByName(name: String) : List<Client?>
}

interface SupplierRepository : JpaRepository<Supplier, Long>