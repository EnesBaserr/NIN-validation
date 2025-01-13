# Identity Validation

A Java library for validating Turkish identity numbers. This library provides a custom annotation, `@ValidIdentityNumber`, that can be used to validate identity numbers for Turkish citizens.

---

## Features

- Validate Turkish identity numbers based on format and checksum algorithm.
- Simple integration with Java Bean Validation (Jakarta Validation API).
- Configurable for additional countries in the future.

---

## Installation

### Add Dependency
Add the following to your project's `pom.xml` file:

```xml
<dependency>
    <groupId>org.identity</groupId>
    <artifactId>identity-validation</artifactId>
    <version>1.0.0</version>
</dependency>
```
Since this package is hosted on GitHub Packages, add the following repository to your pom.xml
    
```xml
<repositories>
    <repository>
        <id>github</id>
        <url>https://maven.pkg.github.com/EnesBaserr/NIN-validation</url>
    </repository>
</repositories>
```

## How to Use 
1. Add the Annotation
   Apply the @ValidIdentityNumber annotation to any field or parameter that requires validation.

Example:
```java
import org.identity.validation.ValidIdentityNumber;
import org.identity.validation.countries.Country;

public class User {

    @ValidIdentityNumber(country = Country.TURKEY)
    private String identityNumber;

    // Getters and setters
    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }
}
```
## Customization
### Default Messages
The default error messages are defined in messages.properties. You can override these by providing your own message bundle or specifying a custom message in the annotation.
```java
@ValidIdentityNumber(message = "Invalid ID format for Turkey")
private String identityNumber;
```
### Custom Country
The library currently supports only Turkey. However, you can add support for additional countries by implementing the Country interface and providing the necessary validation logic.
```java
@ValidIdentityNumber(country = Country.TURKEY)
private String identityNumber;
```
## How it Works
The IdentityNumberValidator performs the following checks:

1. Validates that the identity number is exactly 11 digits long.
2. Computes the checksum using the Turkish identity number algorithm:
3. Sum of odd and even digits.
4. Validation of 10th and 11th digits based on rules.
5. Returns an error message if any validation step fails.

## Contributing
Contributing
We welcome contributions to enhance this library! To contribute:

- Fork the repository.
- Create a feature branch.
- Commit your changes.
- Submit a pull request.
## License

This project is licensed under the Apache License 2.0.

You may use, copy, modify, and distribute this software in source and binary forms, with or without modification, provided that you meet the conditions stated in the Apache License 2.0. A copy of the license is included in the [LICENSE](LICENSE) file.

For more details, see the [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).
