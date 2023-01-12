/* 
 * Honda de Mexico 2018
 * All rights reserved
 */

// replace values on Body Top Navigator (Home > Admin > Users)
$(function () {
    var childMenuText = '';
    var parentMenuText = '';
    
    $('ul > li a[href]').filter(
        function() {
            return this.pathname === window.location.pathname;
        }
    ).each(function(index){
        childMenuText = $(this).text().trim();
        parentMenuText = $(this).parent().parent().parent().find('span').first().text();
        //console.log("childMenuText:" +childMenuText + "\n" + "parentMenuText:" +parentMenuText + "\n");        
    });
    
    // check if parent menu text is not null
    if (parentMenuText){
        // remove carriage returns and whitespaces
        parentMenuText = removeCarriageReturn(parentMenuText);
        parentMenuText = removeWhiteSpace(parentMenuText).trim();
    }
    
    if (parentMenuText){
        // if parent menu text is empty, trim text and
        parentMenuText.trim();
        $('#body-nav-parent-span').text(parentMenuText);
    }else{
        // otherwise, remove <li> that contains 
        $('#body-nav-parent-li').remove();
    }
        
    $('#body-nav-child-span').text(childMenuText);

});


function removeWhiteSpace(t){
    return t.replace(/\s{2,10}/g, ' ');
}

function removeCarriageReturn(t){
    return t.replace(/[\n\r]+/g, '');
}