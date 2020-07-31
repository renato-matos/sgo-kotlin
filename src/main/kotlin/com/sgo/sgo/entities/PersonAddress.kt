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
                          val id : Long = 0,
                          val insertedOn: Instant,
                          val lastUpdate: Instant) {

    @ManyToOne
    lateinit var person : Person

    @OneToOne
    lateinit var address: Address

    constructor(insertedOn: Instant, lastUpdate: Instant, address: Address) :
            this (insertedOn = insertedOn, lastUpdate = lastUpdate) {
        this.address = address
    }

}

@JsonIgnoreProperties(ignoreUnknown = true)
class AddressInputDTO(@JsonProperty("address_type")
                      @field:NotEmpty
                      val addressType: Int,
                      @JsonProperty("street")
                      @field:NotEmpty
                      val street: String,
                      @JsonProperty("number")
                      @field:NotEmpty
                      val number: String,
                      @JsonProperty("complement")
                      val complement: String?,
                      @JsonProperty("neighborhood")
                      @field:NotEmpty
                      val neighborhood: String,
                      @JsonProperty("city")
                      @field:NotEmpty
                      val city: String,
                      @JsonProperty("state")
                      @field:NotEmpty
                      val state: String,
                      @JsonProperty("country")
                      @field:NotEmpty
                      val country: String,
                      @JsonProperty("zip_code")
                      @field:NotEmpty
                      val zipCode: String)

@JsonIgnoreProperties(ignoreUnknown = true)
class AddressOutputDTO(@JsonProperty("address_id")
                      val addressId: Long,
                      @JsonProperty("address_type")
                      val addressType: Int,
                      @JsonProperty("street")
                      val street: String,
                      @JsonProperty("number")
                      val number: String,
                      @JsonProperty("complement")
                      val complement: String?,
                      @JsonProperty("neighborhood")
                      val neighborhood: String,
                      @JsonProperty("city")
                      val city: String,
                      @JsonProperty("state")
                      val state: String,
                      @JsonProperty("country")
                      val country: String,
                      @JsonProperty("zip_code")
                      val zipCode: String,
                      @JsonProperty("inserted_on")
                      @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
                      val insertedOn: Instant,
                      @JsonProperty("last_update")
                      @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
                      val lastUpdate: Instant)
