// Main Application Controller - Frontend Only
let currentPage = 'login';
let currentUser = null;

// Define logout function immediately to avoid reference errors
function logout() {
    console.log('Logging out...');
    currentUser = null;
    currentPage = 'login';
    showLoginPage();
}

// Make logout function globally accessible immediately
window.logout = logout;

// Initialize application when DOM is loaded
document.addEventListener('DOMContentLoaded', function() {
    console.log('DOM loaded, initializing frontend application...');
    initializeApplication();
});

async function initializeApplication() {
    try {
        console.log('Initializing frontend application...');
        
        // Show loading spinner
        showLoadingSpinner();
        
        // Show login page immediately (no backend calls)
        showLoginPage();
        
        // Hide loading spinner
        hideLoadingSpinner();
        
        console.log('Frontend application initialized successfully');
    } catch (error) {
        console.error('Error initializing application:', error);
        hideLoadingSpinner();
        showError('Failed to initialize application. Please refresh the page.');
    }
}

function showLoadingSpinner() {
    const spinner = document.getElementById('loadingSpinner');
    if (spinner) {
        spinner.style.display = 'flex';
    }
}

function hideLoadingSpinner() {
    const spinner = document.getElementById('loadingSpinner');
    if (spinner) {
        spinner.style.display = 'none';
    }
}

function showError(message) {
    const container = document.getElementById('appContainer');
    if (container) {
        container.innerHTML = `
            <div class="alert alert-danger m-3" role="alert">
                <i class="fas fa-exclamation-triangle me-2"></i>
                ${message}
                <button type="button" class="btn btn-sm btn-outline-danger ms-3" onclick="location.reload()">
                    <i class="fas fa-redo me-1"></i>Retry
                </button>
            </div>
        `;
    }
}

function showLoginPage() {
    console.log('Showing login page...');
    const container = document.getElementById('appContainer');
    if (container) {
        container.innerHTML = `
            <div class="row h-100">
                <div class="col-lg-6 d-none d-lg-flex bg-primary text-white flex-column justify-content-center align-items-center">
                    <div class="text-center">
                        <i class="fas fa-university fa-5x mb-4"></i>
                        <h1 class="display-4 fw-bold mb-3">SecureBank</h1>
                        <p class="lead">Professional Loan Management System</p>
                        <div class="row mt-5">
                            <div class="col-4">
                                <div class="feature-box">
                                    <i class="fas fa-lock fa-2x mb-2"></i>
                                    <p class="fw-bold">Secure</p>
                                    <small>Data encrypted</small>
                                </div>
                            </div>
                            <div class="col-4">
                                <div class="feature-box">
                                    <i class="fas fa-cogs fa-2x mb-2"></i>
                                    <p class="fw-bold">Efficient</p>
                                    <small>Streamlined workflows</small>
                                </div>
                            </div>
                            <div class="col-4">
                                <div class="feature-box">
                                    <i class="fas fa-chart-line fa-2x mb-2"></i>
                                    <p class="fw-bold">Insightful</p>
                                    <small>Powerful analytics</small>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6 d-flex justify-content-center align-items-center bg-light">
                    <div class="card p-4 shadow-lg w-100" style="max-width: 450px;">
                        <div class="card-body">
                            <h3 class="card-title text-center mb-4">Welcome Back</h3>
                            <p class="text-center text-muted">Please sign in to your account</p>
                            <div id="authAlerts"></div>
                            <form id="loginForm">
                                <div class="mb-3">
                                    <label for="username" class="form-label">Username</label>
                                    <div class="input-group">
                                        <span class="input-group-text"><i class="fas fa-user"></i></span>
                                        <input type="text" class="form-control" id="username" placeholder="Enter your username" required>
                                    </div>
                                </div>
                                <div class="mb-3">
                                    <label for="password" class="form-label">Password</label>
                                    <div class="input-group">
                                        <span class="input-group-text"><i class="fas fa-lock"></i></span>
                                        <input type="password" class="form-control" id="password" placeholder="Enter your password" required>
                                        <button type="button" class="btn btn-outline-secondary" id="togglePassword">
                                            <i class="fas fa-eye-slash"></i>
                                        </button>
                                    </div>
                                </div>
                                <div class="d-grid mb-3">
                                    <button type="submit" class="btn btn-primary btn-block">Sign In</button>
                                </div>
                            </form>
                            <div class="text-center">
                                <p class="mb-0">
                                    Don't have an account?
                                    <a href="/register.html" id="showRegisterBtn">Sign Up</a>
                                </p>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        `;
        
        // Setup login form listeners
        // Setup login form listeners
        setupLoginListeners();
        
        // Setup register button listener
        const showRegisterBtn = document.getElementById('showRegisterBtn');
        if (showRegisterBtn) {
            showRegisterBtn.onclick = () => {
                showRegisterPage();
            };
        }
    }
}

