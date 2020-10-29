//import 'lazysizes';

$( document ).ready( function () {
    changeNavbarBackgroundOnTogglerClick();
    changeNavbarColorOnScroll();
    swapActiveClassInNavbar()
    getCopyrightYear();
    autoplayEmbedYoutubeVideos()
});


function changeNavbarColorOnScroll() {
    $(document).scroll(function () {
        let $nav = $("#main-navbar.fixed-top");
        //The following IFs aren't all under one single if because doing so caused issues on scrolling
        // '>' to add class on scroll, '<' to remove class on scroll
        $nav.toggleClass('scrolled navbar-light', $(this).scrollTop() > $nav.height());
        $("#main-navbar .nav-item a").toggleClass('text-dark', $(this).scrollTop() > $nav.height()); //change links color to black, this is needed because in css I have overriden styles to make links bolder and white
        $("#navbar-social-media li a i").toggleClass('navbar-social-media-black', $(this).scrollTop() > $nav.height())
        $nav.toggleClass('navbar-dark', $(this).scrollTop() < $nav.height());
    });
};

function changeNavbarBackgroundOnTogglerClick() {
    $( ".navbar-toggler" ).click(function() {
        $("#main-navbar").toggleClass("bg-dark");
        $("#navbar-contact").toggleClass("text-center");
    });
}

/* toggles the active class in the navbar's links when user navigates to a different page */
function swapActiveClassInNavbar() {
    $('#main-navbar li.active').removeClass('active');
    $('a[href="' + location.pathname + '"]').closest('li').addClass('active');
}

// Get the current year for the copyright
function getCopyrightYear() {
    $('#copyright-year').text(new Date().getFullYear());
}


function autoplayEmbedYoutubeVideos() {
    // This function help to autoplay the video only when the modal is opened (rather then on page-load)
    // and also to stop the audio once the modal is closed
    let videoSrc = $("#modal-video iframe").attr("src");

    $('#modal-video').on('show.bs.modal', function () { // on opening the modal
        // set the video to autostart
        $("#modal-video iframe").attr("src", videoSrc+"?autoplay=1");
    });

    $("#modal-video").on('hidden.bs.modal', function (e) { // on closing the modal
        // stop the video
        $("#modal-video iframe").attr("src", null);
    });
}

