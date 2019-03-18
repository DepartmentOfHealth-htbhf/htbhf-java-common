package uk.gov.dhsc.htbhf.errorhandler;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.ControllerAdvice;
import uk.gov.dhsc.htbhf.requestcontext.RequestContext;
import uk.gov.dhsc.htbhf.requestcontext.RequestContextConfiguration;

@Configuration
@Import(RequestContextConfiguration.class)
@ConditionalOnMissingBean(annotation = ControllerAdvice.class)
public class ErrorHandlerConfiguration {

    private final RequestContext requestContext;

    public ErrorHandlerConfiguration(RequestContext requestContext) {
        this.requestContext = requestContext;
    }

    @Bean
    public ErrorHandler errorHandler() {
        return new ErrorHandler(requestContext);
    }
}

