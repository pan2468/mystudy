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
       <h1>공지사항 상세<h1>
    <table>
        <tr>
            <td>
                <label for="txt1">name</label>
                <input type="text" name="name" value="${vo.name}">
            </td>
        </tr>
        <tr>
            <td>
                <label for="txt2">email</label>
                <input type="email" name="email" value="${vo.email}">
            </td>
        </tr>
        <tr>
            <td>
                <label for="txt3">title</label>
                <input type="text" name="title" value="${vo.title}">
            </td>
        </tr>
        <tr>
            <td>
                <label for="txt1">contents</label>
                <textarea name="contents" id="" cols="30" rows="10">${vo.contents}</textarea>
            </td>
        </tr>
        <c:if test="${not empty vo}">
        <tr>
            <td>
                <label for="txt1">readcount</label>
                <input type="text" name="readcount" value="${vo.readcount}">
            </td>
        </tr>
        <tr>
            <td>
                <label for="txt1">writedate</label>
                <input type="text" name="writedate" value="${vo.writedate}">
            </td>
        </tr>
        </c:if>
        <tr>
            <td>
                <input type="button" value="저장" name="btnSave"> &nbsp;&nbsp;
                <input type="button" value="취소" name="btnCancel">
            </td>
        </tr>
    </table>
    <script>
        $(document).ready(function () {

            $('[name="btnSave"]').on('click', function() {

                var obj = {
                    "num": $('[name="num"]').val(),
                    "pass": $('[name="pass"]').val(),
                    "name": $('[name="name"]').val(),
                    "email": $('[name="email"]').val(),
                    "title": $('[name="title"]').val(),
                    "content": $('[name="content"]').val(),
                    "readcount": $('[name="readcount"]').val(),
                    "writedate": $('[name="writedate"]').val()
                };

                if(obj.num == 0) {
                    // 등록
                    $.ajax({
                        type: "post",
                        url: "notice.json",
                        data: JSON.stringify(obj),
                        success: function (response) {
                            debugger            
                        }, error: function( err) {
                            debugger
                        }
                    });
                } else {
                    // 수정
                    $.ajax({
                        type: "put",
                        url: "notice/" + obj.num + '.json',
                        data: JSON.stringify(obj),
                        success: function (response) {
                            debugger            
                        }, error: function( err) {
                            debugger
                        }
                    });
                }

            })

            $('[name="btnCancel"]').on('click', function() {
                window.close();
                opener.location.reload();
            });
        });
    </script>
</body>
</html>