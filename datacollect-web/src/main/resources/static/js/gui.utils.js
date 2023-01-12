/* 
 * Honda de Mexico 2018
 * All rights reserved
 */

function pad(num, size) {
    var s = num + "";
    while (s.length < size)
        s = "0" + s;
    return s;
}

function autoRefreshPage() {
    window.location = window.location.href;
}