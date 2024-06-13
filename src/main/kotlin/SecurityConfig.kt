/*package jp.ac.it_college.std.s23006.book.manager.presentation.config

import jp.ac.it_college.std.s23006.book.manager.BookManagerApplication
import jp.ac.it_college.std.s23006.book.manager.application.service.security.AuthenticationService
import jp.ac.it_college.std.s23006.book.manager.application.service.security.BookManagerUserDetailsService
import jp.ac.it_college.std.s23006.book.manager.domain.types.RoleType
import jp.ac.it_college.std.s23006.book.manager.presentation.form.SecurityHandler
import org.apache.tomcat.util.net.openssl.ciphers.Authentication
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.SecurityNamespaceHandler
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val authenticationService: AuthenticationService
) {

    @Bean
    @Order(1)
    fun configure(http: HttpSecurity): SecurityFilterChain {
        http {
            authorizeHttpRequests {
                authorize("/login", permitAll)
                authorize("/admin/**", hasAuthority(RoleType.ADMIN.toString()))
                authorize(anyRequest, authenticated)
            }
            formLogin {
                loginProcessingUrl = "/login"
                usernameParameter = "email"
                passwordParameter = "pass"
                authenticationSuccessHandler = SecurityHandler
                authenticationFailureHandler = SecurityHandler
            }
            csrf {


                disable()
            }
            exceptionHandling {

                authenticationEntryPoint = SecurityHandler
                accessDeniedHandler = SecurityHandler
            }
            cors {

            }
        }
        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = Argon2PasswordEncoder(
        16, 32, 1, 19 * 1024, 2
    )

    @Bean
    fun userDetailsService(): UserDetailsService {
        BookManagerUserDetailsService(authenticationService)

        @Bean
        fun corsConfigurationSource(): CorsConfigurationSource {
            val config = CorsConfiguration().apply {
                allowedMethods = listOf(CorsConfiguration.ALL)
                allowedHeaders = listOf(CorsConfiguration.ALL)
                allowedOrigins = listOf(
                    "http://localhost:3000"
                )

                allowCredentials = true
            }

            val source = UrlBasedCorsConfigurationSource().apply {

                registerCorsConfiguration("/**", config)
            }
        }
    }
}*/