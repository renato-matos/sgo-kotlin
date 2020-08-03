package com.sgo.sgo.data

import com.sgo.sgo.entities.*
import com.sgo.sgo.entities.domains.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PersonAddressRepository : JpaRepository<PersonAddress, Long>

interface AddressRepository: JpaRepository<Address, Long>

interface PersonRepository : JpaRepository<Person, Long> {
    fun findByDocument(document: Long) : Person?
}

interface ClientRepository : JpaRepository<Client, Long> {
    @Query("select c from Client c JOIN c.person p where p.document = ?1")
    fun findByDocument(document: Long) : Client?

    @Query("select c from Client c JOIN c.person p where p.name like ?1")
    fun findByName(name: String) : List<Client?>
}

interface SupplierRepository : JpaRepository<Supplier, Long> {
    @Query("select s from Supplier s JOIN s.person p where p.document = ?1")
    fun findByDocument(document: Long) : Supplier?

    @Query("select s from Supplier s JOIN s.person p where p.name like ?1")
    fun findByName(name: String) : List<Supplier?>
}

// Domains
interface AddressTypeRepository: JpaRepository<AddressType, Int>

interface AdministrationTypeRepository: JpaRepository<AdministrationType, Int>

interface ContractTypeRepository: JpaRepository<ContractType, Int>

interface ProjectStatusRepository: JpaRepository<ProjectStatus, Int>

interface ProjectTypeRepository: JpaRepository<ProjectType, Int>

interface PaymentMethodRepository: JpaRepository<PaymentMethod, Int>

interface PaymentStatusRepository: JpaRepository<PaymentStatus, Int>

interface PaymentTypeRepository: JpaRepository<PaymentType, Int>