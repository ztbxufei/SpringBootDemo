$(function () {
    $("select").each(function (i, elem) {
        var value = eval($(elem).attr("value"));
        if ($.isArray(value)) {
            for (var index = 0; index < value.length; index++) {
                $(elem).append(("<option value = "+value[index].code+">"+value[index].text+"</option>"));
            }
        }
    });
});