function tableInit(id) {
    $('#' + id).dataTable({
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "oLanguage": {
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
