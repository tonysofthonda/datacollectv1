/* 
 * Honda de Mexico 2018
 * All rights reserved
 */

// mark menu sidebar item as active according to current page
$(function () {
    $('ul > li a[href]').filter(
        function() {
            return this.pathname === window.location.pathname;
        }
    ).parent('li').addClass('active').parent('ul').parent('li').addClass('active');
});
