//import 'lazysizes';

$( document ).ready( function () {
    changeNavbarColorOnScroll();
    getCopyrightYear();
});


function changeNavbarColorOnScroll() {
    $(document).scroll(function () {
        let $nav = $("#main-navbar.fixed-top");
        //The following IFs aren't all under one single if because doing so caused issues on scrolling
        // '>' to add class on scroll, '<' to remove class on scroll
        $nav.toggleClass('scrolled navbar-light', $(this).scrollTop() > $nav.height());
        $nav.toggleClass('navbar-dark', $(this).scrollTop() < $nav.height());
        $("#navbar-social-media li a i").toggleClass('navbar-social-media-black', $(this).scrollTop() > $nav.height())
    });
};


// Get the current year for the copyright
function getCopyrightYear() {
    $('#copyright-year').text(new Date().getFullYear());
}
