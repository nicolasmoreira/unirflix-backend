package com.unir.rents.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ReturnRequest {

	//Validacion con Jakarta Validation API
	//Info de todas las validaciones disponibles out-of-the-box: https://jakarta.ee/specifications/bean-validation/3.0/jakarta-bean-validation-spec-3.0.html#builtinconstraints
	//Customizacion de mensajes: https://www.baeldung.com/spring-validation-message-interpolation
	//Customizacion de validaciones(1): https://www.baeldung.com/javax-validation-method-constraints
	//Customizacion de validaciones (2): https://medium-parser-seven.vercel.app/?url=https://medium.com/thedevproject/unlock-the-power-of-jakarta-validation-with-spring-boot-boost-your-java-development-skills-c2ea676f1157
	@NotNull(message = "`movie` cannot be null")
	private Long RentId;

}
