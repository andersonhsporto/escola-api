package org.sed.escolaapi.registration.web;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.sed.escolaapi.registration.domain.RegistrationCreationDTO;
import org.sed.escolaapi.registration.domain.RegistrationDTO;
import org.sed.escolaapi.registration.domain.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/matricula")
public class RegistrationController {

  private final RegistrationService registrationService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<RegistrationDTO> getRegistrations() {
    return registrationService.getRegistrations();
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public RegistrationDTO getRegistrationById(@PathVariable  Long id) throws EntityNotFoundException {
    return registrationService.getRegistrationById(id);
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public RegistrationDTO createRegistration(@RequestBody @Valid RegistrationCreationDTO registration) throws BadRequestException {
    return registrationService.createRegistration(registration);
  }

  @PatchMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public RegistrationDTO updateRegistration(@PathVariable Long id, @RequestBody @Valid RegistrationCreationDTO registration)
      throws EntityNotFoundException, BadRequestException {
    return registrationService.updateRegistration(id, registration);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteRegistration(@PathVariable Long id) throws EntityNotFoundException {
    registrationService.deleteRegistration(id);
  }

  @ExceptionHandler(BadRequestException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String handleBadRequestException(BadRequestException e) {
    log.info("Requisição inválida {}", e.getMessage());
    return e.getMessage();
  }

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String handleEntityNotFoundException(EntityNotFoundException e) {
    log.info("Entidade não encontrada {}", e.getMessage());
    return "Matricula não encontrada";
  }

}
