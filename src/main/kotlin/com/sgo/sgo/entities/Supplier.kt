package com.sgo.sgo.entities

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.sgo.sgo.entities.enums.EntityType
import com.sgo.sgo.entities.enums.PersonType
import java.time.Instant
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotEmpty

@Entity
data class Supplier (@Id @GeneratedValue(strategy = GenerationType.AUTO)
                     val id : Long,
                     val activitySegment: String,
                     val contact: String,
                     val ccm: String?,
                     val ie: String?,
                     val email: String?,
                     val site: String?,
                     val comments: String?) {

    @OneToOne
    lateinit var person: Person

}

@JsonIgnoreProperties(ignoreUnknown = true)
class SupplierInputDTO(@JsonProperty("name")
                        @NotEmpty
                        val name: String,
                        @JsonProperty("person_type")
                        @NotEmpty
                        val personType: String,
                        @JsonProperty("document")
                        @NotEmpty
                        val document: Long,
                        @JsonProperty("rg")
                        val rg: String?,
                        @JsonProperty("activity_segment")
                        @NotEmpty
                        val activeSegment: String,
                        @JsonProperty("contact")
                        @NotEmpty
                        val contact: String,
                        @JsonProperty("ccm")
                        val ccm: String?,
                        @JsonProperty("ie")
                        val ie: String?,
                        @JsonProperty("email")
                        val email: String?,
                        @JsonProperty("site")
                        val site: String?,
                        @JsonProperty("comments")
                        val comments: String?,
                        @JsonProperty("addresses")
                        @NotEmpty
                        val addresses: List<Address>
)

@JsonIgnoreProperties(ignoreUnknown = true)
class SupplierOutputDTO(@JsonProperty("supplier_id")
                val supplierId: Long,
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
                @JsonProperty("activity_segment")
                val activeSegment: String,
                @JsonProperty("contact")
                val contact: String,
                @JsonProperty("ccm")
                val ccm: String?,
                @JsonProperty("ie")
                val ie: String?,
                @JsonProperty("email")
                val email: String?,
                @JsonProperty("site")
                val site: String?,
                @JsonProperty("comments")
                val comments: String?,
                @JsonProperty("addresses")
                val addresses: List<Address>,
                @JsonProperty("inserted_on")
                @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
                val insertedOn: Instant,
                @JsonProperty("last_update")
                @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
                val lastUpdate: Instant
)