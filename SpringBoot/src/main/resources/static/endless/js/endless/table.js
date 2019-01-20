function dataTableInit(id) {
    dataTable = $('#' + id).dataTable({
        "bFilter": true,
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "aLengthMenu": [10, 25, 30, "All"], // 定义每页显示数据量
        //"bServerSide": true,
        //"sServerMethod": "POST",
        //"sAjaxSource": "codeTable1",
        // data: [
        //     [ "Trident", "Internet Explorer 4.0", "Win 95+", 4, "X" ],
        //     [ "Trident", "Internet Explorer 5.0", "Win 95+", 5, "C" ],
        //     [ "Trident", "Internet Explorer 6.0", "Win 98+", 6, "A" ],
        //     [ "Trident", "Internet Explorer 7.0", "Win XP SP2+", 7, "A" ],
        //     [ "Gecko", "Firefox 1.5", "Win 98+ / OSX.2+", 1.8, "A" ],
        //     [ "Gecko", "Firefox 2", "Win 98+ / OSX.2+", 1.8, "A" ],
        //     [ "Gecko", "Firefox 3", "Win 2k+ / OSX.3+", 1.9, "A" ],
        //     [ "Webkit", "Safari 1.2", "OSX.3", 125.5, "A" ],
        //     [ "Webkit", "Safari 3.0", "OSX.4+", 522.1, "A" ]
        // ],
        // columns: [
        //     { "sTitle": "Engine" },
        //     { "sTitle": "Browser" },
        //     { "sTitle": "Platform" },
        //     { "sTitle": "Version", "sClass": "center" },
        //     {
        //         "sTitle": "Grade",
        //         "sClass": "center",
        //         "fnRender": function(obj) {
        //             var sReturn = obj.aData[ obj.iDataColumn ];
        //             if ( sReturn == "A" ) {
        //                 sReturn = "<b>A</b>";
        //             }
        //             return sReturn;
        //         }
        //     }
        // ],
        "destroy": true,
        "oLanguage":{
            "sProcessing": "<img th:src='/img/loading.gif'>  努力加载数据中.",
            "sLengthMenu": "每页显示 _MENU_ 条记录",
            "sZeroRecords": "抱歉， 没有找到",
            "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
            "sInfoEmpty": "没有数据",
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
}