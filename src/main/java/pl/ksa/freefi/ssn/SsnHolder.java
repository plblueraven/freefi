package pl.ksa.freefi.ssn;

import javax.validation.constraints.NotBlank;

record SsnHolder(@NotBlank String ssn) {

}
