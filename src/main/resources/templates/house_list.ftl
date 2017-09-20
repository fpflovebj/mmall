<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
    <script type="text/javascript">
        function toUrl(url){
            window.location.href = url;
            return;
        }

        function doSearch(){
            $('#findFrom').submit();
        }
    </script>

    <style type="">
        br{
            display:none;
        }
    </style>
</head>
<body>
<div id="navbar" class="wrap">
    <dl class="search clearfix">
        <s:form method="get" action="house!findAll.action" id='findFrom'>
            <dt>
                <ul>
                    <li class="bold">房屋信息</li>
                    <li>
                        标题：
                        <s:textfield name="house.title" id="title" cssClass="text" />
                        <label class="ui-blue">
                            <input type="button" onclick="doSearch();" name="search" value="搜索房屋" />
                        </label>
                    </li>
                </ul>
            </dt>
            <dd>
                <ul>
                    <li class="first">
                        价格
                    </li>
                    <li>
                        <s:select list="#{'<=1000':'1000元及以下', '>1000,<2000':'1000元—2000元', '>=2000':'2000元及以上'}" listKey="key" listValue="value" headerKey="" headerValue="不限" name="price" value="price"></s:select>
                    </li>
                </ul>
            </dd>
            <dd>
                <ul>
                    <li class="first">房屋位置</li>
                    <li>
                        <%-- <s:doubleselect list="disList" listKey="id" listValue="name" headerKey="" headerValue="不限" name="districtId" doubleList="streetMap.get(top.id)" doubleListKey="id" doubleListValue="name" doubleName="house.streetId" doubleHeaderKey="" doubleHeaderValue="不限">

                    </s:doubleselect> --%>
                    </li>
                </ul>
            </dd>
            <dd>
                <ul>
                    <li class="first">房型</li>
                    <li>
                        <%-- <s:select list="typeList" name="house.typeId" listKey="id" listValue="name" headerKey="" headerValue="不限">
                    </s:select> --%>
                    </li>
                </ul>
            </dd>
            <dd>
                <ul>
                    <li class="first">
                        面积
                    </li>
                    <li>
                        <s:select list="#{'<=40':'40及以下', '>40,<80':'40-80', '>=80':'80及以上'}" listKey="key" listValue="value" headerKey="" headerValue="不限" name="floorage" value="floorage"></s:select>
                    </li>
                </ul>
            </dd>
        </s:form>
    </dl>
</div>
</body>
</html>