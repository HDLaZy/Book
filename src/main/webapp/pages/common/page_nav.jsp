<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2021/11/9
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%--分页条--%>
<div id="page_nav">

    <c:if test="${requestScope.page.pageNo>1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
    </c:if>

    <c:choose>
        <%--1--%>
        <c:when test="${requestScope.page.pageTotal<=5}">

            <c:forEach begin="1" end="${requestScope.page.pageTotal}" var="i">

                <c:if test="${i==requestScope.page.pageNo}">
                    【${i}】
                </c:if>
                <c:if test="${i!=requestScope.page.pageNo}">
                    <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                </c:if>

            </c:forEach>

        </c:when>
        <%--2--%>
        <c:when test="${requestScope.page.pageTotal > 5}">

            <c:choose>

                <c:when test="${requestScope.page.pageNo <= 3}">

                    <c:forEach begin="1" end="5" var="i">
                        <c:if test="${i==requestScope.page.pageNo}">
                            【${i}】
                        </c:if>
                        <c:if test="${i!=requestScope.page.pageNo}">
                            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                        </c:if>
                    </c:forEach>

                </c:when>

                <c:when test="${requestScope.page.pageNo > (requestScope.page.pageTotal-3)}">

                    <c:forEach begin="${requestScope.page.pageTotal-4}" end="${requestScope.page.pageTotal}" var="i">
                        <c:if test="${i==requestScope.page.pageNo}">
                            【${i}】
                        </c:if>
                        <c:if test="${i!=requestScope.page.pageNo}">
                            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                        </c:if>
                    </c:forEach>

                </c:when>

                <c:otherwise>

                    <c:forEach begin="${requestScope.page.pageNo-2}" end="${requestScope.page.pageNo+2}" var="i">
                        <c:if test="${i==requestScope.page.pageNo}">
                            【${i}】
                        </c:if>
                        <c:if test="${i!=requestScope.page.pageNo}">
                            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                        </c:if>
                    </c:forEach>

                </c:otherwise>

            </c:choose>

        </c:when>

    </c:choose>




    <c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>


    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
    到第<input value="${param.pageNo}"  name="pn" id="pn_input"/>页
    <input type="button"  id="searchPageBtn" value="确定">

    <script type="text/javascript">
        $(function () {
            $("#searchPageBtn").click(function () {

                var pageNo = $("#pn_input").val();

                var pageToal=${requestScope.page.pageTotal};

                if(pageNo<1 || pageNo>pageToal){
                    alert("页数有问题！！！")
                }else {
                    //js的Location地址栏对象  href可以获取浏览器地址栏的地址   可读可写
                    location.href="${pageScope.basePath}${requestScope.page.url}&pageNo="+pageNo;
                }
            });
        });
    </script>

</div>