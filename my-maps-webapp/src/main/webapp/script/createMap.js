
function createMap() {
      
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
