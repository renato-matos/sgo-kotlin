package com.sgo.sgo.entities

import com.sgo.sgo.entities.enums.EntityType
import com.sgo.sgo.entities.enums.PersonType
import com.sun.istack.NotNull
import io.swagger.v3.oas.annotations.media.Schema
import java.time.Instant
import javax.persistence.*

@Entity
data class Person (@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
                   open val id : Long,
                   @NotNull
                   open val entityType: EntityType,
                   @NotNull
                   open val personType: PersonType,
                   @NotNull
                   open val name: String,
                   open val rg: String?,
                   @NotNull
                   open val document: Long,
                   open val insertedOn: Instant,
                   open val lastUpdate: Instant) {

    @Schema(hidden = true)
    @OneToMany(mappedBy = "person")
    open lateinit var addresses: List<Address>

}