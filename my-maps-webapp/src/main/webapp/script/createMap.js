var map;
var markers = [];
function initialize() {
    var mapOptions = {
        zoom: 10,
        streetViewControl: false,
        center: new google.maps.LatLng(49.192050599999990000, 16.613190900000063000), //brno
        mapTypeId: google.maps.MapTypeId.ROADMAP
    }
    map = new google.maps.Map(document.getElementById('map-canvas'),
        mapOptions);
    if(importedMap != null && importedMap.pointsOfInterest != null){
        var ne = new  google.maps.LatLng(importedMap.view.northEast.latitude, importedMap.view.northEast.longitude);
        var sw = new  google.maps.LatLng(importedMap.view.southWest.latitude, importedMap.view.southWest.longitude);
        var bounds = new  google.maps.LatLngBounds();
        bounds.extend(ne);
        bounds.extend(sw)
        map.fitBounds(bounds);
        for(var i = 0; i< importedMap.pointsOfInterest.length; i++){
            var point = importedMap.pointsOfInterest[i];
            var latLng = new  google.maps.LatLng(point.point.latitude, point.point.longitude);
            var marker = new google.maps.Marker({
                position: latLng,
                map: map, 
                icon: point.iconPath,
                title: point.description
            });
            
            markers.push(marker);
            makeJsonPoints();
            
        }
        
    }    
    google.maps.event.addListener(map, 'click', function(arg){
        window.console.debug(arg);
        var latLng = arg.latLng;
        var dialog = dijit.byId('newMarker');
        var iconSelect = dijit.byId('iconSelect');
        var markerName = dijit.byId('markerName');
        dialog.onExecute = function(){
            var marker = new google.maps.Marker({
                position: latLng,
                map: map, 
                icon: baseUrl+'/icons/'+iconSelect.getValue()+'.png',
                title: markerName.getValue()
            });
            markers.push(marker);
            makeJsonPoints();
            dialog.hide();
                       
        }
        dialog.show();
                    
    }); 
    var neLat = dojo.byId('newView:neLat');
    var neLng = dojo.byId('newView:neLng');
    var swLat = dojo.byId('newView:swLat');
    var swLng = dojo.byId('newView:swLng');
    google.maps.event.addListener(map, 'bounds_changed', function() {
                   
        neLat.value = map.getBounds().getNorthEast().lat();
        neLng.value = map.getBounds().getNorthEast().lng();
        swLat.value = map.getBounds().getSouthWest().lat();
        swLng.value = map.getBounds().getSouthWest().lng();
    });

             
} 
            
var makeJsonPoints = function(){
    var result ={}
    for(var i =0; i < markers.length; i++){
        var marker = markers[i]
        result[i]={
            desc: marker.title,
            iconPath: marker.icon,
            lat: marker.position.lat(),
            lng: marker.position.lng()
        };
                    
    }
    var pointsEl = dojo.byId('mapForm:mapPoints');
    pointsEl.value = JSON.stringify(result);
}
            
google.maps.event.addDomListener(window, 'load', initialize);