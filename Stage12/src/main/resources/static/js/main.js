$(function(){

    var baseUrl = "http://localhost:8080/api/user/v1/todo/"

    $('.dateInput').datepicker({
       dateFormat:"dd.mm.yy",
    });

    getAllToDo();

    function getAllToDo() {
        $.ajax({
            url: baseUrl + "alltodos",
            method: "GET",
            dataType: "json",
            success: function(data) {
                var tableBody = $("#todo-table");
                tableBody.empty();
                $(data).each(function(index, element) {
                    tableBody.append(
                        '<tr>' +
                        '<td>' +
                        '<a href="#" class="todoLink" data-active=true data-id="' + element.id + '">' + element.name + '</a>' +
                        '</td>' +
                        '<td class="taskDate">' + element.dateStart + '</td>' +
                        '<td class="taskDate">' + element.dateEnd + '</td>' +
                        '<td>' + element.description + '</td>' +
                        '</tr>'
                    );
                })
            }
        });
    };

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
    $(document).on("click", ".todoLink", function() {
        var link = $(this);
        var todoId = $(this).data('id');
        $("#editForm").css("display", "flex");
        $.ajax({
                url: baseUrl + todoId,
                method: "GET",
                success: function(response) {
                    globToDoId = todoId;
                    $("#editToDoName").val(response.name);
                    $("#editStartDate").val(response.dateStart);
                    $("#editEndDate").val(response.dateEnd);
                    $("#editToDoDescription").val(response.description);
                },
                error: function(response) {
                    if (response.status == 404) {
                        alert("Задание не найдено.");
                    }
                }
        });
    });

    //Saving task
    $("#saveTodoBtn").click(function() {
        var formData = {
                "name": $("#editToDoName").val(),
                "dateStart": $("#editStartDate").val(),
                "dateEnd": $("#editEndDate").val(),
                "description": $("#editToDoDescription").val()
            };
        $.ajax({
                url: baseUrl + globToDoId,
                method: 'PUT',
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                data: JSON.stringify(formData),
                success: function(response) {
                    getAllToDo();
                    $('#editForm').css('display', 'none');
                },
                error: function(response) {
                    if (response.status == 404) {
                        alert('Задание не найдено.');
                    }
                }
        });
    });

    //Deleting task
    $("#deleteTodoBtn").click(function() {
        $.ajax({
            url: baseUrl + globToDoId,
            method: "DELETE",
            success: function() {
                getAllToDo();
                $("#editForm").css("display", "none");
            },
            error: function (error) {
              console.log(error);
            }
        });
    });

    //Adding task
    $("#addNewTodoBtn").click(function() {
        var formData = {
        			"name" : $("#newToDoName").val(),
        			"dateStart" : $("#startDatePicker").val(),
        			"dateEnd" : $("#finishDatePicker").val(),
        			"description" : $("#newToDoDescription").val()
        		};
        $.ajax({
            url: baseUrl,
            method: "POST",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(formData, null, '\t'),
            success: function() {
                getAllToDo();
            },
            error: function (error) {
                console.log(error);
            }
        });
    });
});