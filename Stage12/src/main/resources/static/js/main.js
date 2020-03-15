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
                '<td class="taskDate">' + $.datepicker.formatDate('dd.mm.yy', new Date(data.dateStart)) + '</td>' +
                '<td class="taskDate">' + $.datepicker.formatDate('dd.mm.yy', new Date(data.dateEnd)) + '</td>' +
                '<td>' + data.description + '</td>' +
                '</tr>'
            );
    };

    //Load todos to table
    $.get('/todos/', function(response){
        for(i in response) {
            appendToDo(response[i]);
        }
        console.log("data successfully loaded into table")
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
                url: '/todos/' + todoId,
                success: function(response) {
                    globToDoId = todoId;
                    $('#todoName').val(response.name);
                    $('#editStartDate').val($.datepicker.formatDate('dd.mm.yy', new Date(response.dateStart)));
                    $('#editEndDate').val($.datepicker.formatDate('dd.mm.yy', new Date(response.dateEnd)));
                    $('#description').val(response.description);
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
        var data = $('#editForm form').serialize();
        $.ajax({
                method: 'PATCH',
                url: '/todos/' + globToDoId,
                data: data,
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
                method: 'DELETE',
                url: '/todos/' + globToDoId,
                success: function(response) {
                    alert("Задание удалено!")
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
        var data = $('#todoForm form').serialize();
        $.ajax({
                method: "POST",
                url: '/todos/',
                data: data,
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