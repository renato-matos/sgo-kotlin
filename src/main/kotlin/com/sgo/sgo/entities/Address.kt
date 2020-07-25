package com.sgo.sgo.entities

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.sgo.sgo.entities.enums.AddressType
import io.swagger.v3.oas.annotations.media.Schema
import java.time.Instant
import javax.persistence.*

@Entity
data class Address (@Id @GeneratedValue(strategy = GenerationType.AUTO)
                val id : Long,
                val addressType: AddressType,
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
    lateinit var person : Person

}

@JsonIgnoreProperties(ignoreUnknown = true)
class AddressInputDTO(@JsonProperty("address_type")
                      val addressType: String,
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
                      val zipCode: String)

@JsonIgnoreProperties(ignoreUnknown = true)
class AddressOutputDTO(@JsonProperty("address_id")
                      val addressId: Long,
                      @JsonProperty("address_type")
                      val addressType: String,
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
