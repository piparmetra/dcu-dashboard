  document.addEventListener('DOMContentLoaded', function() {
    updateLoginButton();
});

function updateLoginButton() {
    const token = localStorage.getItem('jwtToken'); // Use 'jwtToken' to match the login script
    const loginButton = document.querySelector('.floating-login-button');

    if (token) {
        loginButton.textContent = 'Logout';
        loginButton.href = '#'; // Prevent default link behavior
        loginButton.onclick = function() {
            localStorage.removeItem('jwtToken'); // Remove the stored token
            alert('You have been logged out.');
            window.location.href = '/connect/login.html'; // Redirect to login page
        };
    } else {
        loginButton.textContent = 'Login';
        loginButton.href = '/connect/login.html';
        loginButton.onclick = null; // Remove any previously assigned event
    }
}

  
function goToUsersPage() {
    const token = localStorage.getItem('jwtToken'); // Again, ensure this matches the login script
    if (!token) {
        alert('You are not logged in');
        return;
    }

    fetch('/admin/users', {
        headers: {
            'Authorization': 'Bearer ' + token // Properly formatted authorization header
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('You might not be authorized to view this page');
        }
        return response.text(); // Assuming the server responds with HTML content
    })
    .then(html => {
        document.body.innerHTML = html; // Warning: this will replace the entire body's content
    })
    .catch(error => {
        console.error('There was an error accessing the users page:', error);
        alert(error.message);
    });
}
