$(function(){

    $('#startDatePicker').datepicker({
        dateFormat:"dd.mm.yy",
    });

    $('#finishDatePicker').datepicker({
            dateFormat:"dd.mm.yy",
    });

    const appendBook = function(data) {
        var bookCode = '<h4>' + data.name + '</h4>' +
        "Дата начала: " + data.dateStart + '<br>' +
        "Дата завершения: " + data.dateEnd + '<br>' +
        "Описание: " + data.description;
        $('#todo-list')
                .append('<div id="divTodo">' + bookCode + '</div>');
    };

    $.get('/todos/', function(response){
        for(i in response) {
            appendBook(response[i]);
        }
    });

    $('#showAddTodoFormBtn').click(function() {
        $("#todoForm form").trigger('reset');
        $('#todoForm').css('display', 'flex');
    });

    $('#todoForm').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none');
        }
    });

    $('#saveTodoBtn').click(function() {
        var data = $('#todoForm form').serialize();
        $.ajax({
            method: "POST",
            url: '/todos/',
            data: data,
            success: function(response) {
                $('#todoForm').css('display', 'none');
                var book = {};
                book.id = response.id;
                var dataArray = $('#todoForm form').serializeArray();
                for(i in  dataArray) {
                    book[dataArray[i]['name']] = dataArray[i]['value'];
                }
                appendBook(book);
            }
        });
        return false;
    });
});