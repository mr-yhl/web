<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>线路列表</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/search.css">
    </head>
    <body>
        <!--引入头部-->

        <%@include file="header.jsp" %>
        <div class="page_one">
            <div class="contant">
                <div class="crumbs">
                    <img src="${pageContext.request.contextPath}/images/search.png" alt="">
                    <p>黑马旅行><span>搜索结果</span></p>
                </div>
                <div class="xinxi clearfix">
                    <%--线路列表 start--%>
                    <div class="left">
                        <div class="header">
                            <span>商品信息</span>
                            <span class="jg">价格</span>
                        </div>
                        <ul>
                            <c:forEach items="${pb.list}" var="route">
                                <li>
                                    <div class="img"><img src="${pageContext.request.contextPath}/${route.rimage}"
                                                          width="300px" alt=""></div>
                                    <div class="text1">
                                        <p>${route.rname}</p>
                                        <br/>
                                        <p>${route.routeIntroduce}</p>
                                    </div>
                                    <div class="price">
                                        <p class="price_num">
                                            <span>&yen;</span>
                                            <span>${route.price}</span>
                                            <span>起</span>
                                        </p>
                                        <p><a href="#">查看详情</a></p>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>


                        <%--分页--%>
                        <div class="page_num_inf">
                            <i></i> 共
                            <span>${pb.totalPage}</span>页<span>${pb.totalCount}</span>条
                        </div>
                        <div class="pageNum">
                            <ul>
                                <c:if test="${pb.pageNum > 1}">
                                    <li class="threeword"><a
                                            href="${pageContext.request.contextPath}/RouteServlet?method=findByPage&pageNum=${pb.pageNum - 1}&pageSize=10">上一页</a>
                                    </li>
                                </c:if>
                                <c:forEach begin="1" end="${pb.totalPage}" var="page">
                                    <c:if test="${page == pb.pageNum}">
                                        <li class="curPage"><a
                                                href="${pageContext.request.contextPath}/RouteServlet?method=findByPage&pageNum=${page}&pageSize=10">${page}</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${page != pb.pageNum}">
                                        <li>
                                            <a href="${pageContext.request.contextPath}/RouteServlet?method=findByPage&pageNum=${page}&pageSize=10">${page}</a>
                                        </li>
                                    </c:if>
                                </c:forEach>

                                <c:if test="${pb.pageNum < pb.totalPage}">
                                    <li class="threeword"><a href="${pageContext.request.contextPath}/RouteServlet?method=findByPage&pageNum=${pb.pageNum + 1}&pageSize=10">下一页</a>
                                    </li>

                                </c:if>
                            </ul>
                        </div>
                    </div>
                    <%--线路列表--%>
                </div>
            </div>
        </div>

        <!--引入头部-->
        <%@include file="footer.jsp" %>
    </body>
</html>
