**Use Maven to load this project; otherwise, the relevant dependencies cannot be introduced.**

Project Design Approach:
Project Functionality:
This project is designed to manage recipes, including adding, deleting, and modifying recipes. Additionally, it supports local data storage.

To closely simulate the actual requirements of company-level development, this project is developed following the three-tier architecture commonly used in web applications.

**Version 1:**
The program implements the most basic features of recipe management.

**Version 2:**
Added file I/O operations. Since the development specifications did not provide specific requirements for I/O handling, this project uses the popular HuTool API often used in enterprises to achieve local file reading and writing.

**Version 3:**
Added error handling mechanisms, unit testing, and restricted illegal user input using regular expressions.

**Future Versions:**
The plan includes integrating a database to further abstract and extend similar functionalities.
