document.addEventListener('DOMContentLoaded', function () {
    var sidebar = document.querySelector('.sidebar');
    sidebar.addEventListener('mouseenter', function() {
        this.classList.add('expanded');
    });
    sidebar.addEventListener('mouseleave', function() {
        this.classList.remove('expanded');
    });
});