function showRegisterPage() {
    console.log('Showing register page...');
    const container = document.getElementById('appContainer');
    if (container) {
        container.innerHTML = `
            <div class="row h-100">
                <div class="col-lg-6 d-none d-lg-flex bg-primary text-white flex-column justify-content-center align-items-center">
                    <div class="text-center">
                        <i class="fas fa-university fa-5x mb-4"></i>
                        <h1 class="display-4 fw-bold mb-3">SecureBank</h1>
                        <p class="lead">Professional Loan Management System</p>
                        <div class="row mt-5">
                            <div class="col-4">
                                <div class="feature-box">
                                    <i class="fas fa-lock fa-2x mb-2"></i>
                                    <p class="fw-bold">Secure</p>
                                    <small>Data encrypted</small>
                                </div>
                            </div>
                            <div class="col-4">
                                <div class="feature-box">
                                    <i class="fas fa-cogs fa-2x mb-2"></i>
                                    <p class="fw-bold">Efficient</p>
                                    <small>Streamlined workflows</small>
                                </div>
                            </div>
                            <div class="col-4">
                                <div class="feature-box">
                                    <i class="fas fa-chart-line fa-2x mb-2"></i>
                                    <p class="fw-bold">Insightful</p>
                                    <small>Powerful analytics</small>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6 d-flex justify-content-center align-items-center bg-light">
                    <div class="card p-4 shadow-lg w-100" style="max-width: 450px;">
                        <div class="card-body">
                            <h3 class="card-title text-center mb-4">Create Account</h3>
                            <p class="text-center text-muted">Join our loan management system</p>
                            <div id="authAlerts"></div>
                            <form id="registerForm">
                                <div class="mb-3">
                                    <label for="regUsername" class="form-label">Username</label>
                                    <div class="input-group">
                                        <span class="input-group-text"><i class="fas fa-user"></i></span>
                                        <input type="text" class="form-control" id="regUsername" placeholder="Choose a username" required>
                                    </div>
                                </div>
                                <div class="mb-3">
                                    <label for="regEmail" class="form-label">Email</label>
                                    <div class="input-group">
                                        <span class="input-group-text"><i class="fas fa-envelope"></i></span>
                                        <input type="email" class="form-control" id="regEmail" placeholder="Enter your email" required>
                                    </div>
                                </div>
                                <div class="mb-3">
                                    <label for="regPassword" class="form-label">Password</label>
                                    <div class="input-group">
                                        <span class="input-group-text"><i class="fas fa-lock"></i></span>
                                        <input type="password" class="form-control" id="regPassword" placeholder="Choose a password" required>
                                        <button type="button" class="btn btn-outline-secondary" id="toggleRegPassword">
                                            <i class="fas fa-eye-slash"></i>
                                        </button>
                                    </div>
                                </div>
                                <div class="mb-3">
                                    <label for="regRole" class="form-label">Role</label>
                                    <div class="input-group">
                                        <span class="input-group-text"><i class="fas fa-user-tag"></i></span>
                                        <select class="form-control" id="regRole" required>
                                            <option value="">Select role</option>
                                            <option value="CUSTOMER">Customer</option>
                                            <option value="ADMIN">Admin</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="d-grid mb-3">
                                    <button type="submit" class="btn btn-primary btn-block">Create Account</button>
                                </div>
                            </form>
                            <div class="text-center">
                                <p class="mb-0">
                                    Already have an account?
                                    <a href="#" id="showLoginBtn">Sign In</a>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        `;
        
        // Setup register form listeners
        setupRegisterListeners();
        
        // Setup login button listener
        const showLoginBtn = document.getElementById('showLoginBtn');
        if (showLoginBtn) {
            showLoginBtn.onclick = () => {
                showLoginPage();
            };
        }
    }
}

