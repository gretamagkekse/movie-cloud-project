package de.tu_berlin.proscience.mca.team_yellow.team_yellow.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

// marker for swaggerUI
@SecurityScheme(type = SecuritySchemeType.HTTP, name = "BasicAuth", scheme = "basic")
public class BasicAuth {
}
