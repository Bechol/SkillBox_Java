$(function(){

    $('.dateInput').datepicker({
       dateFormat:"dd.mm.yy",
    });

    const appendToDo = function(data) {
        var todoCode = '<a href="#" class="todoLink" data-active=true data-id="' +
            data.id + '">' + data.name + '</a>';
            $('#todo-table').append(
                '<tr>' +
                '<td>' + todoCode + '</td>' +
                '<td class="taskDate">' + data.dateStart + '</td>' +
                '<td class="taskDate">' + data.dateEnd + '</td>' +
                '<td>' + data.description + '</td>' +
                '</tr>'
            );
    };

    //Load todos to table
    $.get('/api/v1/todo/alltodos', function(response){
        for(i in response) {
            appendToDo(response[i]);
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

    $('#editForm').click(function(event){
            if(event.target === this) {
                $(this).css('display', 'none');
            }
    });

    var globToDoId;

    //Open form with task information
    $(document).on('click', '.todoLink', function() {
        var link = $(this);
        var todoId = $(this).data('id');
        $('#editForm').css('display', 'flex');
        $.ajax({
                method: "GET",
                url: '/api/v1/todo/' + todoId,
                success: function(response) {
                    globToDoId = todoId;
                    $('#editToDoName').val(response.name);
                    $('#editStartDate').val(response.dateStart);
                    $('#editEndDate').val(response.dateEnd);
                    $('#editToDoDescription').val(response.description);
                },
                error: function() {
                    if (response.status == 404) {
                    alert('Задание не найдено.')
                    }
                }
        });
    });

    //Saving task
    $('#saveTodoBtn').click(function() {
        var formData = {
                'name': $('#editToDoName').val(),
                'dateStart': $('#editStartDate').val(),
                'dateEnd': $('#editEndDate').val(),
                'description': $('#editToDoDescription').val()
            };
        $.ajax({
                url: '/api/v1/todo/' + globToDoId,
                type: "PUT",
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                data: JSON.stringify(formData),
                success: function(response) {
                    $('#editForm').css('display', 'none');
                },
                error: function() {
                    if (response.status == 404) {
                        alert('Задание не найдено.')
                    }
                }
        });
    });

    //Deleting task
    $('#deleteTodoBtn').click(function() {
        $.ajax({
                url: '/api/v1/todo/' + globToDoId,
                method: "DELETE",
                success: function(response) {
                    $('#editForm').css('display', 'none');
                },
                error: function() {
                    if (response.status == 404) {
                        alert('Задание не найдено.')
                    }
                }
        });
    });

    //Adding task
    $('#addNewTodoBtn').click(function() {
        var formData = {
        			'name': $('#newToDoName').val(),
        			'dateStart': $('#startDatePicker').val(),
        			'dateEnd': $('#finishDatePicker').val(),
        			'description': $('#newToDoDescription').val()
        		};
        $.ajax({
                url: '/api/v1/todo/',
                type: "POST",
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                data: JSON.stringify(formData),
                success: function(response) {
                    $('#todoForm').css('display', 'none');
                    var todo = {};
                    todo.id = response;
                    var dataArray = $('#todoForm form').serializeArray();
                    for(i in  dataArray) {
                        todo[dataArray[i]['name']] = dataArray[i]['value'];
                    }
                    appendToDo(todo);
                }
        });
    });
});