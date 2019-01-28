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

function ExportTableDataByPage(tableId, columns, url){
    var aotoData = aotoDataMap[tableId];
    var param = "?";
    for(var index = 0; index < aotoData.length; index++){
        param = param + aotoData[index].name + "=" + aotoData[index].value + "&";
    }
    window.location.href = url + "?" + param;
}

function ExportTableDataAll(tableId, columns, url){
    var aotoData = aotoDataMap[tableId];
    var param = "?";
    for(var index = 0; index < aotoData.length; index++){
        if(aotoData[index].name == "bServerSide"){
            aotoData[index].value = "false";
        }
        param = param + aotoData[index].name + "=" + aotoData[index].value + "&";
    }
    window.location.href = url + "?" + param;
}


function getParamByDataTable(tableId, columns, param){
    var jsonList = [];
    var columnKey;
    for(var index = 0; index < columns.length; index++){
        columnKey = columns[index];
        if(columnKey.isExported == false){ //设置为不导出的不用计算在导出里面
            continue;
        }
        var data = {};
        data.name = columnKey.mDataProp;
        data.value = columnKey.title;
        jsonList.push(data);
    }
    param.push({"name":"headData", "value":JSON.stringify(jsonList)});
}

function initTable(tableId, aoColumns){
    var thead = "<thead><tr>";
    for(var index = 0; index < aoColumns.length; index++){
        thead = thead + "<td>" + aoColumns[index].title + "</td>";
    }
    thead = thead + "</tr></thead>";
    $("#" + tableId).append(thead);
}