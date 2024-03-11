package org.sed.escolaapi.registration.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RegistrationCreationDTO(

    @JsonProperty("estudante_id")
    @NotNull(message = "O id do estudante é obrigatório")
    @Positive(message = "O id do estudante deve ser um número positivo")
    Long studentId,

    @JsonProperty("curso_id")
    @NotNull(message = "O id do curso é obrigatório")
    @Positive(message = "O id do curso deve ser um número positivo")
    Long courseId
) {

}
