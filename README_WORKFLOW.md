# Bank Loan Management System (BLMS) - Complete Workflow Guide

## Table of Contents
1. [Project Overview](#project-overview)
2. [System Architecture](#system-architecture)
3. [Technology Stack](#technology-stack)
4. [Setup and Installation](#setup-and-installation)
5. [Database Configuration](#database-configuration)
6. [Application Workflow](#application-workflow)
7. [API Endpoints](#api-endpoints)
8. [User Roles and Permissions](#user-roles-and-permissions)
9. [Frontend Workflow](#frontend-workflow)
10. [Troubleshooting](#troubleshooting)
11. [Development Workflow](#development-workflow)

## Project Overview

The Bank Loan Management System (BLMS) is a comprehensive web application that manages the entire loan lifecycle from application submission to repayment tracking. The system provides a modern, responsive interface for both administrators and customers to manage loan-related activities.

### Key Features
- **User Authentication & Authorization**: Secure login/logout with role-based access
- **Customer Management**: KYC verification and customer profile management
- **Loan Product Management**: Create and manage different loan products
- **Loan Application Processing**: Submit, review, and approve loan applications
- **Repayment Tracking**: Monitor payment schedules and status
- **Reporting & Analytics**: Generate reports and view system statistics

## System Architecture

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Frontend      │    │   Backend API   │    │   Database      │
│   (HTML/CSS/JS) │◄──►│  (Spring Boot)  │◄──►│   (H2/MySQL)    │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

### Architecture Components

1. **Frontend Layer**
   - Static HTML pages with Bootstrap 5 styling
   - Vanilla JavaScript for client-side logic
   - Responsive design for mobile and desktop

2. **Backend Layer**
   - Spring Boot REST API
   - JPA/Hibernate for data persistence
   - Spring Security for authentication
   - CORS configuration for cross-origin requests

3. **Data Layer**
   - H2 in-memory database (development)
   - MySQL database (production)
   - JPA repositories for data access

## Technology Stack

### Backend
- **Java 17+**: Core programming language
- **Spring Boot 3.3.2**: Application framework
- **Spring Data JPA**: Data persistence
- **Spring Security**: Authentication and authorization
- **Hibernate**: ORM framework
- **Maven**: Build and dependency management

### Frontend
- **HTML5**: Markup language
- **CSS3**: Styling with Bootstrap 5
- **JavaScript (ES6+)**: Client-side functionality
- **Bootstrap 5**: UI framework

### Database
- **H2 Database**: In-memory database (development)
- **MySQL 8.0+**: Production database

## Setup and Installation

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+ (for production)
- Git

### Step 1: Clone the Repository
```bash
git clone <repository-url>
cd blms-api
```

### Step 2: Database Setup

#### Option A: H2 Database (Development - Recommended)
The application is configured to use H2 in-memory database by default. No additional setup required.

#### Option B: MySQL Database (Production)
```sql
CREATE DATABASE demo_blms_db;
CREATE USER 'blms_user'@'localhost' IDENTIFIED BY 'your_password';
GRANT ALL PRIVILEGES ON demo_blms_db.* TO 'blms_user'@'localhost';
FLUSH PRIVILEGES;
```

### Step 3: Configuration
Update `src/main/resources/application.properties`:

#### For H2 Database (Default)
```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

#### For MySQL Database
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/demo_blms_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=blms_user
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### Step 4: Build and Run
```bash
# Clean and compile
mvn clean compile

# Run the application
mvn spring-boot:run
```

### Step 5: Access the Application
- **Web Interface**: http://localhost:8080
- **API Base URL**: http://localhost:8080/api
- **H2 Console**: http://localhost:8080/h2-console (if using H2)

### Step 6: Login and Test
1. Open your browser and navigate to http://localhost:8080
2. Login with default admin credentials:
   - **Username**: `admin`
   - **Password**: `admin`
3. Explore the application features:
   - Dashboard with statistics
   - Customer management
   - Loan product management
   - Loan application processing
   - Repayment tracking
   - Reports and analytics

## Database Configuration

### Default Admin User
The system automatically creates an admin user on startup:
- **Username**: `admin`
- **Password**: `admin`
- **Role**: `ADMIN`

### Database Schema

#### Users Table
```sql
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) UNIQUE NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    password VARCHAR(200) NOT NULL,
    role ENUM('ADMIN', 'CUSTOMER') NOT NULL,
    active BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL
);
```

#### Customers Table
```sql
CREATE TABLE customer (
    customer_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT UNIQUE,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(15),
    address CLOB,
    kyc_status ENUM('PENDING', 'VERIFIED') NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);
```

#### Loan Products Table
```sql
CREATE TABLE loanproduct (
    loan_product_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(50) NOT NULL,
    min_amount NUMERIC(10,2) NOT NULL,
    max_amount NUMERIC(10,2) NOT NULL,
    interest_rate NUMERIC(5,2) NOT NULL,
    tenure INTEGER NOT NULL
);
```

#### Loan Applications Table
```sql
CREATE TABLE loanapplication (
    application_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    customer_id BIGINT NOT NULL,
    loan_product_id BIGINT NOT NULL,
    loan_amount NUMERIC(10,2) NOT NULL,
    application_date DATE NOT NULL,
    approval_status ENUM('PENDING', 'APPROVED', 'REJECTED') NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
    FOREIGN KEY (loan_product_id) REFERENCES loanproduct(loan_product_id)
);
```

#### Repayments Table
```sql
CREATE TABLE repayment (
    repayment_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    application_id BIGINT NOT NULL,
    amount_due NUMERIC(10,2) NOT NULL,
    due_date DATE NOT NULL,
    payment_date DATE,
    payment_status ENUM('PENDING', 'COMPLETED') NOT NULL,
    FOREIGN KEY (application_id) REFERENCES loanapplication(application_id)
);
```

## Application Workflow

### 1. User Authentication Workflow
```
User Access → Login Page → Credentials Validation → Role-based Redirect
```

1. User navigates to http://localhost:8081
2. System checks authentication status
3. If not authenticated, redirects to login page
4. User enters credentials
5. System validates credentials against database
6. If valid, creates session and redirects based on role:
   - **Admin**: Dashboard with management tools
   - **Customer**: Customer dashboard

### 2. Customer Registration Workflow
```
Registration Form → Data Validation → User Creation → KYC Process
```

1. New user fills registration form
2. System validates input data
3. Creates user account with CUSTOMER role
4. Creates customer profile with PENDING KYC status
5. Redirects to login page

### 3. Loan Application Workflow
```
Application Form → Data Validation → Application Creation → Admin Review
```

1. Customer logs in and navigates to loan applications
2. Fills loan application form with:
   - Personal details
   - Loan amount
   - Loan product selection
   - Purpose and documentation
3. System validates application data
4. Creates loan application with PENDING status
5. Admin receives notification for review

### 4. Loan Approval Workflow
```
Admin Review → Application Assessment → Approval/Rejection → Notification
```

1. Admin logs in and views pending applications
2. Reviews application details and supporting documents
3. Makes decision (APPROVE/REJECT)
4. System updates application status
5. Customer receives notification of decision
6. If approved, repayment schedule is generated

### 5. Repayment Tracking Workflow
```
Payment Schedule → Due Date Monitoring → Payment Recording → Status Update
```

1. System generates repayment schedule for approved loans
2. Monitors due dates and sends reminders
3. Customer makes payments
4. Admin records payments in system
5. System updates repayment status
6. Generates payment receipts and reports

## API Endpoints

### Authentication Endpoints
```
POST /api/auth/register    - Register new user
POST /api/auth/login       - User login
POST /api/auth/logout      - User logout
GET  /api/auth/me          - Get current user info
```

### Customer Management
```
GET    /api/customers      - List all customers
POST   /api/customers      - Create new customer
GET    /api/customers/{id} - Get customer by ID
PUT    /api/customers/{id} - Update customer
```

### Loan Products
```
GET    /api/products       - List all loan products
POST   /api/products       - Create new loan product
GET    /api/products/{id}  - Get product by ID
PUT    /api/products/{id}  - Update product
DELETE /api/products/{id}  - Delete product
```

### Loan Applications
```
GET    /api/applications                    - List all applications
POST   /api/applications                    - Submit new application
GET    /api/applications/{id}               - Get application by ID
PUT    /api/applications/{id}/approve       - Approve application
PUT    /api/applications/{id}/reject        - Reject application
```

### Repayments
```
GET    /api/repayments     - List all repayments
POST   /api/repayments     - Record new repayment
GET    /api/repayments/{id} - Get repayment by ID
PUT    /api/repayments/{id} - Update repayment
```

### Reports
```
GET /api/reports/dashboard     - Dashboard statistics
GET /api/reports/applications  - Application reports
GET /api/reports/repayments    - Repayment reports
```

## User Roles and Permissions

### Admin Role
- **Access**: Full system access
- **Permissions**:
  - View all customers and applications
  - Approve/reject loan applications
  - Manage loan products
  - Record repayments
  - Generate reports
  - Manage system settings

### Customer Role
- **Access**: Limited to own data
- **Permissions**:
  - View own profile
  - Submit loan applications
  - View application status
  - View repayment schedule
  - Update personal information

## Frontend Workflow

### Detailed File Execution Flow

#### 1. Entry Point: index.html
```html
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="/css/style.css" rel="stylesheet">
</head>
<body>
    <!-- Loading Spinner -->
    <div id="loadingSpinner" class="loading-overlay">
        <div class="spinner-border text-primary" role="status">
            <span class="visually-hidden">Loading...</span>
        </div>
    </div>

    <!-- Main Application Container -->
    <div id="appContainer" class="min-vh-100">
        <!-- Content will be dynamically loaded here -->
    </div>

    <!-- Toast Container for Notifications -->
    <div id="toastContainer" class="toast-container position-fixed top-0 end-0 p-3"></div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    
    <!-- Application Scripts (Execution Order Matters) -->
    <script src="js/api.js"></script>           <!-- 1. API communication layer -->
    <script src="js/data.js"></script>          <!-- 2. Data management -->
    <script src="js/dashboard.js"></script>     <!-- 3. Dashboard functionality -->
    <script src="js/customers.js"></script>     <!-- 4. Customer management -->
    <script src="js/loans.js"></script>         <!-- 5. Loan product management -->
    <script src="js/applications.js"></script>  <!-- 6. Application processing -->
    <script src="js/calculator.js"></script>    <!-- 7. Loan calculator -->
    <script src="js/reports.js"></script>       <!-- 8. Reporting functionality -->
    <script src="js/main.js"></script>          <!-- 9. Main application controller -->
    <script src="js/data-sync.js"></script>     <!-- 10. Data synchronization -->
</body>
</html>
```

#### 2. Script Execution Sequence

##### Step 1: api.js (API Communication Layer)
```javascript
// Executes first - Sets up HTTP communication
const API_BASE = '/api';

// Global API request function
async function apiRequest(path, options = {}) {
    // Handles all HTTP requests to backend
    // Manages authentication headers
    // Handles error responses
}

// Exports API functions for other modules
const api = {
    auth: {
        login: (username, password) => apiRequest('/auth/login', { method: 'POST', body: { username, password } }),
        register: (username, email, password, role) => apiRequest('/auth/register', { method: 'POST', body: { username, email, password, role } }),
        logout: () => apiRequest('/auth/logout', { method: 'POST' }),
        me: () => apiRequest('/auth/me')
    },
    customers: {
        list: () => apiRequest('/customers'),
        get: (id) => apiRequest(`/customers/${id}`),
        create: (payload) => apiRequest('/customers', { method: 'POST', body: payload }),
        update: (id, payload) => apiRequest(`/customers/${id}`, { method: 'PUT', body: payload }),
        delete: (id) => apiRequest(`/customers/${id}`, { method: 'DELETE' })
    },
    loans: {
        list: () => apiRequest('/loan-products'),
        get: (id) => apiRequest(`/loan-products/${id}`),
        create: (payload) => apiRequest('/loan-products', { method: 'POST', body: payload }),
        update: (id, payload) => apiRequest(`/loan-products/${id}`, { method: 'PUT', body: payload }),
        delete: (id) => apiRequest(`/loan-products/${id}`, { method: 'DELETE' })
    },
    applications: {
        list: () => apiRequest('/applications'),
        listByCustomer: (customerId) => apiRequest(`/applications/customer/${customerId}`),
        apply: (customerId, loanProductId, loanAmount) => apiRequest(`/applications?customerId=${customerId}&loanProductId=${loanProductId}&loanAmount=${loanAmount}`, { method: 'POST' }),
        approve: (id) => apiRequest(`/applications/${id}/approve`, { method: 'PUT' }),
        reject: (id) => apiRequest(`/applications/${id}/reject`, { method: 'PUT' })
    },
    repayments: {
        list: () => apiRequest('/repayments'),
        schedule: (applicationId) => apiRequest(`/repayments/application/${applicationId}`),
        generate: (applicationId, months, monthlyAmount) => apiRequest(`/repayments/application/${applicationId}/generate?months=${months}&monthlyAmount=${monthlyAmount}`, { method: 'POST' }),
        pay: (repaymentId) => apiRequest(`/repayments/${repaymentId}/pay`, { method: 'PUT' }),
        outstanding: (applicationId) => apiRequest(`/repayments/application/${applicationId}/outstanding`)
    },
    reports: {
        summary: () => apiRequest('/reports/summary')
    }
};

window.api = api;
```

##### Step 2: data.js (Data Management)
```javascript
// Executes second - Manages application data
class DataManager {
    constructor() {
        this.customers = [];
        this.loanProducts = [];
        this.applications = [];
        this.repayments = [];
    }
    
    async loadCustomers() {
        // Calls /api/customers
        // Updates this.customers array
        // Triggers UI updates
    }
    
    async loadLoanProducts() {
        // Calls /api/products
        // Updates this.loanProducts array
        // Triggers UI updates
    }
    
    async loadApplications() {
        // Calls /api/applications
        // Updates this.applications array
        // Triggers UI updates
    }
    
    async loadRepayments() {
        // Calls /api/repayments
        // Updates this.repayments array
        // Triggers UI updates
    }
}

// Initialize data management
window.dataManager = new DataManager();
```

##### Step 3: dashboard.js (Dashboard Functionality)
```javascript
// Executes third - Dashboard-specific logic
class DashboardManager {
    constructor() {
        this.charts = {};
        this.statistics = {};
    }
    
    async loadDashboardData() {
        // Calls /api/reports/dashboard
        // Updates dashboard statistics
        // Renders charts and graphs
    }
    
    updateStatistics() {
        // Calculates real-time statistics
        // Updates dashboard counters
    }
}

// Initialize dashboard
window.dashboardManager = new DashboardManager();
```

##### Step 4: customers.js (Customer Management)
```javascript
// Executes fourth - Customer management logic
class CustomerManager {
    constructor() {
        this.currentCustomer = null;
    }
    
    async createCustomer(customerData) {
        // Calls /api/customers (POST)
        // Validates customer data
        // Updates UI with new customer
    }
    
    async updateCustomer(id, customerData) {
        // Calls /api/customers/{id} (PUT)
        // Updates customer information
        // Refreshes customer list
    }
    
    async deleteCustomer(id) {
        // Calls /api/customers/{id} (DELETE)
        // Removes customer from list
        // Updates UI
    }
}

// Initialize customer management
window.customerManager = new CustomerManager();
```

##### Step 5: loans.js (Loan Product Management)
```javascript
// Executes fifth - Loan product management
class LoanProductManager {
    constructor() {
        this.currentProduct = null;
    }
    
    async createProduct(productData) {
        // Calls /api/products (POST)
        // Validates product data
        // Updates product list
    }
    
    async updateProduct(id, productData) {
        // Calls /api/products/{id} (PUT)
        // Updates product information
        // Refreshes product list
    }
    
    async deleteProduct(id) {
        // Calls /api/products/{id} (DELETE)
        // Removes product from list
        // Updates UI
    }
}

// Initialize loan product management
window.loanProductManager = new LoanProductManager();
```

##### Step 6: applications.js (Application Processing)
```javascript
// Executes sixth - Loan application processing
class ApplicationManager {
    constructor() {
        this.currentApplication = null;
    }
    
    async submitApplication(applicationData) {
        // Calls /api/applications (POST)
        // Validates application data
        // Creates new application
        // Updates application list
    }
    
    async approveApplication(id) {
        // Calls /api/applications/{id}/approve (PUT)
        // Updates application status
        // Generates repayment schedule
        // Updates UI
    }
    
    async rejectApplication(id) {
        // Calls /api/applications/{id}/reject (PUT)
        // Updates application status
        // Sends notification
        // Updates UI
    }
}

// Initialize application management
window.applicationManager = new ApplicationManager();
```

##### Step 7: calculator.js (Loan Calculator)
```javascript
// Executes seventh - Loan calculation logic
class LoanCalculator {
    constructor() {
        this.calculationResults = {};
    }
    
    calculateEMI(principal, rate, tenure) {
        // Calculates monthly EMI
        // Returns payment schedule
        // Updates calculator UI
    }
    
    calculateInterest(principal, rate, tenure) {
        // Calculates total interest
        // Returns interest breakdown
        // Updates calculator UI
    }
}

// Initialize loan calculator
window.loanCalculator = new LoanCalculator();
```

##### Step 8: reports.js (Reporting Functionality)
```javascript
// Executes eighth - Report generation
class ReportManager {
    constructor() {
        this.reportData = {};
    }
    
    async generateApplicationReport() {
        // Calls /api/reports/applications
        // Generates application statistics
        // Renders report charts
    }
    
    async generateRepaymentReport() {
        // Calls /api/reports/repayments
        // Generates repayment statistics
        // Renders report charts
    }
    
    exportReport(format) {
        // Exports report in specified format
        // Downloads report file
    }
}

// Initialize reporting
window.reportManager = new ReportManager();
```

##### Step 9: main.js (Main Application Controller)
```javascript
// Executes ninth - Main application controller
let currentPage = 'login';
let currentUser = null;

// Initialize application when DOM is loaded
document.addEventListener('DOMContentLoaded', function() {
    console.log('DOM loaded, initializing application...');
    initializeApplication();
});

async function initializeApplication() {
    try {
        console.log('Initializing application...');
        
        // Show loading spinner
        showLoadingSpinner();
        
        // Initialize data synchronization
        await initializeDataFromDatabase();
        
        // Always show login page first
        showLoginPage();
        
        // Hide loading spinner
        hideLoadingSpinner();
        
        console.log('Application initialized successfully');
    } catch (error) {
        console.error('Error initializing application:', error);
        hideLoadingSpinner();
        showError('Failed to initialize application. Please refresh the page.');
    }
}

function showLoginPage() {
    // Renders login page HTML
    // Sets up login form listeners
    // Handles login/register navigation
}

function showMainApplication() {
    // Renders main application layout
    // Sets up navigation
    // Loads dashboard by default
}

function showPage(pageName) {
    // Handles page navigation
    // Loads appropriate page content
    // Updates navigation state
}

function logout() {
    // Clears user session
    // Returns to login page
}
```

##### Step 10: data-sync.js (Data Synchronization)
```javascript
// Executes tenth - Data synchronization
async function initializeDataFromDatabase() {
    try {
        console.log('Initializing data from database...');
        
        // Load all data in parallel
        await Promise.all([
            loadCustomers(),
            loadLoanProducts(),
            loadApplications(),
            loadRepayments()
        ]);
        
        console.log('Data initialized successfully');
    } catch (error) {
        console.error('Error loading data:', error);
        throw error;
    }
}

async function loadCustomers() {
    try {
        const customers = await api.customers.list();
        window.dataManager.customers = customers;
        console.log('Customers loaded:', customers.length);
    } catch (error) {
        console.error('Error loading customers:', error);
    }
}

async function loadLoanProducts() {
    try {
        const products = await api.loans.list();
        window.dataManager.loanProducts = products;
        console.log('Loan products loaded:', products.length);
    } catch (error) {
        console.error('Error loading loan products:', error);
    }
}

async function loadApplications() {
    try {
        const applications = await api.applications.list();
        window.dataManager.applications = applications;
        console.log('Applications loaded:', applications.length);
    } catch (error) {
        console.error('Error loading applications:', error);
    }
}

async function loadRepayments() {
    try {
        const repayments = await api.repayments.list();
        window.dataManager.repayments = repayments;
        console.log('Repayments loaded:', repayments.length);
    } catch (error) {
        console.error('Error loading repayments:', error);
    }
}
```

### Complete Execution Flow

```
1. Browser loads index.html
   ↓
2. HTML parser loads CSS and JavaScript files in order
   ↓
3. api.js executes - Sets up HTTP communication layer
   ↓
4. data.js executes - Sets up data management
   ↓
5. dashboard.js executes - Initializes dashboard functionality
   ↓
6. customers.js executes - Sets up customer management
   ↓
7. loans.js executes - Sets up loan product management
   ↓
8. applications.js executes - Sets up application processing
   ↓
9. calculator.js executes - Sets up loan calculator
   ↓
10. reports.js executes - Sets up reporting functionality
    ↓
11. main.js executes - Main application controller
    ↓
12. data-sync.js executes - Sets up data synchronization
    ↓
13. DOMContentLoaded event fires
    ↓
14. main.js.initializeApplication() executes:
    - Shows loading spinner
    - Calls data-sync.js.initializeDataFromDatabase()
    - Loads all data from API endpoints
    - Shows login page by default
    - Hides loading spinner
    ↓
15. User sees login page immediately
    ↓
16. User can login or register
    ↓
17. After successful login, main application loads
```

### Page-Specific Execution

#### Login Page (Default)
```
1. main.js.showLoginPage() executes
2. Renders login form HTML
3. Sets up form event listeners
4. User enters credentials
5. main.js.handleLogin() processes login
6. Calls api.auth.login()
7. On success, shows main application
```

#### Main Application
```
1. main.js.showMainApplication() executes
2. Renders sidebar navigation
3. Renders main content area
4. Loads dashboard by default
5. Sets up navigation event listeners
```

#### Dashboard Page
```
1. main.js.showPage('dashboard') executes
2. Calls dashboard.js.loadDashboard()
3. Renders dashboard statistics
4. Loads charts and graphs
5. Updates real-time data
```

#### Customer Management
```
1. main.js.showPage('customers') executes
2. Calls customers.js.loadCustomersPage()
3. Renders customer table
4. Sets up CRUD operations
5. Handles customer data updates
```

#### Loan Applications
```
1. main.js.showPage('applications') executes
2. Calls applications.js.loadApplicationsPage()
3. Renders application table
4. Sets up approval/rejection actions
5. Handles application status updates
```

### Data Flow Between Components

```
User Action → Event Listener → JavaScript Module → API Call → Backend → Database
    ↓
Response → JavaScript Module → Data Manager → UI Update → User Sees Changes
```

### File Connections and Dependencies

#### Direct Dependencies
```
index.html
├── api.js (No dependencies)
├── data.js (Depends on api.js)
├── dashboard.js (Depends on api.js, data.js)
├── customers.js (Depends on api.js, data.js)
├── loans.js (Depends on api.js, data.js)
├── applications.js (Depends on api.js, data.js)
├── calculator.js (No dependencies)
├── reports.js (Depends on api.js, data.js)
├── main.js (Depends on all other modules)
└── data-sync.js (Depends on api.js, data.js)
```

#### Module Communication
```
main.js (Controller)
├── Uses api.js for all HTTP requests
├── Uses data.js for data management
├── Uses dashboard.js for dashboard functionality
├── Uses customers.js for customer operations
├── Uses loans.js for loan product operations
├── Uses applications.js for application processing
├── Uses calculator.js for calculations
├── Uses reports.js for reporting
└── Uses data-sync.js for data synchronization
```

#### Event Flow
```
1. User clicks login button
   ↓
2. main.js.handleLogin() executes
   ↓
3. Calls api.auth.login()
   ↓
4. On success, main.js.showMainApplication() executes
   ↓
5. Renders main app layout
   ↓
6. Calls main.js.showPage('dashboard')
   ↓
7. Calls dashboard.js.loadDashboard()
   ↓
8. Calls api.reports.summary()
   ↓
9. Updates UI with dashboard data
```

### Error Handling Flow

```
API Error → api.js catches error → Shows error message → Retries if needed → Updates UI
    ↓
Network Error → Shows connection error → Disables features → Retries on reconnect
    ↓
Authentication Error → main.js handles → Shows login page → Clears session
```

### State Management

```
Global State (main.js)
├── currentPage: Current active page
├── currentUser: Logged in user information
└── loadingState: Application loading status

Data State (data.js)
├── customers: Array of customer data
├── loanProducts: Array of loan product data
├── applications: Array of application data
└── repayments: Array of repayment data

UI State
├── Navigation: Active page highlighting
├── Forms: Form validation states
├── Modals: Modal open/close states
└── Loading: Loading spinner states
```

## Troubleshooting

### Common Issues

#### 1. Port Already in Use (Most Common)
```
Error: Port 8080 was already in use
Solution: 
- Stop existing process: Get-Process | Where-Object {$_.ProcessName -like "*java*"} | Stop-Process -Force
- Or find specific process: netstat -ano | findstr :8080
- Kill process: taskkill /PID <process_id>
- Or change port in application.properties: server.port=8081
```

#### 2. Database Connection Issues
```
Error: Failed to connect to database
Solution:
- Check database service is running
- Verify connection credentials
- Ensure database exists
- Check firewall settings
- For H2: No setup required, runs in-memory
- For MySQL: Ensure MySQL service is running
```

#### 3. CORS Errors
```
Error: CORS policy blocked request
Solution:
- Verify CORS configuration in CorsConfig.java
- Check frontend URL matches allowed origins
- Ensure backend is running on correct port
```

#### 4. Authentication Issues
```
Error: 401 Unauthorized
Solution:
- Check user credentials (admin/admin)
- Verify session is valid
- Clear browser cache and cookies
- Check if DataLoader created admin user
- Application now shows login page by default
```

#### 5. Frontend Not Loading
```
Error: Cannot access http://localhost:8080
Solution:
- Ensure Spring Boot application is running
- Check console for startup errors
- Verify port configuration
- Check firewall settings
```

#### 6. JavaScript Errors
```
Error: api.js not found or undefined
Solution:
- Check file paths in index.html
- Ensure all JS files are in src/main/resources/static/js/
- Verify script loading order
- Check browser console for errors
```

### Step-by-Step Debugging

#### Step 1: Check Application Status
```bash
# Check if Java processes are running
Get-Process | Where-Object {$_.ProcessName -like "*java*"}

# Check if port 8080 is in use
netstat -ano | findstr :8080
```

#### Step 2: Start Application Properly
```bash
# Clean any existing processes
Get-Process | Where-Object {$_.ProcessName -like "*java*"} | Stop-Process -Force

# Clean and compile
mvn clean compile

# Start application
mvn spring-boot:run
```

#### Step 3: Verify Backend is Running
```bash
# Test API endpoint
curl http://localhost:8080/api/auth/me

# Expected: 401 Unauthorized (normal for unauthenticated request)
```

#### Step 4: Test Frontend
```bash
# Open browser to
http://localhost:8080

# Check browser console for errors
# Press F12 → Console tab
```

#### Step 5: Check Database
```bash
# For H2 Database (default)
# Access H2 console: http://localhost:8080/h2-console
# JDBC URL: jdbc:h2:mem:testdb
# Username: sa
# Password: password

# For MySQL
# Connect using MySQL client
mysql -u root -p -e "USE demo_blms_db; SHOW TABLES;"
```

### Debug Mode
Enable debug logging by adding to `application.properties`:
```properties
logging.level.com.example.blms=DEBUG
logging.level.org.springframework.web=DEBUG
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```

### Quick Fix Commands

#### Kill All Java Processes
```powershell
Get-Process | Where-Object {$_.ProcessName -like "*java*"} | Stop-Process -Force
```

#### Change Port (if 8081 is busy)
#### Change Port (if 8080 is busy)
```properties
# Edit src/main/resources/application.properties
server.port=8081
```

#### Reset Database (H2)
```properties
# Edit src/main/resources/application.properties
spring.jpa.hibernate.ddl-auto=create-drop
```

#### Clear Browser Cache
```javascript
// In browser console
localStorage.clear();
sessionStorage.clear();
location.reload();
```

### Common Error Messages and Solutions

| Error Message | Cause | Solution |
|---------------|-------|----------|
| `Port 8081 was already in use` | Another application using port | Kill Java processes or change port |
| `Failed to connect to database` | Database not running/configured | Check database setup or use H2 |
| `CORS policy blocked request` | Frontend/backend port mismatch | Verify CORS configuration |
| `401 Unauthorized` | Not logged in | Login with admin/admin |
| `404 Not Found` | API endpoint doesn't exist | Check controller mappings |
| `500 Internal Server Error` | Backend exception | Check application logs |
| `Cannot read property of undefined` | JavaScript loading order | Check script tags in HTML |

### Application Startup Checklist

✅ **Prerequisites**
- Java 17+ installed
- Maven 3.6+ installed
- Git installed

✅ **Database Setup**
- H2 (automatic) or MySQL configured
- Database credentials correct

✅ **Application Startup**
- No Java processes running on port 8081
- Maven dependencies downloaded
- Application compiles successfully
- Spring Boot starts without errors

✅ **Frontend Access**
- Backend running on http://localhost:8080
- Frontend accessible at http://localhost:8080
- No JavaScript errors in browser console

✅ **Authentication**
- Admin user created (admin/admin)
- Login page loads correctly
- Authentication works

✅ **Data Loading**
- API endpoints respond correctly
- Data loads in frontend
- No network errors

## Quick Start Guide

### For Immediate Execution (5 minutes)

1. **Prerequisites Check**
   ```bash
   java -version  # Should show Java 17+
   mvn -version   # Should show Maven 3.6+
   ```

2. **Kill Any Existing Java Processes**
   ```powershell
   Get-Process | Where-Object {$_.ProcessName -like "*java*"} | Stop-Process -Force
   ```

3. **Build and Run**
   ```bash
   mvn clean compile
   mvn spring-boot:run
   ```

4. **Access Application**
   - Open browser: http://localhost:8080
   - Login: admin/admin
   - Start exploring!

### What You'll See

✅ **Working Application Features**
- Complete loan management system
- User authentication and authorization
- Customer management with KYC status
- Loan product creation and management
- Loan application submission and approval
- Repayment tracking and scheduling
- Real-time dashboard with statistics
- Responsive web interface

✅ **Technical Stack**
- Backend: Spring Boot 3.3.2 with H2 database
- Frontend: HTML5, CSS3, Bootstrap 5, Vanilla JavaScript
- Database: H2 in-memory (no setup required)
- Port: 8080 (configurable)

### File Execution Summary

```
1. index.html loads → CSS and JavaScript files
2. api.js executes → Sets up HTTP communication
3. data.js executes → Handles data management
4. dashboard.js → Dashboard functionality
5. customers.js → Customer management
6. loans.js → Loan product management
7. applications.js → Application processing
8. calculator.js → Loan calculations
9. reports.js → Reporting functionality
10. main.js → Main application controller
11. data-sync.js → Data synchronization
12. Application initializes → Shows login page immediately
13. User logs in → Main application loads
```

### Troubleshooting Quick Fix

If you encounter issues:
1. **Port conflict**: Kill Java processes and restart
2. **Database issues**: H2 runs automatically, no setup needed
3. **Frontend errors**: Check browser console (F12)
4. **Authentication**: Use admin/admin credentials

The application is now fully functional and ready for use! 