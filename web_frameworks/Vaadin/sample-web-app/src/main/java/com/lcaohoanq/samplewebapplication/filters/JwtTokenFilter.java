//package com.lcaohoanq.samplewebapplication.filters;
//
//import com.lcaohoanq.samplewebapplication.components.JwtTokenUtils;
//import com.lcaohoanq.samplewebapplication.exceptions.JwtAuthenticationException;
//import com.lcaohoanq.samplewebapplication.models.User;
//import jakarta.annotation.PostConstruct;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.regex.Pattern;
//import lombok.NonNull;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.util.Pair;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class JwtTokenFilter extends OncePerRequestFilter {
//
//    @Value("${api.prefix}")
//    private String apiPrefix;
//    private final UserDetailsService userDetailsService;
//    private final JwtTokenUtils jwtTokenUtil;
//    private List<Pair<String, Pattern>> specialCasePatterns;
//
//    @PostConstruct
//    private void initializeSpecialCasePatterns() {
//        specialCasePatterns = new ArrayList<>();
//
//        // Add special case patterns
//        specialCasePatterns.add(Pair.of("GET", Pattern.compile(
//                String.format("%s/auctions/\\d+", apiPrefix))));
//        specialCasePatterns.add(Pair.of("GET", Pattern.compile(
//                String.format("%s/kois/\\d+", apiPrefix))));
//        specialCasePatterns.add(Pair.of("GET", Pattern.compile(
//                String.format("%s/auctionkois/auction/\\d+", apiPrefix))));
//        specialCasePatterns.add(Pair.of("GET", Pattern.compile(
//                String.format("%s/auctionkois/\\d+/\\d+", apiPrefix))));
//        specialCasePatterns.add(Pair.of("GET", Pattern.compile(
//                String.format("%s/bidding/\\d+", apiPrefix))));
//        specialCasePatterns.add(Pair.of("GET", Pattern.compile(
//                String.format("%s/bidding/\\d+/\\d+", apiPrefix))));
//        // Add WebSocket connection pattern
//        specialCasePatterns.add(Pair.of("POST", Pattern.compile("/app/auctionkoi/\\d+/bid")));
//        specialCasePatterns.add(Pair.of("GET", Pattern.compile(String.format("%s/auction-websocket.*", apiPrefix))));
//        //swagger-*
//
//        specialCasePatterns.add(Pair.of("GET", Pattern.compile("/swagger-resources.*")));
//        specialCasePatterns.add(Pair.of("GET", Pattern.compile("/configuration.*")));
//        specialCasePatterns.add(Pair.of("GET", Pattern.compile("/api-docs.*")));
//        specialCasePatterns.add(Pair.of("GET", Pattern.compile("/swagger-ui.*")));
//        specialCasePatterns.add(Pair.of("GET", Pattern.compile("/configuration.*")));
//        specialCasePatterns.add(Pair.of("GET", Pattern.compile("/webjars.*")));
//        specialCasePatterns.add(Pair.of("GET", Pattern.compile("/topic.*")));
//
//        //RoleController
//        specialCasePatterns.add(Pair.of("GET", Pattern.compile(String.format("%s/roles/\\d+", apiPrefix))));
//
//        //BreederController
//        specialCasePatterns.add(Pair.of("GET", Pattern.compile(String.format("%s/breeders/\\d+", apiPrefix))));
//
//        //UserController
//        specialCasePatterns.add(Pair.of("GET", Pattern.compile(String.format("%s/users/\\d+", apiPrefix))));
//        specialCasePatterns.add(Pair.of("PUT", Pattern.compile(String.format("%s/users/verify/\\d"
//                                                                                 + "+", apiPrefix))));
//        //CategoryController
//        specialCasePatterns.add(Pair.of("GET", Pattern.compile(String.format("%s/categories/\\d+", apiPrefix))));
//        // Add more special case patterns here as needed
//    }
//
//    @Override
//    protected void doFilterInternal(
//            @NonNull HttpServletRequest request,
//            @NonNull HttpServletResponse response,
//            @NonNull FilterChain filterChain)
//            throws ServletException, IOException, JwtAuthenticationException {
//        try {
//            log.debug("Request path: {}, Method: {}", request.getServletPath(),
//                    request.getMethod());
//            if (isBypassToken(request)) {
//                log.debug("Bypass token: {}", request.getRequestURI());
//                filterChain.doFilter(request, response); // enable bypass
//                return;
//            }
//            final String authHeader = request.getHeader("Authorization");
//            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
//                return;
//            }
//            final String token = authHeader.substring(7);
//            final String email = jwtTokenUtil.extractEmail(token);
//            if (email != null
//                    && SecurityContextHolder.getContext().getAuthentication() == null) {
//                User userDetails = (User) userDetailsService.loadUserByUsername(email);
//                if (jwtTokenUtil.validateToken(token, userDetails)) {
//                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//                            userDetails,
//                            null,
//                            userDetails.getAuthorities());
//                    authenticationToken.setDetails(
//                            new WebAuthenticationDetailsSource().buildDetails(request));
//                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//                }
//            }
//            filterChain.doFilter(request, response); // enable bypass
//        } catch (Exception e) {
//            log.error("Error in JwtTokenFilter: ", e);
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
//        }
//
//    }
//
//    private boolean isBypassToken(@NonNull HttpServletRequest request) {
//        final List<Pair<String, String>> bypassTokens = Arrays.<Pair<String, String>>asList(
//                // RoleController
//                Pair.of(String.format("%s/roles", apiPrefix), "GET"),
//                Pair.of(String.format("%s/roles", apiPrefix), "POST"),
//                Pair.of(String.format("%s/roles", apiPrefix), "PUT"),
//                Pair.of(String.format("%s/roles", apiPrefix), "DELETE"),
//
//                // KoiController
//                Pair.of(String.format("%s/kois", apiPrefix), "GET"),
//                Pair.of(String.format("%s/kois/get-kois-owner-by-keyword-not-auth", apiPrefix), "GET"),
//                Pair.of(String.format("%s/kois/count-by-gender", apiPrefix), "GET"),
//                Pair.of(String.format("%s/kois/count-by-status", apiPrefix), "GET"),
//
//                Pair.of(String.format("%s/breeders", apiPrefix), "GET"),
//
//                // KoiImageController
//                Pair.of(String.format("%s/koiimage", apiPrefix), "GET"),
//
//                // CategoryController
//                Pair.of(String.format("%s/categories", apiPrefix), "GET"),
//
//                // ManagerController
//                Pair.of(String.format("%s/managers", apiPrefix), "GET"),
//                Pair.of(String.format("%s/managers", apiPrefix), "POST"),
//                Pair.of(String.format("%s/managers", apiPrefix), "PUT"),
//                Pair.of(String.format("%s/managers", apiPrefix), "DELETE"),
//
//                // UserController
//                Pair.of(String.format("%s/users/register", apiPrefix), "POST"),
//                Pair.of(String.format("%s/users/login", apiPrefix), "POST"),
//
//                // Verify OTP
//
//                Pair.of(String.format("%s/users/verify", apiPrefix), "POST"),
//
//                Pair.of(String.format("%s/oauth2", apiPrefix), "POST"),
//                Pair.of(String.format("%s/oauth2/google-client-id", apiPrefix), "GET"),
//
//                // Otp
//                Pair.of(String.format("%s/otp/send", apiPrefix), "GET"),
//                Pair.of(String.format("%s/otp/verify", apiPrefix), "POST"),
//
//                Pair.of(String.format("%s/auction-participant", apiPrefix), "GET"),
//                Pair.of(String.format("%s/auction-participant", apiPrefix), "POST"),
//                Pair.of(String.format("%s/auction-participant", apiPrefix), "PUT"),
//                Pair.of(String.format("%s/auction-participant", apiPrefix), "DELETE"),
//
//                // Send mail (considering to choose which method, does this email need a table
//                // to
//                // store)
//                Pair.of(String.format("%s/auction-mail", apiPrefix), "GET"),
//                Pair.of(String.format("%s/auction-mail", apiPrefix), "POST"),
//                Pair.of(String.format("%s/auction-mail", apiPrefix), "PUT"),
//                Pair.of(String.format("%s/auction-mail", apiPrefix), "DELETE"),
//
//                // Order required to check token
//                Pair.of(String.format("%s/orders", apiPrefix), "GET"),
//                Pair.of(String.format("%s/orders", apiPrefix), "POST"),
//                Pair.of(String.format("%s/orders", apiPrefix), "PUT"),
//                Pair.of(String.format("%s/orders", apiPrefix), "DELETE"),
//
//                // OrderDetail
//                Pair.of(String.format("%s/orders_details", apiPrefix), "GET"),
//                Pair.of(String.format("%s/orders_details", apiPrefix), "POST"),
//                Pair.of(String.format("%s/orders_details", apiPrefix), "PUT"),
//                Pair.of(String.format("%s/orders_details", apiPrefix), "DELETE"),
//
//                // Swagger
//                Pair.of("/api-docs", "GET"),
//                Pair.of("/swagger-resources", "GET"),
//                Pair.of("/configuration/ui", "GET"),
//                Pair.of("/configuration/security", "GET"),
//                Pair.of("/swagger-ui", "GET"),
//                Pair.of("/swagger-ui.html", "GET"),
//                Pair.of("/swagger-ui/index.html", "GET"),
//
//                // Actuator
//                Pair.of(String.format("%s/healthcheck/health", apiPrefix), "GET"),
//
//                Pair.of(String.format("%s/actuator", apiPrefix), "GET"),
//                Pair.of(String.format("%s/actuator/prometheus", apiPrefix), "GET"),
//                Pair.of(String.format("%s/actuator/info", apiPrefix), "GET"),
//                Pair.of(String.format("%s/actuator/health", apiPrefix), "GET"),
//
//                // Prometheus
//                Pair.of("/actuator/prometheus", "GET"),
//
//                // Auction
//                Pair.of(String.format("%s/auctions", apiPrefix), "GET"),
//
//                // AuctionKoiController
//                Pair.of(String.format("%s/auctionkois", apiPrefix), "GET"),
//                Pair.of(String.format("%s/auctionkois", apiPrefix), "PUT"),
//                Pair.of(String.format("%s/auctionkois/get-kois-by-keyword", apiPrefix), "GET"),
//                Pair.of(String.format("%s/auctions/get-auctions-by-keyword", apiPrefix), "GET"),
//                Pair.of(String.format("%s/auctions/get-auctions-upcoming", apiPrefix), "GET"),
//                Pair.of(String.format("%s/auctions/count-by-auction-status", apiPrefix), "GET"),
//
//                //auctionkois//count-by-bid-method
//                Pair.of(String.format("%s/auctionkois/count-by-bid-method", apiPrefix), "GET"),
//
//                // AuctionKoiDetail
//                Pair.of(String.format("%s/bidding", apiPrefix), "GET"),
//                Pair.of(String.format("%s/bidding", apiPrefix), "POST"),
//                Pair.of(String.format("%s/bidding", apiPrefix), "PUT"),
//                Pair.of(String.format("%s/bidding", apiPrefix), "DELETE"),
//
//                // AuctionKoiWebsocket
//                Pair.of(String.format("%s/auction-websocket", apiPrefix), "GET"),
//                Pair.of(String.format("%s/auction-websocket", apiPrefix), "POST"),
//                Pair.of(String.format("%s/auction-websocket/info", apiPrefix), "GET"),
//
//                Pair.of("/topic", "GET"),
//                Pair.of("/topic", "POST"),
//
//                // VNPay
//                Pair.of(String.format("%s/payments", apiPrefix), "POST"),
//                Pair.of(String.format("%s/payments/vnpay/payment_return", apiPrefix), "GET"),
//
//                // ForgotPassword
//                Pair.of(String.format("%s/forgot-password", apiPrefix), "GET"),
//                Pair.of(String.format("%s/forgot-password", apiPrefix), "PUT"),
//
//                // Feedback
//                Pair.of(String.format("%s/feedback", apiPrefix), "GET"),
//                Pair.of(String.format("%s/feedback", apiPrefix), "POST"),
//                Pair.of(String.format("%s/feedback", apiPrefix), "PUT"),
//                Pair.of(String.format("%s/feedback", apiPrefix), "DELETE")
//
//
//        // Pair.of(String.format("%s/products/test/view", apiPrefix), "GET")
//        );
//
//        String servletPath = request.getServletPath();
//        String method = request.getMethod();
//
//        log.info("Request path: {}, Method: {}", servletPath, method);
//
//        // Check against the special case patterns first
//        for (Pair<String, Pattern> specialCase : specialCasePatterns) {
//            String specialCaseMethod = specialCase.getFirst();
//            Pattern specialCasePattern = specialCase.getSecond();
//            if (method.equals(specialCaseMethod) && specialCasePattern.matcher(servletPath)
//                    .matches()) {
//                log.debug("Bypassing token check for special case: {}", servletPath);
//                return true;
//            }
//        }
//
//        // Then check against the existing bypass rules
//        for (Pair<String, String> bypassToken : bypassTokens) {
//            String bypassPath = bypassToken.getFirst();
//            String bypassMethod = bypassToken.getSecond();
//            if (servletPath.equals(bypassPath) && method.equals(bypassMethod)) {
//                log.debug("Bypassing token check for path: {}", servletPath);
//                return true;
//            }
//        }
//        return false;
//    }
//}
