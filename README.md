<img src="src/main/resources/images/LogoCartCaptain1.png" alt="CartCaptain" width="300">

# CartCaptain - Kitchen Inventory System

Kitchen inventory management tool developed by Group 9 in IDATG1005 - Software Engineering at NTNU Gjøvik.

Group Members: Theodor Sjetnan Utvik, Sigurd Riseth, Halvard Nordberg, Simon Snildal

## Project description

CartCaptain is a kitchen inventory management tool that helps users keep track of their food items
and recipes. The application allows users to add, remove, and update food items, as well as manage 
a shopping list. Users can add items to the shopping list either manually or by selecting recipes.

[//]: # (TODO: Write a short description of your project/product here.)

## Project structure

The project structure is displayed in the directory tree below.

```text
.
├── src
│   ├── main
│   │   ├── resources
│   │   │   └── // Images, csv-files, and other resources
│   │   └── java
│   │       ├── application
│   │       │   ├── Main.java
│   │       │   └── MainApp.java
│   │       ├── controllers
│   │       │   ├── FrideController.java
│   │       │   ├── FrontPageController.java
│   │       │   ├── MainController.java
│   │       │   ├── RecipeController.java
│   │       │   ├── SceneUtils.java
│   │       │   └── ShoppingListController.java
│   │       ├── entities
│   │       │   ├── FoodItem.java
│   │       │   └── Recipe.java
│   │       ├── file_handling
│   │       │   └── FileHandler.java
│   │       ├── generaltools
│   │       │   ├── InputHandler.java
│   │       │   └── Selections.java
│   │       └── registers
│   │           ├── FoodItemRegister.java
│   │           └── RecipeRegister.java
│   └── test
│       └── java
│           ├── FoodItemRegisterTest.java
│           └── RecipeRegisterTest.java
├── target
│   └── // Compiled bytecode and generated JAR files
├── .gitignore
├── pom.xml
└── README.md
```

[//]: # (TODO: Describe the structure of your project here. How have you used packages in your structure. Where are all sourcefiles stored. Where are all JUnit-test classes stored. etc.)

The src-folder is divided into two parts; main containing the main source code of the application, and test containing the test classes for the source code.

## Link to repository

[//]: # (TODO: Include a link to your repository here.)

https://gitlab.stud.idi.ntnu.no/sigurris/idatg1005_2024_gruppe09

## How to run the project

[//]: # (TODO: Describe how to run your project here. What is the main class? What is the main method?
What is the input and output of the program? What is the expected behaviour of the program?)

1. **Open the Project:**
- Ensure you have Java and a compatible IDE (like IntelliJ IDEA, Eclipse, or Visual Studio Code) and Maven installed.
- Open your IDE and import the project by selecting the root directory where the `pom.xml` file is located.

2. **Compile the Code:**
- Compile the code using Maven. This can be done in terminal or IDE.
    - Through Terminal:
```text
mvn compile
```

3. **Interact with the Program:**
- Once the program is running, you'll be prompted with a menu offering 10 choices.
- Navigate through the menu using text inputs to interact with different functionalities.

4. **Exit the Program:**
- To exit the program, choose option 0 in the menu. This will gracefully terminate the program.


## How to run the tests

To execute all tests, right-click on the 'java' folder within the 'test' directory and choose 'Run All Tests'.
Alternatively, you can run individual test classes by right-clicking on the specific test class.
If you wish to test a particular method within a test class, right-click on the desired method.

[//]: # (TODO: Describe how to run the tests here.)
