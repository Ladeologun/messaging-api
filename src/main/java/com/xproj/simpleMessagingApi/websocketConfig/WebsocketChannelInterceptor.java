package com.xproj.simpleMessagingApi.websocketConfig;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WebsocketChannelInterceptor implements ChannelInterceptor {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {


        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        StompCommand command = accessor.getCommand();

        System.out.println("===== STOMP frame: " + command + " =====");
        System.out.println("Native headers: " + accessor.toNativeHeaderMap());

        if (command == StompCommand.CONNECT || command == StompCommand.SEND || command == StompCommand.SUBSCRIBE){
            String authToken = accessor.getFirstNativeHeader("Authorization");
            System.out.println("------ " + authToken + " ------");

            if (authToken != null && authToken.startsWith("Bearer ")) {
                String jwt = authToken.substring(7);
                String username = jwtService.extractUsername(jwt);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                boolean isTokenValid = jwtService.isTokenValid(jwt,userDetails);
                if (isTokenValid){
                    Authentication authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    accessor.setUser(authentication);
                    System.out.println(">>> [Interceptor] set user: " + accessor.getUser() + " on " + command);
                    return message;
                }
            }
            // Token is missing or invalid
            throw new MessagingException("Unauthorized WebSocket request: invalid or missing token");
            // OR: return null; // silently drop the message
        }

        return message;
    }


//    @Override
//    public Message<?> preSend(Message<?> message, MessageChannel channel) {
//        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
//        StompCommand command = accessor.getCommand();
//
//        String authToken = accessor.getFirstNativeHeader("Authorization");
//        System.out.println("===== STOMP frame: " + command + " =====");
//        System.out.println("Native headers: " + accessor.toNativeHeaderMap());
//        System.out.println("Extracted Authorization header: " + authToken);
//
//        if (command == StompCommand.CONNECT) {
//            if (authToken != null && authToken.startsWith("Bearer ")) {
//                String jwt = authToken.substring(7);
//                String username = jwtService.extractUsername(jwt);
//                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//                if (jwtService.isTokenValid(jwt, userDetails)) {
//                    Authentication authentication =
//                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                    SecurityContextHolder.getContext().setAuthentication(authentication);
//                    accessor.setUser(authentication);
//                    accessor.getSessionAttributes().put("user", authentication);
//                    System.out.println("Authenticated on CONNECT: " + authentication.getName());
//                    return message;
//                }
//            }
//            throw new MessagingException("Unauthorized WebSocket request: invalid or missing token on CONNECT");
//        }
//
//        if (command == StompCommand.SEND || command == StompCommand.SUBSCRIBE) {
//            // Try fresh header-based validation first
//            if (authToken != null && authToken.startsWith("Bearer ")) {
//                String jwt = authToken.substring(7);
//                String username = jwtService.extractUsername(jwt);
//                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//                if (jwtService.isTokenValid(jwt, userDetails)) {
//                    Authentication authentication =
//                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                    SecurityContextHolder.getContext().setAuthentication(authentication);
//                    accessor.setUser(authentication);
//                    System.out.println("Re-authenticated from header on " + command + ": " + authentication.getName());
//                    return message;
//                }
//            }
//            // Fallback to stored session auth
//            Object saved = accessor.getSessionAttributes().get("user");
//            if (saved instanceof Authentication auth) {
//                SecurityContextHolder.getContext().setAuthentication(auth);
//                accessor.setUser(auth);
//                System.out.println("Restored user from session on " + command + ": " + auth.getName());
//                return message;
//            }
//            throw new MessagingException("Unauthorized WebSocket request: no valid auth for " + command);
//        }
//
//        return message;
//    }


}
