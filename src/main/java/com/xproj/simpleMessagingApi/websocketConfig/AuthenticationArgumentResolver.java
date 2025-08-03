package com.xproj.simpleMessagingApi.websocketConfig;

import org.springframework.core.MethodParameter;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.core.Authentication;

public class AuthenticationArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Authentication.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, Message<?> message) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        Object user = accessor.getUser();
        if (user instanceof Authentication auth) {
            return auth;
        }
        System.out.println("[Resolver] supportsParameter for " + parameter.getParameterType());
        System.out.println("[Resolver] accessor.getUser() = " + StompHeaderAccessor.wrap(message).getUser());

        return null;
    }
}

