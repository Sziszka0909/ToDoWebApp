$(document).ready(function () {
        $.ajax({
            url: "/database",
            type: "GET",

            success: function (data) {
                $("#here").append(data);
                $(".butt").click(function () {
                    var id = this.id;
                    console.log(data);
                    $.post("/database", {hidden: id});
                    $("#" + id).remove();
                });
            }
        });
        $("#button").click(function () {
            var activity = $("#activity").val();
            $.post("/list", {activity: activity}, function (data) {
                $.ajax({
                    url: "/database",
                    type: "GET",

                    success: function (data) {
                        $("#here").empty();
                        $("#here").append(data);
                        $(".butt").click(function () {
                            var id = this.id;
                            $.post("/database", {hidden: id});
                            $("#" + id).remove();
                        });
                    }
                });
            });

        });
    });