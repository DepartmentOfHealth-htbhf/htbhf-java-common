package uk.gov.dhsc.htbhf.requestcontext;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

import static uk.gov.dhsc.htbhf.requestcontext.RequestIdFilter.REQUEST_ID_HEADER;
import static uk.gov.dhsc.htbhf.requestcontext.RequestIdFilter.SESSION_ID_HEADER;

/**
 * Interceptor that automatically adds the request and session id to outgoing requests.
 */
@AllArgsConstructor
public class HeaderInterceptor implements ClientHttpRequestInterceptor {

    private final RequestContextHolder requestContextHolder;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        var headers = request.getHeaders();
        RequestContext requestContext = requestContextHolder.get();
        headers.add(REQUEST_ID_HEADER, requestContext.getRequestId());
        headers.add(SESSION_ID_HEADER, requestContext.getSessionId());
        return execution.execute(request, body);
    }
}
