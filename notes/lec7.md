# Lec7 Spring Security

prerequisite: Authentication & Authorization

### Spring Security

- A popular security framework that secures JavaEE applications, by providing powerful, customizable security features like authentication and authorization. 
- No Shiro! (That's nice for Claud)

##### Spring Security Filters Chains

- Interception mechanism
- Many kinds of filters:
  - **SecurityContextPersistenceFilter**: Load the security context information related to this request.
  - **UsernamePasswordAuthenticationFilter**: Handle the username and password from the request.
  - **BasicAuthenticationFilter**: Handle http basic authentication.
  - **ExceptionTranslationFilter**: Handle AccessDeniedException and AuthenticationException exceptions.
  - **FilterSecurityInterceptor**: Exit of the filter chain.

### Basic Authentication

Config in pom.xml file. Then do not change anything. A basic authentication is created by lib configured in pom.xml. Username and password can be set in application.yaml file.

### UserDetailService

##### Authentication Process

User data can be obtained from database, config files and other places.

##### Two Beans: UserDetailService, PasswordEncoder

##### In-memory UserDetailsService

##### Load user from Database

Encoders and decoders can be used, too.

### JWT Authentication

##### How does the server recognize you after login?

- Server response with a session Id.
- Then, every request will take session Id in the cookie.

##### Session-based authentication

- A session is a server-side memory space to share information between serial of requests from the same client .
- Cookies are client-side files to store information of web application.
- **Drawback :**
  -  Some client (e.g. mobile application) has no cookie to hold session Id.
  - Hard to use for distributed applications
    - Single Sign-On
    - Load balance

##### Token-based authentication

- Suitable for distributed system.

##### JWT **JSON Web Token** 

- JWT is a open standard to share security information between parties.
- Each JWT contains encoded JSON objects, including a set of claims. 
- JWTs are signed using a cryptographic algorithm to ensure that the claims cannot be altered after the token is issued.

##### JWT authentication with Spring Security

- SecurityConfig
- AuthenticationController
- JwtRequestFilter

##### Configuation for JWT authentication

##### Login to generate JWT

##### Verify JWT in Filter

### Cache

##### Why cache?

- The Filter has to loadUser from database for each request,

  and it will slow down the response time of APIs.

##### Using Cache

- The users are cached, therefore repeatedly loading same user will not query from database.

##### CacheManager implementations

### Authorization

##### Role-based Access Control (RBAC)

- An access control method that assigns permissions to end-users based on their roles.

##### Simple authorization (role as an authority)

- **Limitation:** The role names are written in code, thus the application cannot do authorization based on the roles dynamically added at runtime.

##### Role-based Dynamic Authorization

##### Expression-Based Access Control