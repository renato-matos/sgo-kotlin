package com.sgo.sgo.entities

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.sgo.sgo.entities.domains.AddressType
import java.time.Instant
import javax.persistence.*
import javax.validation.constraints.NotEmpty

@Entity
data class PersonAddress (@Id @GeneratedValue(strategy = GenerationType.AUTO)
                          val id : Long = 0) {

    @ManyToOne
    lateinit var person : Person

    @OneToOne
    lateinit var address: Address

    constructor(address: Address) :
            this () {
        this.address = address
    }

}


