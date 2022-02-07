# Kineo Employee Management System

You are developing an application to manage employees at different companies. You are required to implement the following features.

Requirements 1 and 2 are mandatory. The other requirements are in a suggested order, however you may work on them in any order you like.

1. A company may have many employees and one employee can only work for one company. Please add this relationship to the database tables.
2. Add the previous relationship to the JPA entities.

3. Create REST end-point to delete an employee by id.
4. Create REST end-point to delete a company by id. Companies can only be deleted if they no longer have employees.
5. Create a "Search" REST end-point to search employees by any of the "Employee" table fields (Id, First Name or Last Name). The result should be ordered by First Name alphabetically.
6. Create the REST end-point to create and update Employees in the system. The endpoint should support updating First Name, Last Name and Company. First Name and Last Name can contain only the following characters: A-Z, a-z, apostrophe ('), hyphen (-) and white space.
7. Extend the search end-point created in (5) to search employee by Company Name or Company Id. Update the endpoint to first group employees by company, then order by first name alphabetically.

Everything will be evaluated, not only the result.

The following database tables and test data are provided. See setup.sql.

- Company:
  - id
  - name

- Employee:
  - id
  - first_name
  - last_name
  
Initial JPA entities are provided. 
