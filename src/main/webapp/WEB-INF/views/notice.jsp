<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>


    <ul class="noticeList">
        <li></li>
    </ul>

    <script>
        $(document).ready(function () {

            list();

            $('[name="btnDelete"]').on('click', function() {
                // 삭제
                var obj = {
                    "num": 1
                }
                $.ajax({
                    type: "delete",
                    url: "notiece/" + obj.num + ".json",
                    success: function (response) {
                        debugger
                    }, error: function(err) {
                        debugger
                    }
                });
            });

            $('[name="btnDetail"]').on('click', function() {
                // 수정 
                var obj = {
                    "num": 1
                }
                $.ajax({
                    type: "delete",
                    url: "notiece/" + obj.num + ".json",
                    success: function (response) {
                        debugger
                    }, error: function(err) {
                        debugger
                    }
                });
            });

            $('[name="btnNew"]').on('click', function() {
                window.open('noticeDetail.do','noticeNew','width=800,height=700');
            });

            function list() {

                var page = {
                    pageSize: 10,
                    page: 1
                }
                $.ajax({
                    url: "notice.json",
                    data: page,
                    success: function (response) {
                        var str = '';
                        $.each(response.data, function (i, vo) {
                            str += '<ol>';
                            str += '  <ul>';
                            str += '    <li>' + vo.num + '</li>';
                            str += '    <li>' + vo.pass + '</li>';
                            str += '    <li>' + vo.name + '</li>';
                            str += '    <li>' + vo.email + '</li>';
                            str += '    <li>' + vo.title + '</li>';
                            str += '    <li>' + vo.content + '</li>';
                            str += '    <li>' + vo.readcount + '</li>';
                            str += '    <li>' + vo.writedate + '</li>';
                            str += '  </ul>';
                            str += '</ol>';
                        });
                        $('.noticeList').html(str);
                    }
                });
            }
        });
    </script>
</body>
</html>