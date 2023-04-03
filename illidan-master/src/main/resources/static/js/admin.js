
$(document).ready(function () {
    // var table = $('#myTables').DataTable({
    //     autoWidth: false,
    //     lengthMenu: [5, 10, 20, 50, 100, 200],
    //     columns : [
    //         { width : '20%' },
    //         { width : '26%' },
    //         { width : '26%' },
    //         { width : '8%' },
    //         { width : '6%' },
    //         { width : '6%' },
    //         { width : '8%' }
    //     ]
    // });
    // $('#myTables td').css('white-space','initial');

    var dataProduct = {};

    $("#btn-new-product").on("click", function () {
        dataProduct = {};
        $('#input-product-type').val("");
        $('#input-product-name').val("");
        $('#input-product-desc').val("");
        $("#input-product-support").val("");
        $('#input-product-price').val("")
        $('#input-product-delivery').val("")
        $("#modal-create-product").modal();
    });

    $(".btn-edit-product").on("click", function () {
        var productId = $(this).attr('data-product');
        // console.log(productId);
        $.ajax({
            url: "/api/product/detail/" + productId,
            type: "GET",
            dataType: 'json', // added data type
            contentType: "application/json; charset=utf-8",
            success: function (res) {
                if(res.success) {
                    // dataProduct.id = res.data.data.id;
                    $('#input-product-id').val(res.data.id);
                    $('#input-product-type').val(res.data.type);
                    $("#input-product-name").val(res.data.name);
                    $("#input-product-desc").val(res.data.description);
                    $("#input-product-support").val(res.data.support);
                    $('#input-product-price').val(res.data.price)
                    $('#input-product-delivery').val(res.data.delivery)
                    $('#input-product-kind').val(res.data.kindOfService)
                }
            }
        });
    });

    $(".btn-save-product").on("click", function () {
        if($("#input-product-name").val() === "" || $("#input-product-desc").val() === ""
            || $("#input-product-type").val() === "" || $("#input-product-support").val() === ""
            || $("#input-product-price").val() === "" || $("#input-product-delivery").val() === ""
            || $("#input-product-kind").val() === "") {
            swal(
                'Error',
                'You need to fill all values',
                'error'
            );
            return;
        }
        dataProduct.id = $('#input-product-id').val();
        dataProduct.name = $('#input-product-name').val();
        dataProduct.type = $('#input-product-type').val();
        dataProduct.description = $('#input-product-desc').val();
        dataProduct.support = $("#input-product-support").val();
        dataProduct.price = $('#input-product-price').val()
        dataProduct.delivery = $('#input-product-delivery').val()
        dataProduct.kindOfService = $('#input-product-kind').val()

        var data = JSON.stringify(dataProduct)
        // console.log(data)
        var url = "";
        if(dataProduct.id > 1) {
            url = "/api/product/update-product/" +dataProduct.id;
        } else {
            url = "/api/product/add-product";
        }
        swal({
            title: "Are you sure?",
            buttons: true,
            dangerMode: true,
        }).then(function(isConfirm) {
            if (!isConfirm) {
                swal('Cancelled!');
            } else {
                $.ajax({
                    url: url,
                    type: "POST",
                    data: data,
                    dataType: 'json', // added data type
                    contentType: "application/json; charset=utf-8",
                    success: function () {
                        swal("Done!", "It was succesfully!", "success");
                    },
                    error: function (xhr, ajaxOptions, thrownError) {
                        swal("Error adding!", "Please try again", "error");
                    }
                });
            }
        });

    });

    $(".btn-delete-product").on("click", function () {
        var productId = $(this).attr('data-product');
        swal({
            title: "Are you sure?",
            text: "Once deleted, you will not be able to recover this file!",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        }).then(function(isConfirm) {
            if (!isConfirm) {
                swal('Cancelled!');
            } else {
                $.ajax({
                    url: "/api/product/delete-product/" + productId,
                    type: "POST",
                    dataType: "html",
                    success: function () {
                        swal("Done!", "It was succesfully deleted!", "success");
                    },
                    error: function (xhr, ajaxOptions, thrownError) {
                        swal("Error deleting!", "Please try again", "error");
                    }
                });
                location.reload();
            }
        });

    });

    $(".btn-edit-status").on("click", function () {
        var orderId = $(this).attr('data-order');
        var status = $(this).parent().parent().find('.status').text();
        if(status === "false") {
            swal({
                title: "Are you sure?",
                icon: "warning",
                buttons: true,
                dangerMode: true,
            }).then(function(isConfirm) {
                if (!isConfirm) {
                    swal('Cancelled!');
                } else {
                    $.ajax({
                        url: "/api/admin/change-status/" + orderId,
                        type: "POST",
                        dataType: "html",
                        success: function () {
                            swal("Done!", "It was succesfully!", "success");
                        },
                        error: function (xhr, ajaxOptions, thrownError) {
                            swal("Error!", "Please try again", "error");
                        }
                    });
                    location.reload();
                }
            });
        }
    });

    $(".btn-delete-order").on("click", function () {
        var orderId = $(this).attr('data-order');
        // console.log(orderId)
        swal({
            title: "Are you sure?",
            text: "Once deleted, you will not be able to recover this file!",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        }).then(function(isConfirm) {
            if (!isConfirm) {
                swal('Cancelled!');
            } else {
                $.ajax({
                    url: "/api/admin/delete-order/" + orderId,
                    type: "POST",
                    dataType: "html",
                    success: function () {
                        swal("Done!", "It was succesfully deleted!", "success");
                    },
                    error: function (xhr, ajaxOptions, thrownError) {
                        swal("Error deleting!", "Please try again", "error");
                    }
                });
                location.reload();
            }
        });

    });

    // $('#myTable').DataTable();

    $('.tab-content').hide();
    $('.tab-content').first().show();
    $('#list-question ul').find('li').first().addClass('active');
    $('#list-question ul li a').click(function (event) {
        event.preventDefault();
        var content = $(this).attr('href');
        var class_name = $(this).parent().attr('class');
        if (class_name !== "active") {
            $(content).slideToggle(500);
            $(content).siblings().hide(10);
            $(this).parent().siblings().removeClass("active")
            $(this).parent().addClass("active")
        } else {
            $(content).slideToggle(500);
            $(this).parent().removeClass("active")
        }
    });


});