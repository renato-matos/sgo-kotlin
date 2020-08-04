package com.sgo.sgo.entities

import com.sgo.sgo.entities.domains.EntityType
import com.sgo.sgo.entities.domains.PersonType
import com.sun.istack.NotNull
import io.swagger.v3.oas.annotations.media.Schema
import java.time.Instant
import javax.persistence.*

@Entity
data class Person (@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
                   val id : Long,
                   @NotNull
                   val entityType: EntityType,
                   @NotNull
                   val personType: PersonType,
                   @NotNull
                   val name: String,
                   val rg: String?,
                   @NotNull
                   val document: Long,
                   val insertedOn: Instant,
                   val lastUpdate: Instant) {

    @Schema(hidden = true)
    @OneToMany(mappedBy = "person")
    lateinit var personAddresses: List<PersonAddress>

    @Schema(hidden = true)
    @OneToMany(mappedBy = "person")
    lateinit var phones: List<Phone>
}