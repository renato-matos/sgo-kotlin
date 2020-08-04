package com.sgo.sgo.entities

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.sgo.sgo.entities.domains.PhoneType
import java.time.Instant
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotEmpty

@Entity
data class Phone(@Id
                @GeneratedValue(strategy = GenerationType.IDENTITY)
                val id : Long = 0,
                val countryCode: String,
                val ddd: Int,
                val phoneNumber: Long,
                val extension: String?,
                val contact: String?,
                val operator: String?,
                val insertedOn: Instant,
                val lastUpdate: Instant
                ) {

    @ManyToOne
    lateinit var phoneType: PhoneType

    @ManyToOne
    lateinit var person: Person

    constructor(countryCode: String,
                ddd: Int,
                phoneNumber: Long,
                extension: String?,
                contact: String?,
                operator: String?,
                phoneType: PhoneType) : this (countryCode = countryCode, ddd = ddd, phoneNumber = phoneNumber,
                                            extension = extension, contact = contact, operator = operator,
                                            insertedOn = Instant.now(), lastUpdate = Instant.now()) {
        this.phoneType = phoneType
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class PhoneInputDTO(@JsonProperty("country_code")
                      @field:NotEmpty
                      val countryCode: String,
                      @JsonProperty("ddd")
                      @field:NotEmpty
                      val ddd: Int,
                      @JsonProperty("phone_number")
                      @field:NotEmpty
                      val phoneNumber: Long,
                      @JsonProperty("extension")
                      val extension: String?,
                      @JsonProperty("contact")
                      val contact: String?,
                      @JsonProperty("operator")
                      val operator: String?,
                      @JsonProperty("phone_type")
                      @field:NotEmpty
                      val phoneType: Int)

@JsonIgnoreProperties(ignoreUnknown = true)
class PhoneOutputDTO(@JsonProperty("phone_id")
                    val phoneId: Long,
                    @JsonProperty("country_code")
                    val countryCode: String,
                    @JsonProperty("ddd")
                    val ddd: Int,
                    @JsonProperty("phone_number")
                    val phoneNumber: Long,
                    @JsonProperty("extension")
                    val extension: String?,
                    @JsonProperty("contact")
                    val contact: String?,
                    @JsonProperty("operator")
                    val operator: String?,
                    @JsonProperty("phone_type")
                    val phoneType: Int,
                    @JsonProperty("inserted_on")
                    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
                    val insertedOn: Instant,
                    @JsonProperty("last_update")
                    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
                    val lastUpdate: Instant)