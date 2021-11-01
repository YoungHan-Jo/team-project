
// when document loaded
$(document).ready(function() {
	// init Navbar Dropdown Menu
	$('.dropdown-trigger').dropdown();
	
	// init Carousel
	$('.carousel.carousel-slider').carousel({
	    fullWidth: true,
	    indicators: true
	});
	
	// init sidenav
	$('.sidenav').sidenav();

	// init collapsible
	$('.collapsible').collapsible();

	// init select
	$('select').formSelect();

	// init autocomplete
	$('input.autocomplete').autocomplete({
		data: {
			"Apple": null,
			"Microsoft": null,
			"Google": 'https://placehold.it/250x250'
		},
	});
	
});

