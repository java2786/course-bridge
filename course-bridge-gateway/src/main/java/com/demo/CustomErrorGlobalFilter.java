// package com.demo;

// import org.reactivestreams.Publisher;
// import org.springframework.cloud.gateway.filter.GlobalFilter;
// import org.springframework.cloud.gateway.filter.factory.rewrite.ModifyResponseBodyGatewayFilterFactory;
// import org.springframework.core.Ordered;
// import org.springframework.core.io.buffer.DataBuffer;
// import org.springframework.core.io.buffer.DataBufferUtils;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;
// import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
// import org.springframework.stereotype.Component;
// import org.springframework.web.server.ServerWebExchange;
// import reactor.core.publisher.Flux;
// import reactor.core.publisher.Mono;

// import java.nio.charset.StandardCharsets;

// @Component
// public class CustomErrorGlobalFilter implements GlobalFilter, Ordered {

//     @Override
//     public Mono<Void> filter(ServerWebExchange exchange, org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {

//         ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(exchange.getResponse()) {

//             @Override
//             public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
//                 if (getStatusCode() != null &&
//                         getStatusCode().is5xxServerError() || getStatusCode().is4xxClientError()) {

//                     Flux<DataBuffer> buffer = Flux.from(body)
//                         .map(dataBuffer -> {
//                             byte[] content = new byte[dataBuffer.readableByteCount()];
//                             dataBuffer.read(content);
//                             DataBufferUtils.release(dataBuffer);

//                             String originalBody = new String(content, StandardCharsets.UTF_8);
//                             String customResponse = "{\"message\":\"Handled by Gateway\", \"details\":" + originalBody + "}";

//                             byte[] newContent = customResponse.getBytes(StandardCharsets.UTF_8);
//                             return exchange.getResponse()
//                                     .bufferFactory()
//                                     .wrap(newContent);
//                         });

//                     getHeaders().setContentType(MediaType.APPLICATION_JSON);
//                     return super.writeWith(buffer);
//                 }

//                 return super.writeWith(body);
//             }
//         };

//         return chain.filter(exchange.mutate().response(decoratedResponse).build());
//     }

//     @Override
//     public int getOrder() {
//         return -2; // Execute before NettyWriteResponseFilter
//     }
// }


package com.demo;

import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * Global filter in Spring Cloud Gateway to intercept all responses
 * and customize error responses (4xx and 5xx) from downstream services.
 */
@Component
public class CustomErrorGlobalFilter implements GlobalFilter, Ordered {

    /**
     * Main filtering logic applied to every incoming request/response.
     * It wraps the response so it can be intercepted and modified.
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {

        // Wrap the original server response to intercept its body
        ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(exchange.getResponse()) {

            /**
             * Intercepts the response body before it is written to the client.
             * This allows reading and modifying error responses.
             */
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {

                // Check if the response status is 4xx or 5xx
                if (getStatusCode() != null &&
                        getStatusCode().is5xxServerError() || getStatusCode().is4xxClientError()) {

                    // Read and modify the body content
                    Flux<DataBuffer> buffer = Flux.from(body)
                        .map(dataBuffer -> {
                            // Read bytes from the original response
                            byte[] content = new byte[dataBuffer.readableByteCount()];
                            dataBuffer.read(content);

                            // Release memory after reading
                            DataBufferUtils.release(dataBuffer);

                            // Convert byte array to original JSON string
                            String originalBody = new String(content, StandardCharsets.UTF_8);

                            // Create a custom JSON response using the original body
                            String customResponse = "{\"message\":\"Handled by Gateway\", \"details\":" + originalBody + "}";

                            // Convert the new JSON string back to byte buffer
                            byte[] newContent = customResponse.getBytes(StandardCharsets.UTF_8);
                            return exchange.getResponse()
                                    .bufferFactory()
                                    .wrap(newContent);
                        });

                    // Set content type of the modified response
                    getHeaders().setContentType(MediaType.APPLICATION_JSON);

                    // Write the modified response to the client
                    return super.writeWith(buffer);
                }

                // If not an error response, pass through the original response
                return super.writeWith(body);
            }
        };

        // Replace the response in the exchange with the decorated one
        return chain.filter(exchange.mutate().response(decoratedResponse).build());
    }

    /**
     * Controls the order of this filter in the filter chain.
     * -2 ensures it runs before the default NettyWriteResponseFilter.
     */
    @Override
    public int getOrder() {
        return -2;
    }
}
