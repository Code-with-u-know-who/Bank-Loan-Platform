# BLMS Backend REST API Documentation

This document outlines the available REST API endpoints for the BLMS (Bank Loan Management System) backend.

### **Base URL**
`http://localhost:8080` (or your configured server URL)

---
## AuthenticateController

### **Base Path:** `/api/auth`

* **Method:** `GET`
* **Endpoint:** `/api/auth/{username}/{pass}`
* **Description:** Authenticates a customer using a username and password.
* **Path Parameters:**
    * `username` (String): The customer's username.
    * `pass` (String): The customer's password.
* **Response:**
    * `200 OK`: Returns the `Customer` object upon successful authentication.
    * `400 Bad Request`: Returns `null` if the username or password is not found.

---
## CustomerController

### **Base Path:** `/api/customer`

* **Method:** `POST`
* **Endpoint:** `/api/customer`
* **Description:** Creates and saves a new customer record.
* **Request Body:** `Customer` object.
* **Response:** `200 OK` with the created `Customer` object.

* **Method:** `PUT`
* **Endpoint:** `/api/customer`
* **Description:** Updates an existing customer record.
* **Request Body:** `Customer` object.
* **Response:** `200 OK` with the updated `Customer` object.

* **Method:** `DELETE`
* **Endpoint:** `/api/customer/{id}`
* **Description:** Deletes a customer record by ID.
* **Path Parameter:** `id` (Integer).
* **Response:** `200 OK` with the deleted `Customer` object.

* **Method:** `GET`
* **Endpoint:** `/api/customer/search?query={query}`
* **Description:** Searches for a customer by email, ID, or name.
* **Query Parameter:** `query` (String).
* **Response:** `200 OK` with the found `Customer` object or `404 Not Found` if the customer is not found.

* **Method:** `GET`
* **Endpoint:** `/api/customer/kyc/verify/{id}`
* **Description:** Approves the KYC (Know Your Customer) status for a customer.
* **Path Parameter:** `id` (Integer).
* **Response:** `200 OK` with the updated `Customer` object.

* **Method:** `GET`
* **Endpoint:** `/api/customer/kyc/reset/{id}`
* **Description:** Resets or rejects the KYC status for a customer.
* **Path Parameter:** `id` (Integer).
* **Response:** `200 OK` with the updated `Customer` object.

---
## PasswordController

### **Base Path:** `/api/password`

* **Method:** `POST`
* **Endpoint:** `/api/password`
* **Description:** Creates and saves a new password.
* **Request Body:** `Password` object.
* **Response:** `200 OK` with the created `Password` object.

* **Method:** `GET`
* **Endpoint:** `/api/password/{id}`
* **Description:** Retrieves a password record by ID.
* **Path Parameter:** `id` (Integer).
* **Response:** `200 OK` with the found `Password` object.

* **Method:** `PUT`
* **Endpoint:** `/api/password`
* **Description:** Updates an existing password record.
* **Request Body:** `Password` object.
* **Response:** `200 OK` with the updated `Password` object.

* **Method:** `DELETE`
* **Endpoint:** `/api/password/{id}`
* **Description:** Deletes a password record by ID.
* **Path Parameter:** `id` (Integer).
* **Response:** `200 OK` with the deleted `Password` object.

---
## LoanApplicationController

### **Base Path:** `/api/loanApp`

* **Method:** `POST`
* **Endpoint:** `/api/loanApp`
* **Description:** Creates and saves a new loan application.
* **Request Body:** `LoanApplication` object.
* **Response:** `200 OK` with the created `LoanApplication` object.

* **Method:** `GET`
* **Endpoint:** `/api/loanApp/{id}`
* **Description:** Retrieves a loan application by ID.
* **Path Parameter:** `id` (Integer).
* **Response:** `200 OK` with the found `LoanApplication` object.

* **Method:** `DELETE`
* **Endpoint:** `/api/loanApp/{id}`
* **Description:** Deletes a loan application by ID.
* **Path Parameter:** `id` (Integer).
* **Response:** `200 OK` with the deleted `LoanApplication` object.

---
## LoanProductController

### **Base Path:** `/api/loanProduct`

* **Method:** `POST`
* **Endpoint:** `/api/loanProduct`
* **Description:** Creates and saves a new loan product.
* **Request Body:** `LoanProduct` object.
* **Response:** `200 OK` with the created `LoanProduct` object.

* **Method:** `GET`
* **Endpoint:** `/api/loanProduct/{id}`
* **Description:** Retrieves a loan product by ID.
* **Path Parameter:** `id` (Integer).
* **Response:** `200 OK` with the found `LoanProduct` object.

* **Method:** `DELETE`
* **Endpoint:** `/api/loanProduct/{id}`
* **Description:** Deletes a loan product by ID.
* **Path Parameter:** `id` (Integer).
* **Response:** `200 OK` with the deleted `LoanProduct` object.

---
## RepaymentController

### **Base Path:** `/api/repayment`

* **Method:** `POST`
* **Endpoint:** `/api/repayment`
* **Description:** Creates and saves a new repayment record.
* **Request Body:** `Repayment` object.
* **Response:** `200 OK` with the created `Repayment` object.

* **Method:** `GET`
* **Endpoint:** `/api/repayment/{id}`
* **Description:** Retrieves a repayment record by ID.
* **Path Parameter:** `id` (Integer).
* **Response:** `200 OK` with the found `Repayment` object.

* **Method:** `DELETE`
* **Endpoint:** `/api/repayment/{id}`
* **Description:** Deletes a repayment record by ID.
* **Path Parameter:** `id` (Integer).
* **Response:** `200 OK` with the deleted `Repayment` object.