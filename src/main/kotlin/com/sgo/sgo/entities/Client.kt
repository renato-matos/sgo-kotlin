package com.sgo.sgo.entities

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.sgo.sgo.entities.enums.EntityType
import com.sgo.sgo.entities.enums.PersonType
import java.time.Instant
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
data class Client (@Id @GeneratedValue(strategy = GenerationType.AUTO)
                   val id : Long) {
    @OneToOne
    lateinit var person: Person

}

//@JsonIgnoreProperties(ignoreUnknown = true)
class ClientInputDTO(@JsonProperty("name")
                     @field:NotBlank(message = "teste de validação do nome")
                     val name: String,
                     @JsonProperty("person_type")
                     val personType: String,
                     @JsonProperty("document")
                     val document: Long,
                     @JsonProperty("rg")
                     val rg: String?,
                     @JsonProperty("addresses")
                     val addresses: List<AddressInputDTO>
)


@JsonIgnoreProperties(ignoreUnknown = true)
class ClientOutputDTO(@JsonProperty("client_id")
                val clientId: Long,
                @JsonProperty("person_id")
                val personId: Long,
                @JsonProperty("name")
                val name: String,
                @JsonProperty("person_type")
                val personType: String,
                @JsonProperty("document")
                val document: Long,
                @JsonProperty("rg")
                val rg: String?,
                @JsonProperty("addresses")
                val addresses: List<AddressOutputDTO>,
                @JsonProperty("inserted_on")
                @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
                val insertedOn: Instant,
                @JsonProperty("last_update")
                @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
                val lastUpdate: Instant
)