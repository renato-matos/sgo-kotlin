package com.sgo.sgo.data

import com.sgo.sgo.entities.Address
import com.sgo.sgo.entities.Client
import com.sgo.sgo.entities.Person
import com.sgo.sgo.entities.Supplier
import org.springframework.data.jpa.repository.JpaRepository

interface AddressRepository : JpaRepository<Address, Long>

interface PersonRepository : JpaRepository<Person, Long>

interface ClientRepository : JpaRepository<Client, Long>

interface SupplierRepository : JpaRepository<Supplier, Long>