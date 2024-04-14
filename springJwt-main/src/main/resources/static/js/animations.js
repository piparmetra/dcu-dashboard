// Add event listener to execute when the DOM content is loaded
document.addEventListener('DOMContentLoaded', function () {
    // Select the sidebar element
    var sidebar = document.querySelector('.sidebar');
    
    // Add event listener for mouse entering the sidebar
    sidebar.addEventListener('mouseenter', function() {
        // Add the 'expanded' class to the sidebar
        this.classList.add('expanded');
    });
    
    // Add event listener for mouse leaving the sidebar
    sidebar.addEventListener('mouseleave', function() {
        // Remove the 'expanded' class from the sidebar
        this.classList.remove('expanded');
    });
});
