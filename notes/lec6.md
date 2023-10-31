# Lec6 Spring AOP

### Aspect Oriented Programming

##### Separation of concerns

- A systematic thinking to solving complex problems
- SOC in software design
  - Modularization
  - Component-based design
  - Multi-layers architecture
  - Single Responsibility Principle

##### **Cross-cutting concerns** 

- The feature that spans multiple modules, and cannot be effectively separated using traditional OO methods
  - Logging and Tracing
  - Transaction Management
  - Security
  - Caching
  - Error Handling
  - Monitoring
  - Custom Business Rules

##### **Aspect Oriented Programming**

- The Cross-cutting concerns in multiple modules are designed as **aspects**，and implemented as **advices**.
- The advices are dynamically **weaved** into the modules when it is needed.

##### **Weaving in AOP**

- Weaving: Create a proxy object, which intercepts calls to  the joint-points of the target object, and executes advice codes before/after the calling.
- Weaving Types: 
  - **Compile-time** weaving, load-time weaving and runtime weaving.
  - **Spring AOP** weaves aspects at **runtime**.

### Hello, Spring AOP

- Create an aspect. Pay attention to --> **Annotation**

### How does Spring AOP work?

##### **Dynamic proxy in Spring AOP**

- JDK Proxy
- CGLib Proxy

##### **JDK Dynamic Proxy** 

##### **Bean-Post-Processor of** **Spring** 

### Pointcuts

##### Pointcuts

- A pointcut specifies the joint points (methods) to be weaved.
- Pointcut expressions
  - execution (matches methods)
  - within （matches classes）
  - this (matches classes)
  - target (matches classes)
  - args (matches arguments)
  - bean(matches beans) 
  - @annotation (matches methods), @target(matches classes), @args

### Advices

##### Advice annotations

- @Before
- @After
- @AfterReturnning
- @Around
- @AfterThrowing

### Global Exception Handling

##### Exceptions thrown by the controller

##### Handling Exceptions of controllers

##### Do we need Common returning class？

- The front-end distinguishes between correct and error messages by HTTP status codes, and handle them differently. 
- In principle, correct and error messages do not need to be in a uniform format.