## XML Credit Card Validator

### Description
This project contains a set of tools to validate and manage credit card data extracted from XML files. It includes two parsers—DOM and SAX—to read credit card information, validate it, and write the results to both a file and the console. The project aims to demonstrate best practices in code readability, modifiability, and reusability.

### How it Works
- `CreditCard.java`: The main class that represents a credit card with attributes and validation logic.
- `DOMParser.java` and `SAXParser.java`: Classes to parse XML files containing credit card information.
- `InvalidCardException.java`: A custom exception to handle invalid credit card cases.

### How to Use
1. Clone the repository to your local machine.
2. Add your XML file `credit_cards.xml` to the root directory.
3. Run either `DOMParser.java` or `SAXParser.java`.
4. Check the console and `output.txt` for the validation results.

### Contributing
Feel free to fork the project, make changes, and create pull requests.
