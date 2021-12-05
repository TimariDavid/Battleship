package hu.nye.progtech.configuration;

import hu.nye.progtech.service.game.Game;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Játék elindításának a konfigurációja.
 */
@Configuration
public class ApplicationConfiguration {

    @Bean(initMethod = "start")
    public Game torpedo() {
        return new Game();
    }

}
