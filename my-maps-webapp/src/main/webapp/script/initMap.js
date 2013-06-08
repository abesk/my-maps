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
            if(createMap){
                makeJsonPoints();
            }
            
        }
        
    }   
    if(createMap != null){
        createMap();
    }
}

google.maps.event.addDomListener(window, 'load', initialize);