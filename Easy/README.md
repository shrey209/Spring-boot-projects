
# Online Compiler Project using Spring Boot

## Overview
This Spring Boot project serves as an online compiler for C and C++ programming languages. It leverages the `ProcessBuilder` library in Java to execute code files and provides a controller to receive code and input from users.

## Supported Languages
Currently, the compiler supports C and C++ languages.

## How to Use
1. Run the project.
2. Use Postman to make a POST request to `localhost:8080/run` with the following JSON payloads:

   - For C++:
     ```json
     {
       "code": "#include<iostream>\nusing namespace std;\n\nint main() {\n    cout << \"Hello, World!\";\n    return 0;\n}",
       "type": "cpp",
       "input": ""
     }
     ```

   - For C:
     ```json
     {
       "code": "#include<stdio.h>\n\nint main() {\n    int a;\n    scanf(\"%d\", &a);\n    printf(\"number is: %d\", a);\n    return 0;\n}",
       "type": "c",
       "input": "18"
     }
     ```

## Features
- Execute code for C and C++.
- Accept user input for the code.
- Receive output and error responses.

## Project Structure
The project follows a simple structure:

- **Controller:** The `RunController` handles incoming requests for code execution.
- **Service:** Code execution logic is encapsulated in the `CodeService` and language-specific services (`CppServices` and `Cservices`).
- **Models:** The `CodeDTO` model represents the data structure for input payloads.

## Usage
1. Start the Spring Boot application.
2. Use Postman or a similar tool to send requests to `localhost:8080/run`.
3. Customize the input payload according to your requirements.

This project provides a foundation for building an online compiler with expandable language support. Enhance it further by adding security features, scalability options, and support for additional languages as needed.
