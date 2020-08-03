package com.sgo.sgo.entities

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.sgo.sgo.entities.domains.AddressType
import java.time.Instant
import javax.persistence.*
import javax.validation.constraints.NotEmpty

@Entity
data class Address (@Id @GeneratedValue(strategy = GenerationType.AUTO)
                    val id : Long = 0,
                    val street: String,
                    val number: String,
                    val complement: String?,
                    val neighborhood: String,
                    val city: String,
                    val state: String,
                    val country: String,
                    val zipCode: String,
                    val insertedOn: Instant,
                    val lastUpdate: Instant) {

    @ManyToOne
    lateinit var addressType: AddressType

    constructor (street: String,
                 number: String,
                 complement: String?,
                 neighborhood: String,
                 city: String,
                 state: String,
                 country: String,
                 zipCode: String,
                 addressType: AddressType) : this(street = street, number=number, complement = complement,
                                                neighborhood = neighborhood, city = city, state = state,
                                                country = country, zipCode = zipCode,
                                                insertedOn = Instant.now(), lastUpdate = Instant.now()) {
        this.addressType = addressType
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