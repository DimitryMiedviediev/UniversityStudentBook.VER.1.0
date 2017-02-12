// This example displays an address form, using the autocomplete feature
// of the Google Places API to help users fill in the information.

var placeSearch, autocomplete1, autocomplete2, autocomplete3;
var componentForm = {
    street_number: 'short_name',
    route: 'long_name',
    locality: 'long_name',
    administrative_area_level_1: 'short_name',
    country: 'long_name',
    postal_code: 'short_name'
};

function initAutocomplete() {
    // Create the autocomplete1 object, restricting the search to geographical
    // location types.
    autocomplete1 = new google.maps.places.Autocomplete(
        /** @type {!HTMLInputElement} */(document.getElementById('autocomplete1')),
        {types: ['geocode']});

    // When the user selects an address from the dropdown, populate the address
    // fields in the form.
    var fia1 = new FillInAddress(1, autocomplete1);
    autocomplete1.addListener('place_changed', fia1);

    autocomplete2 = new google.maps.places.Autocomplete(
        /** @type {!HTMLInputElement} */(document.getElementById('autocomplete2')),
        {types: ['geocode']});

    // When the user selects an address from the dropdown, populate the address
    // fields in the form.
    var fia2 = new FillInAddress(2, autocomplete2);
    autocomplete2.addListener('place_changed', fia2);

    autocomplete3 = new google.maps.places.Autocomplete(
        /** @type {!HTMLInputElement} */(document.getElementById('autocomplete3')),
        {types: ['geocode']});

    // When the user selects an address from the dropdown, populate the address
    // fields in the form.
    var fia3 = new FillInAddress(3, autocomplete3);
    autocomplete3.addListener('place_changed', fia3);
}

function FillInAddress(num, ac) {
    var fia = function() {
        var place = ac.getPlace();
        console.log("Component number: " + num);
        for (var component in componentForm) {
            document.getElementById(component+num).value = '';
            document.getElementById(component+num).disabled = false;
        }

        // Get each component of the address from the place details
        // and fill the corresponding field on the form.
        for (var i = 0; i < place.address_components.length; i++) {
            var addressType = place.address_components[i].types[0];
            if (componentForm[addressType]) {
                var val = place.address_components[i][componentForm[addressType]];
                document.getElementById(addressType+num).value = val;
            }
        }
    };
    return fia;
}