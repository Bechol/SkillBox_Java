$(function(){

    $('#startDatePicker').datepicker({
        dateFormat:"dd.mm.yy",
    });

    $('#finishDatePicker').datepicker({
            dateFormat:"dd.mm.yy",
    });

    const appendBook = function(data) {
        var bookCode = '<a href="#" class="todoLink" data-active=true data-id="' +
            data.id + '">' + data.name + '</a>';
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

    $(document).on('click', '.todoLink', function() {
        var link = $(this);
        var todoId = $(this).data('id');
        var isActive = $(this).data('active');
        var request = $.ajax({
                    method: "GET",
                    url: '/todos/' + todoId,
                    cache: false,
                    success: function(response) {
                        var code = '<br><span> Дата начала: ' + response.dateStart + '<br>' +
                         'Дата завершения: ' + response.dateEnd + '<br>' +
                         'Описание: ' + response.description + '</span>';
                            link.parent().append(code);
                            link.attr('data-active', false);
                    },
                    //TODO: обновить link
                    beforeSend: function () {
                        if(isActive !== true) {
                            request.abort();
                        }
                    },
                    complete: function() {
                        link = null;
                    },

                    error: function() {
                        if (response.status == 404) {
                            alert('Задание не найдено.')
                        }
                    }
                });
        return false;
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
                book.id = response;
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