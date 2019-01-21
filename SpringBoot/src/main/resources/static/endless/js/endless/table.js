function serverDataTableInit(id, url, param, aoColumns) { // param 为json对象
    var dataTable = $('#' + id).dataTable({
        "bFilter": false,
        "bJQueryUI": true,
        "bPaginate": true,
        "bLengthChange": false, //改变每页显示数据数量
        "sPaginationType": "full_numbers",
        //"aLengthMenu": [10, 25, 30, "All"], // 定义每页显示数据量
        "bServerSide": true,
        "bRetrieve": true,
        "bSort":false,
        "iDisplayLength": 10,
        "sServerMethod": "POST",
        "sAjaxSource": url,
        "fnServerData": function retrieveData(sSource,aoData,fnCallback) {
            aoData.push({"name":"bServerSide", "value":"true"}); // 表示后端分页
            for(var key in param){
                aoData.push({"name":key, "value":param[key]});
            }
            $.ajax({
                url : sSource,//这个就是请求地址对应sAjaxSource
                data : {"aoData":JSON.stringify(aoData)},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
                type : 'post',
                dataType : 'json',
                async : false,
                success : function(result) {
                    fnCallback(result);//把返回的数据传给这个方法就可以了,datatable会自动绑定数据的
                },
                error : function(msg) {
                }
            });
        },
        "aoColumns":aoColumns,
        "destroy": true,
        "oLanguage":{
            "sProcessing": "<img th:src='/img/loading.gif'>  努力加载数据中.",
            "sLengthMenu": "每页显示 _MENU_ 条记录",
            "sZeroRecords": "抱歉， 没有找到",
            "sInfo": "从 _START_ 到 _END_ /共 _MAX_ 条数据",
            "sInfoFiltered" : "",
            "sInfoEmpty": "没有数据",
            "oPaginate":{'sNext':'下页','sPrevious':'下页','sFirst':'第一页','sLast':'最后一页'},
            "sZeroRecords": "没有检索到数据",
        },
    });
    $('#chk-all').click(function () {
        if ($(this).is(':checked')) {
            $('#responsiveTable').find('.chk-row').each(function () {
                $(this).prop('checked', true);
                $(this).parent().parent().parent().addClass('selected');
            });
        }
        else {
            $('#responsiveTable').find('.chk-row').each(function () {
                $(this).prop('checked', false);
                $(this).parent().parent().parent().removeClass('selected');
            });
        }
    })
    return dataTable;
}


function clientDataTableInit(id, url, param, aoColumns){
    var dataTable = $('#' + id).dataTable({
        "bFilter": true,
        "bJQueryUI": true,
        "bPaginate": true,
        "bLengthChange": false, //改变每页显示数据数量
        "sPaginationType": "full_numbers",
        "bServerSide": false,
        //"bRetrieve": true,
        //"bSort":true,
        "iDisplayLength": 10,
        "sServerMethod": "POST",
        "sAjaxSource": url,
        "fnServerData": function retrieveData(sSource,aoData,fnCallback) {
            aoData.push({"name":"bServerSide", "value":"false"});// 表示前端分页
            for(var key in param){
                aoData.push({"name":key, "value":param[key]});
            }
            $.ajax({
                url : sSource,//这个就是请求地址对应sAjaxSource
                data : {"aoData":JSON.stringify(aoData)},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
                type : 'post',
                dataType : 'json',
                async : false,
                success : function(result) {
                    fnCallback(result);//把返回的数据传给这个方法就可以了,datatable会自动绑定数据的
                },
                error : function(msg) {
                }
            });
        },
        "aoColumns":aoColumns,
        "destroy": true,
        "oLanguage":{
            "sProcessing": "<img th:src='/img/loading.gif'>  努力加载数据中.",
            "sLengthMenu": "每页显示 _MENU_ 条记录",
            "sZeroRecords": "抱歉， 没有找到",
            "sInfo": "从 _START_ 到 _END_ /共 _MAX_ 条数据",
            "sInfoFiltered" : "",
            "sInfoEmpty": "没有数据",
            "oPaginate":{'sNext':'下页','sPrevious':'下页','sFirst':'第一页','sLast':'最后一页'},
            "sZeroRecords": "没有检索到数据",
        },
    });
    $('#chk-all').click(function () {
        if ($(this).is(':checked')) {
            $('#responsiveTable').find('.chk-row').each(function () {
                $(this).prop('checked', true);
                $(this).parent().parent().parent().addClass('selected');
            });
        }
        else {
            $('#responsiveTable').find('.chk-row').each(function () {
                $(this).prop('checked', false);
                $(this).parent().parent().parent().removeClass('selected');
            });
        }
    })
    return dataTable;
}

