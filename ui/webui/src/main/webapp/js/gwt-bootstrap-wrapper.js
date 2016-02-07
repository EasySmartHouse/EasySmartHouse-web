/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
if (!net)
    var net = {};
if (!net.easysmarthouse)
    net.easysmarthouse = {};
if (!net.easysmarthouse.gwt)
    net.easysmarthouse.gwt = {};
net.easysmarthouse.gwt.wrap = function() {
    $(".gwt-DecoratedTabBar").addClass("table table-hover");
    $(".gwt-TabBarFirst-wrapper").addClass("nohover");
    $(".gwt-TabBarRest-wrapper").addClass("nohover");
};

