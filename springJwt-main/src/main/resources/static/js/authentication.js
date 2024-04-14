// Execute when the DOM content is loaded
document.addEventListener('DOMContentLoaded', function() {
    // Call the updateLoginButton function
    updateLoginButton();
});

// Function to update the login button based on token presence
function updateLoginButton() {
    // Get the JWT token from localStorage
    const token = localStorage.getItem('jwtToken');
    // Select the login button element
    const loginButton = document.querySelector('.floating-login-button');

    // Check if token is present
    if (token) {
        // Update button text and behavior for logout
        loginButton.textContent = 'Logout';
        loginButton.href = '#'; // Prevent default link behavior
        loginButton.onclick = function() {
            localStorage.removeItem('jwtToken'); // Remove token from storage
            alert('You have been logged out.');
            window.location.href = '/connect/login.html'; // Redirect to login page
        };
    } else {
        // Update button text and behavior for login
        loginButton.textContent = 'Login';
        loginButton.href = '/connect/login.html';
        loginButton.onclick = null; // Remove any previously assigned event
    }
}

// Function to navigate to users page
function goToUsersPage() {
    // Get the JWT token from localStorage
    const token = localStorage.getItem('jwtToken');
    // Check if token is present
    if (!token) {
        alert('You are not logged in');
        return;
    }

    // Fetch users page with authorization header
    fetch('/admin/users', {
        headers: {
            'Authorization': 'Bearer ' + token
        }
    })
    .then(response => {
        // Check for response status
        if (!response.ok) {
            throw new Error('You might not be authorized to view this page');
        }
        return response.text(); // Return HTML content
    })
    .then(html => {
        // Replace body content with fetched HTML
        document.body.innerHTML = html;
        updateLoginButton(); // Update login button
    })
    .catch(error => {
        console.error('There was an error accessing the users page:', error);
        alert(error.message);
    });
}

// Function to navigate to add room page
function goToAddRoomPage() {
    // Get the JWT token from localStorage
    const token = localStorage.getItem('jwtToken');
    // Check if token is present
    if (!token) {
        alert('You are not logged in');
        return;
    }

    // Fetch add room page with authorization header
    fetch('/admin/addRoom.html', {
        headers: {
            'Authorization': 'Bearer ' + token
        }
    })
    .then(response => {
        // Check for response status
        if (!response.ok) {
            throw new Error('You might not be authorized to view this page');
        }
        return response.text(); // Return HTML content
    })
    .then(html => {
        // Replace body content with fetched HTML
        document.body.innerHTML = html;
        updateLoginButton(); // Update login button
    })
    .catch(error => {
        console.error('There was an error accessing the add room page:', error);
        alert(error.message);
    });
}

// Function to navigate to edit room page
function goToEditRoomPage(dataset) {
    // Get the JWT token from localStorage
    const token = localStorage.getItem('jwtToken');
    // Check if token is present
    if (!token) {
        alert('You are not logged in');
        return;
    }

    // Extract room ID from dataset
    const roomId = dataset.roomId;

    // Fetch edit room page with room ID and authorization header
    fetch(`/admin/editRoom/${roomId}`, {
        headers: {
            'Authorization': 'Bearer ' + token
        }
    })
    .then(response => {
        // Check for response status
        if (!response.ok) {
            throw new Error('You might not be authorized to view this page');
        }
        return response.text(); // Return HTML content
    })
    .then(html => {
        // Replace body content with fetched HTML
        document.body.innerHTML = html;
        updateLoginButton(); // Update login button
    })
    .catch(error => {
        console.error('There was an error accessing the edit room page:', error);
        alert(error.message);
    });
}
