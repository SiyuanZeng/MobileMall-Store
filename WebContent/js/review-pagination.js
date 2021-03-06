﻿function showReviewsInPages () {
	/* The following code is executed once the DOM is loaded */
	// Calling the jQuery plugin and splitting the
	// #holder UL into pages of 3 LIs each:
	
	$('#holder').sweetPages({perPage:20});
	
	// The default behaviour of the plugin is to insert the
	// page links in the ul, but we need them in the main container:
	
	var controls = $('.swControls').detach();
	controls.appendTo('#main');
	
}

(function($){
// Creating the sweetPages jQuery plugin and most importantly, add to the prototype, so that every element can use it. 
$.fn.sweetPages = function(opts){

	// If no options were passed, create an empty opts object
	if(!opts) opts = {};

	var resultsPerPage = opts.perPage || 20;

	// The plugin works best for unordered lists,
	// although OLs would do just as well:
	var ul = this;
	var li = ul.find('li');

	li.each(function(){
		// Calculating the height of each li element,
		// and storing it with the data method:

		var el = $(this);
		el.data('height',el.outerHeight(true));
	});

	// Calculating the total number of pages:
	var pagesNumber = Math.ceil(li.length/resultsPerPage);

	// If the pages are less than two, show all in one page:
	if(pagesNumber<2) pagesNumber = 1;

	// Creating the controls div:
	var swControls = $('<div class="swControls">');

	for(var i=0;i<pagesNumber;i++)
	{
		// Slice a portion of the li elements, and wrap it in a swPage div:
		li.slice(i*resultsPerPage,(i+1)*resultsPerPage).wrapAll('<div class="swPage" />');

		// Adding a link to the swControls div:
		swControls.append('<a href="" class="swShowPage">'+(i+1)+'</a>');
	}

	ul.append(swControls);

	var maxHeight = 0;
	var totalWidth = 0;

	var swPage = ul.find('.swPage');
	swPage.each(function(){

	// Looping through all the newly created pages:
	var elem = $(this);

	var tmpHeight = 0;
	// 2 columns, so we only need it to be half. 
	elem.find('li').each(function(){tmpHeight+=$(this).data('height')/2;});

	if(tmpHeight>maxHeight)
		maxHeight = tmpHeight;
		totalWidth+=elem.outerWidth();
		elem.css('float','left').width(ul.width());
	});

	swPage.wrapAll('<div class="swSlider" />');

	// Setting the height of the ul to the height of the tallest page:
	ul.height(maxHeight);

	var swSlider = ul.find('.swSlider');
	swSlider.append('<div class="clear" />').width(totalWidth);

	var hyperLinks = ul.find('a.swShowPage');

	hyperLinks.click(function(e){

		// If one of the control links is clicked, slide the swSlider div
		// (which contains all the pages) and mark it as active:

		$(this).addClass('active').siblings().removeClass('active');

		swSlider.stop().animate({'margin-left': -(parseInt($(this).text())-1)*ul.width()},'slow');
		e.preventDefault();
	});

	// Mark the first link as active the first time the code runs:
	hyperLinks.eq(0).addClass('active');

	// Center the control div:
	swControls.css({
		'left':'50%',
		'margin-left':-swControls.width()/2
	});

	return this;

}})(jQuery);


$(document).ready(function(){
	showReviewsInPages();
});
