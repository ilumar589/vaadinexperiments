package com.example.sk

import com.vaadin.flow.component.page.AppShellConfigurator
import com.vaadin.flow.theme.Theme
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import java.time.Clock

@SpringBootApplication
@Theme("default")
class Application : AppShellConfigurator {

    @Bean
    fun clock(): Clock {
        return Clock.systemDefaultZone() // You can also use Clock.systemUTC()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(Application::class.java, *args)
        }
    }
}