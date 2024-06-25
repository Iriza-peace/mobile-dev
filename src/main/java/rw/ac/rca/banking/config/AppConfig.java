package rw.ac.rca.banking.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rw.ac.rca.banking.models.User;

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Example: Custom mapping configuration
        // modelMapper.addMappings(new BookMap());
        modelMapper.addMappings(new PropertyMap<User, User>() {
            @Override
            protected void configure() {
                skip(destination.getId()); // Exclude mapping for the 'id' field
            }
        });

        return modelMapper;
    }



}
