package uk.gov.dhsc.htbhf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import uk.gov.dhsc.htbhf.errorhandler.ErrorHandler;
import uk.gov.dhsc.htbhf.requestcontext.RequestContext;
import uk.gov.dhsc.htbhf.requestcontext.RequestContextConfiguration;

@Configuration
@Import(RequestContextConfiguration.class)
public class CommonRestConfiguration {

    private final RequestContext requestContext;

    public CommonRestConfiguration(RequestContext requestContext) {
        this.requestContext = requestContext;
    }

    @Bean
    public ErrorHandler errorHandler() {
        return new ErrorHandler(requestContext);
    }
}

