/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function updateQueryString(key, value, url) {
    if (!url)
        url = window.location.href;
    var re = new RegExp("([?&])" + key + "=.*?(&|#|$)(.*)", "gi"),
            hash;

    if (re.test(url)) {
        if (typeof value !== 'undefined' && value !== null)
            return url.replace(re, '$1' + key + "=" + value + '$2$3');
        else {
            hash = url.split('#');
            url = hash[0].replace(re, '$1$3').replace(/(&|\?)$/, '');
            if (typeof hash[1] !== 'undefined' && hash[1] !== null)
                url += '#' + hash[1];
            return url;
        }
    }
    else {
        if (typeof value !== 'undefined' && value !== null) {
            var separator = url.indexOf('?') !== -1 ? '&' : '?';
            hash = url.split('#');
            url = hash[0] + separator + key + '=' + value;
            if (typeof hash[1] !== 'undefined' && hash[1] !== null)
                url += '#' + hash[1];
            return url;
        }
        else
            return url;
    }
}

function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
            sURLVariables = sPageURL.split('&'),
            sParameterName,
            i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
}
;

function survey(selector, callback) {
    var urlLocale = getUrlParameter('locale');
    var oldvalue = '';
    switch (urlLocale) {
        case 'ru':
            oldvalue = 'ru_RU';
            $('.bfh-languages').attr('data-available', 'ru_RU,en_US');
            break;
        case 'en':
        default:
            oldvalue = 'en_US';
            $('.bfh-languages').attr('data-available', 'en_US,ru_RU');
    }
    $('.bfh-languages').attr('data-language', oldvalue);
    $('.bfh-languages > input[type=hidden]').val(oldvalue);
    setInterval(function() {
        var input = $(selector);
        if (input.val() !== oldvalue) {
            oldvalue = input.val();
            callback(oldvalue);
        }
    }, 100);
}
;

$(document).ready(function() {
    survey('.bfh-languages > input[type=hidden]',
            function(updated) {
                var key = 'locale';
                var val = updated.split('_')[0];
                window.location = updateQueryString(key, val);
            });
});
