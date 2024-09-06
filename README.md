## XMLCreditCardManager: Comprehensive Credit Card Management with Spring Boot

### Description

XMLCreditCardManager is an advanced project for handling credit card information. The project combines classic XML parsing techniques with modern Spring Boot technology to create a comprehensive solution for credit card data management. This project now includes a RESTful API, Database Integration, and uses Factory Method and Singleton Design Patterns. Developed as part of the "Corporate Applications Programming" course, this project demonstrates a high level of expertise in Java programming.

### Features

#### Legacy Features
- `CreditCard.java`: The main class that represents a credit card with attributes and validation logic.
- `DOMParser.java` and `SAXParser.java`: Classes to parse XML files containing credit card information.
- `InvalidCardException.java`: A custom exception to handle invalid credit card cases.

#### New Features
- RESTful API to interact with credit card data
- Database integration for storing and retrieving credit card information
- Factory Method and Singleton design patterns for efficient object creation and resource utilization
- Spring Boot application, built with Maven for easy dependency management
- Automated tests for added features

### How it Works

#### Using Legacy Features
1. Clone the repository to your local machine.
2. Add your XML file `credit_cards.xml` to the root directory.
3. Run `Main.java`.
4. Check the console and `output.txt` for the validation results.

#### Using New Features
1. Clone the repository.
2. Navigate to the root directory and start the Spring Boot application using `mvn spring-boot:run` or your IDE's run configuration.
3. The RESTful API is accessible at `http://localhost:8080`.
4. Use REST API endpoints to add, delete, and manage credit card information.

### Contributing

Contributions are always welcome! Feel free to fork the project, make changes, and create pull requests.

### License

MIT
