// API Service for connecting to MySQL database
const API_BASE = '/api';

/*
async function apiRequest(path, options = {}) {
    const url = `${API_BASE}${path}`;
    const config = {
        headers: {
            'Content-Type': 'application/json',
            ...options.headers
        },
        ...options
    };

    if (options.body) {
        config.body = JSON.stringify(options.body);
    }

    try {
        const response = await fetch(url, config);
        const data = await response.json();
        return data;
    } catch (error) {
        console.error('API request failed:', error);
        throw new Error('Network error. Please check your connection.');
    }
}

*/

async function apiRequest(path, options = {}) {

    const url = `${API_BASE}${path}/${options.body.username}/${options.body.password}`;

    const data = await fetch(url)
    .then(res => res.json())
    .then(resData => resData)
    .catch(err => console.error(err));

    return data;
}

const api = {
    auth: {
        login: (username, password) => apiRequest('/auth', {
            method: 'GET',
            body: { username, password }
        }),
        register: (username, email, password, role) => apiRequest('/auth/register', {
            method: 'POST',
            body: { username, email, password, role }
        })
    }
};

window.api = api; 