function setupLoginListeners() {
    const loginForm = document.getElementById('loginForm');
    const togglePassword = document.getElementById('togglePassword');
    
    if (loginForm) {
        loginForm.onsubmit = async (e) => {
            e.preventDefault();
            await handleLogin();
        };
    }
    
    if (togglePassword) {
        togglePassword.onclick = () => {
            const passwordInput = document.getElementById('password');
            const icon = togglePassword.querySelector('i');
            
            if (passwordInput.type === 'password') {
                passwordInput.type = 'text';
                icon.classList.remove('fa-eye-slash');
                icon.classList.add('fa-eye');
            } else {
                passwordInput.type = 'password';
                icon.classList.remove('fa-eye');
                icon.classList.add('fa-eye-slash');
            }
        };
    }
}

function setupRegisterListeners() {
    const registerForm = document.getElementById('registerForm');
    const toggleRegPassword = document.getElementById('toggleRegPassword');
    
    if (registerForm) {
        registerForm.onsubmit = async (e) => {
            e.preventDefault();
            await handleRegister();
        };
    }
    
    if (toggleRegPassword) {
        toggleRegPassword.onclick = () => {
            const passwordInput = document.getElementById('regPassword');
            const icon = toggleRegPassword.querySelector('i');
            
            if (passwordInput.type === 'password') {
                passwordInput.type = 'text';
                icon.classList.remove('fa-eye-slash');
                icon.classList.add('fa-eye');
            } else {
                passwordInput.type = 'password';
                icon.classList.remove('fa-eye');
                icon.classList.add('fa-eye-slash');
            }
        };
    }
}

async function handleLogin() {
    const username = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    
    if (!username || !password) {
        showAlert('Please fill in all fields.', 'danger');
        return;
    }
    
    try {
        showLoadingSpinner();

        const data = await api.auth.login(username, password);

        if (data !== null) {
            currentUser = data;

            console.log(currentUser);

            showAlert('Login successful!', 'success');
            
            // Show main application
            showMainApplication();
        } else {
            showAlert(response.message || 'Login failed. Please check your credentials.', 'danger');
        }
    } catch (error) {
        console.error('Login error:', error);
        showAlert('Login failed. Please try again.', 'danger');
    } finally {
        hideLoadingSpinner();
    }
}

async function handleRegister() {
    const username = document.getElementById('regUsername').value;
    const email = document.getElementById('regEmail').value;
    const password = document.getElementById('regPassword').value;
    const role = document.getElementById('regRole').value;
    
    if (!username || !email || !password || !role) {
        showAlert('Please fill in all fields.', 'danger');
        return;
    }
    
    try {
        showLoadingSpinner();
        
        const response = await api.auth.register(username, email, password, role);
        
        if (response.success) {
            showAlert('Registration successful! Please login.', 'success');
            showLoginPage();
        } else {
            showAlert(response.message || 'Registration failed. Please try again.', 'danger');
        }
    } catch (error) {
        console.error('Registration error:', error);
        showAlert('Registration failed. Please try again.', 'danger');
    } finally {
        hideLoadingSpinner();
    }
}

function showMainApplication() {
    console.log('Showing main application...');
    const container = document.getElementById('mainApp');

    console.log(container);

    if (container) {

        
        // Load dashboard by default

        showPage('dashboard');
    }
}

