# Steps to Implement a JWT Filter

1. We create a class `JwtFilter` or something similar, which will implement the `OncePerRequestFilter` interface.
2. Since we implemented this interface, we need to override the method `doFilterInternal`.
3. This method will perform the below steps starting from step 4.
4. We expect `HttpServletRequest`, `HttpServletResponse`, and `FilterChain` in the `doFilterInternal` method.
5. Using `HttpServletRequest`, we fetch the **Authorization** header from the request, like this:
   ```java
   request.getHeader("Authorization")
   ```  
6. Check if the header is **not null** and starts with `"Bearer "`. If valid, we extract the token from the header like this:
   ```java
   authorizationHeader.substring(7)
   ```  
7. Once the token is fetched, we check if it is empty or not. If it's not empty, proceed to the next step.
8. Extract the username from the token using the `JwtUtils` class.
9. Using the fetched username, load the `UserDetails` from `UserDetailsServiceImpl`.
10. If the `UserDetails` is successfully loaded, validate the token using the `JwtUtils` method. This method will take the loaded `UserDetails` and the token as parameters to validate the token.
11. If the token is valid, create an object of `UsernamePasswordAuthenticationToken` and pass these to its constructor:
- `UserDetails`
- `null`
- `userDetails.getAuthorities()`

Then, set this authentication object in the `SecurityContextHolder` like this:
```java
SecurityContextHolder.getContext().setAuthentication(authenticationToken);
```  
12. Finally, call the `doFilter` method with the `HttpServletRequest`, `HttpServletResponse`, and `FilterChain` that were passed to the `doFilterInternal` method.
