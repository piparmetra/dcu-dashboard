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
    const token = localStorage.getItem('jwtToken'); 
    if (!token) {
        alert('You are not logged in');
        return;
    }

    fetch('/admin/users', {
        headers: {
            'Authorization': 'Bearer ' + token
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('You might not be authorized to view this page');
        }
        return response.text(); 
    })
    .then(html => {
        document.body.innerHTML = html;
    })
    .catch(error => {
        console.error('There was an error accessing the users page:', error);
        alert(error.message);
    });
}

function goToAddRoomPage() {
    const token = localStorage.getItem('jwtToken');
    if (!token) {
        alert('You are not logged in');
        return;
    }

    fetch('/admin/addRoom.html', {
        headers: {
            'Authorization': 'Bearer ' + token
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('You might not be authorized to view this page');
        }
        return response.text();
    })
    .then(html => {
        document.body.innerHTML = html;
    })
    .catch(error => {
        console.error('There was an error accessing the add room page:', error);
        alert(error.message);
    });
}