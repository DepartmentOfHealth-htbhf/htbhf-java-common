package uk.gov.dhsc.htbhf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import uk.gov.dhsc.htbhf.errorhandler.ErrorHandler;
import uk.gov.dhsc.htbhf.requestcontext.RequestContextConfiguration;
import uk.gov.dhsc.htbhf.requestcontext.RequestContextHolder;

@Configuration
@Import(RequestContextConfiguration.class)
public class CommonRestConfiguration {

    private final RequestContextHolder requestContextHolder;

    public CommonRestConfiguration(RequestContextHolder requestContextHolder) {
        this.requestContextHolder = requestContextHolder;
    }

    @Bean
    public ErrorHandler errorHandler() {
        return new ErrorHandler(requestContextHolder);
    }
}

