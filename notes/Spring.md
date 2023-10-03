# Lec3 Spring Core

### Introduction to Spring

##### **Why do we learn Spring?**

- Simplify development of enterprise application
- Efficiently integration with other technologies to improve the development

##### **Spring Ecosphere**

- **Spring Framework:** the core framework, and it is the basis for all other technologies.
- **Spring Boot:** Spring Boot is to simplify Spring application development.
- **Spring Cloud:** microservice framework for distributed application.

### Spring IOC

##### **Tight-coupled programming**

If the implementation class of Book-Dao changes, the business layer code needs to change as well, and after the change, it needs to be compiled and packaged.

##### **Loose-coupled programming**

- Do not dependent on concreate class, dependent on the interface
- Use "get & set" method. When some kind of function uses class instance to implement some work, do not instance class, but to leave an interface and use "set & get" to instance outside. 

##### **What is IOC?** 

- Inversion of Control 
- Object creation and initialization. The objects in the IOC container are called “Beans”.
- Dependency Injection between beans. 
- Connection between two layers.

### Hello, Spring

### Life Cycle of Beans

##### Scope of Beans

- default: singleton -- only instance one object -- high efficiency

- can be set: prototype -- instance when .getBean() invokes -- thread security

##### Instantiation with Factory

### Dependency Injection

##### **Dependency Injection with Setter**

##### Dependency Injection with Constructor

Do not make a real class as a Bean. Beans are always abstract classes.

##### **Auto-wired Injection By Type**

- Find a “bean” whose type is a derived class of BookDao to inject.

- If more than one class are matched, No-Unique-Bean-Definition-Exception is thrown.

##### **Auto-wired** **Injection By Name** 

- Find a bean with the specific name.
- If no bean is found, no injection is done.

### IOC Containers

##### **Spring IOC container classes**

- From class path.
- From other position.(always not recommended)

##### **FileSystemXmlApplicationContext**

##### **getBean**

- By name.
- By type.

### Spring Annotations

##### **Spring with annotations**

##### **@Scope, @PostConstruct and @PreDestroy**

##### @Bean

Not the same as Bean Id. Factory Method

**@Autowired**

##### **Properties Injection**

##### **Annotations**

