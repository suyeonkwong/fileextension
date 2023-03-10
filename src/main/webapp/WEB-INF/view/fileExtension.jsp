<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF8">
    <title>첨부파일 확장자 체크 테스트페이지</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>

<body>
    <div>

        <div>
            <h2>파일 확장자 차단</h2>
        </div>

        <hr />

        <div>
            <p>
                고정 확장자
                <c:forEach var="fixedFileExtension" items="${fixedFileExtensions}">
                    <label for="${fixedFileExtension.fileExtensionSeq}">${fixedFileExtension.fileExtensionName}</label>
                    <input type="checkbox" id="${fixedFileExtension.fileExtensionSeq}"
                            <c:if test="${fixedFileExtension.fileExtensionVisibility eq 'SHOW'}">checked</c:if>
                           onchange="updateFixedFileExtensionVisibilityRequest(this.id)"/>
                </c:forEach>
            </p>
        </div>

        <div>
            <p>
                커스텀 확장자
                <input type="text" id="addCustomFileExtensionName" maxlength="20" />
                <button id="addCustomFileExtension" onclick="addCustomFileExtensionRequest()">추가</button>
            </p>
        </div>

        <div id="viewCustomFileExtension">
            <c:forEach var="customFileExtension" items="${customFileExtensions}">
                <button class="${customFileExtension.fileExtensionSeq}"
                        <c:if test="${customFileExtension.fileExtensionVisibility eq 'HIDE'}">disabled</c:if>>
                        ${customFileExtension.fileExtensionName}</button>
                <button id="${customFileExtension.fileExtensionSeq}" class="${customFileExtension.fileExtensionSeq}"
                        <c:if test="${customFileExtension.fileExtensionVisibility eq 'HIDE'}">disabled</c:if>
                        onclick="updateCustomFileExtensionVisibilityRequest(this.id)">x </button>
            </c:forEach>
        </div>

        </div>

    </div>

</body>

<script>

    function updateFixedFileExtensionVisibilityRequest(fileExtensionSeq) {

        $.ajax({
            type : "PATCH",
            url : "/fileextension",
            data: {fileExtensionSeq : Number(fileExtensionSeq)},
            success:function(result){
                console.log(result)
            },
            error:function(error){
                console.log(error);
            }
        });

    }

    function addCustomFileExtensionRequest() {

        let data = {
            fileExtensionName: $("#addCustomFileExtensionName").val()
        };

        $.ajax({
            type : "POST",
            url : "/fileextension",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            success:function(result){

                $("#viewCustomFileExtension")
                    .append("<button class=" + result.fileExtensionSeq + ">" + result.fileExtensionName + "</button>" +
                    "<button id=" + result.fileExtensionSeq + "class=" + result.fileExtensionSeq + "> x </button>");
            },
            error:function(error){ //실패 이벤트 핸들러
                console.log(error);
            }
        });
    }

    function updateCustomFileExtensionVisibilityRequest(fileExtensionSeq) {

        $(this.class).attr("disabled", true)

        $.ajax({
            type : "PATCH",
            url : "/fileextension",
            data: {fileExtensionSeq : Number(fileExtensionSeq)},
            success:function(result){
                console.log(result)
            },
            error:function(error){
                console.log(error);
            }
        });
    }

</script>

</html>