function showPage(pageName) {
    console.log('Showing page:', pageName);
    const contentArea = document.getElementById('contentArea');
    if (!contentArea) return;
    
    currentPage = pageName;
    updateNavigation();
    
    switch (pageName) {
        case 'dashboard':
            loadDashboard(contentArea);
            break;
        case 'customers':
            loadCustomersPage(contentArea);
            break;
        case 'loans':
            loadLoansPage(contentArea);
            break;
        case 'applications':
            loadApplicationsPage(contentArea);
            break;
        case 'calculator':
            loadCalculatorPage(contentArea);
            break;
        case 'reports':
            loadReportsPage(contentArea);
            break;
        default:
            contentArea.innerHTML = '<div class="alert alert-warning">Page not found.</div>';
    }
}

function updateNavigation() {
    const navLinks = document.querySelectorAll('.sidebar-nav .nav-link');
    navLinks.forEach(link => {
        const linkPage = link.getAttribute('onclick')?.match(/'([^']*)'/)?.[1];
        if (linkPage === currentPage) {
            link.classList.add('active');
        } else {
            link.classList.remove('active');
        }
    });
}

function showAlert(message, type = 'info') {
    const alertsContainer = document.getElementById('authAlerts');
    if (alertsContainer) {
        const alertId = 'alert-' + Date.now();
        alertsContainer.innerHTML = `
            <div id="${alertId}" class="alert alert-${type} alert-dismissible fade show" role="alert">
                ${message}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        `;
        
        // Auto-dismiss after 5 seconds
        setTimeout(() => {
            const alert = document.getElementById(alertId);
            if (alert) {
                alert.remove();
            }
        }, 5000);
    }
}

// Utility functions
function formatNumber(num) {
    return new Intl.NumberFormat('en-IN').format(num);
}

function formatCurrency(amount) {
    return new Intl.NumberFormat('en-IN', {
        style: 'currency',
        currency: 'INR'
    }).format(amount);
}

function formatDate(dateString) {
    if (!dateString) return 'N/A';
    return new Date(dateString).toLocaleDateString('en-IN');
}

function formatDateTime(dateString) {
    if (!dateString) return 'N/A';
    return new Date(dateString).toLocaleString('en-IN');
}

function calculateAge(dateOfBirth) {
    if (!dateOfBirth) return 0;
    const today = new Date();
    const birthDate = new Date(dateOfBirth);
    let age = today.getFullYear() - birthDate.getFullYear();
    const monthDiff = today.getMonth() - birthDate.getMonth();

    if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < birthDate.getDate())) {
        age--;
    }

    return age;
}

function validateEmail(email) {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
}

function validatePhone(phone) {
    const phoneRegex = /^[\+]?[1-9][\d]{0,15}$/;
    return phoneRegex.test(phone.replace(/[\s\-\(\)]/g, ''));
}

function generateRandomId() {
    return Math.random().toString(36).substr(2, 9);
}

function debounce(func, wait) {
    let timeout;
    return function executedFunction(...args) {
        const later = () => {
            clearTimeout(timeout);
            func(...args);
        };
        clearTimeout(timeout);
        timeout = setTimeout(later, wait);
    };
}

// Loading state management
function showLoading(container) {
    if (container) {
        container.innerHTML = `
            <div class="text-center py-5">
                <div class="spinner-border text-primary" role="status">
                    <span class="visually-hidden">Loading...</span>
                </div>
                <p class="mt-3 text-muted">Loading...</p>
            </div>
        `;
    }
}

function hideLoading() {
    // Loading is hidden when content is loaded
}

// Error handling
function showError(container, message) {
    if (container) {
        container.innerHTML = `
            <div class="alert alert-danger" role="alert">
                <i class="fas fa-exclamation-triangle me-2"></i>
                ${message}
                <button type="button" class="btn btn-sm btn-outline-danger ms-3" onclick="location.reload()">
                    <i class="fas fa-redo me-1"></i>Retry
                </button>
            </div>
        `;
    }
}

// Export utility functions
window.utils = {
    formatNumber,
    formatCurrency,
    formatDate,
    formatDateTime,
    calculateAge,
    validateEmail,
    validatePhone,
    generateRandomId,
    debounce,
    showLoading,
    hideLoading,
    showError
}; 