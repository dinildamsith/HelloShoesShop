function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
    document.getElementById("main").style.marginLeft = "250px";
    document.body.style.backgroundColor = "white";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
    document.getElementById("main").style.marginLeft= "0";
    document.body.style.backgroundColor = "white";
}

window.onload = function() {
    openNav(); // Call the openNav function when the window is loaded
};
document.addEventListener("DOMContentLoaded", function() {
    openNav(); // Call the openNav function when the DOM content is loaded
});


window.addEventListener('DOMContentLoaded', function() {
    if (749<innerWidth) {
        closeNav();
    }
});

window.addEventListener('DOMContentLoaded', function() {
    // Check window width on page load
    if (748 < window.innerWidth) {
        openNav();
    }

    // Add event listener for window resize
    window.addEventListener('resize', function() {
        // Check window width when window is resized
        if (748 < window.innerWidth) {
            openNav();
        } else {
            // Close navigation or do something else if needed
        }
    });
});
