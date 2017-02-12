<!-- Bootstrap CSS-->
<link href="../css/bootstrap.min.css" rel="stylesheet">

<!-- Develops CSS -->
<link href="../css/users.css" rel="stylesheet">
<link href="../css/font_style.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script type="javascript" src="../js/bootstrap.min.js"></script>

<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

<!-- Google Map API - Autocomplete Address Form-->
<script src="../js/google_api.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAd_ui82vz6dh7LzxoSF3nO4Sv3UeIQ0zg&signed_in=true&libraries=places&callback=initAutocomplete"
        async defer></script>

<!-- Include DataPicker plugin-->
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<%--<link rel="stylesheet" href="/resources/demos/style.css">--%>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
    $( function() {
        $( "#datepicker1" ).datepicker();
    } );
</script>
<script>
    $( function() {
        $( "#datepicker2" ).datepicker();
    } );
</script>
<script>
    $( function() {
        $( "#datepicker3" ).datepicker();
    } );
</script>

<!-- jQuery mask for input types -->
<script type="text/javascript" src="../js/jquery.mask.js"></script>
<script type="text/javascript">
    $(function() {
        $('.date').mask('00/00/0000');
        $('.time').mask('00:00:00');
        $('.date_time').mask('00/00/0000 00:00:00');
        $('.cep').mask('00000-000');
        $('.phone').mask('0000-0000');
        $('.phone_with_ddd').mask('(00) 0000-0000');
        $('.phone_us').mask('(000) 000-0000');
        $('.mixed').mask('AAA 000-S0S');
        $('.ip_address').mask('099.099.099.099');
        $('.percent').mask('##0,00%', {reverse: true});
        $('.clear-if-not-match').mask("00/00/0000", {clearIfNotMatch: true});
        $('.placeholder').mask("00/00/0000", {placeholder: "__/__/____"});
        $('.fallback').mask("00r00r0000", {
            translation: {
                'r': {
                    pattern: /[\/]/,
                    fallback: '/'
                },
                placeholder: "__/__/____"
            }
        });

        $('.selectonfocus').mask("00/00/0000", {selectOnFocus: true});

        $('.cep_with_callback').mask('00000-000', {onComplete: function(cep) {
            console.log('Mask is done!:', cep);
        },
            onKeyPress: function(cep, event, currentField, options){
                console.log('An key was pressed!:', cep, ' event: ', event, 'currentField: ', currentField.attr('class'), ' options: ', options);
            },
            onInvalid: function(val, e, field, invalid, options){
                var error = invalid[0];
                console.log ("Digit: ", error.v, " is invalid for the position: ", error.p, ". We expect something like: ", error.e);
            }
        });

        $('.crazy_cep').mask('00000-000', {onKeyPress: function(cep, e, field, options){
            var masks = ['00000-000', '0-00-00-00'];
            mask = (cep.length>7) ? masks[1] : masks[0];
            $('.crazy_cep').mask(mask, options);
        }});

        $('.cnpj').mask('00.000.000/0000-00', {reverse: true});
        $('.cpf').mask('000.000.000-00', {reverse: true});
        $('.money').mask('#.##0,00', {reverse: true});

        var SPMaskBehavior = function (val) {
                return val.replace(/\D/g, '').length === 11 ? '(00) 00000-0000' : '(00) 0000-00009';
            },
            spOptions = {
                onKeyPress: function(val, e, field, options) {
                    field.mask(SPMaskBehavior.apply({}, arguments), options);
                }
            };

        $('.sp_celphones').mask(SPMaskBehavior, spOptions);

        $(".bt-mask-it").click(function(){
            $(".mask-on-div").mask("000.000.000-00");
            $(".mask-on-div").fadeOut(500).fadeIn(500)
        })

        $('pre').each(function(i, e) {hljs.highlightBlock(e)});
    });
</script>

