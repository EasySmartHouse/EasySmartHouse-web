/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
if (!net)
    var net = {};
if (!net.easysmarthouse)
    net.easysmarthouse = {};
if (!net.easysmarthouse.gwt)
    net.easysmarthouse.gwt = {};
net.easysmarthouse.gwt.anchor = {
    getHash: function() {
        var hash = self.document.location.hash;
        if (hash) {
            var gwtAnchor = unescape(hash.substring(1));
            return gwtAnchor;
        }
        return "";
    }
};
