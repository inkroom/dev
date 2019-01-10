<%--
  Created by IntelliJ IDEA.
  User: inkbox
  Date: 18-2-23
  Time: 下午10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8" />
    <title></title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../../../resources/js/prodAndTech.js"></script>
    <script src="../../../resources/js/menu-left.js"></script>
    <link rel="stylesheet" href="../../../resources/css/home.css" />
    <link rel="stylesheet" href="../../../resources/css/menu.css" />
    <link rel="stylesheet" href="../../../resources/css/under.css" />
    <link rel="stylesheet" type="text/css" href="../../../resources/css/menu-left.css" />
    <link rel="stylesheet" type="text/css" href="../../../resources/css/prodAndTech.css" />



</head>

<body>
<div class="top" id="topb">
    <img src="../../../resources/img/topLfw.jpg" style="float: left;">
    <img src="../../../resources/img/logo3.png" style="float: left;padding:20px 0px;margin-left:-16px ;">
    <img src="../../../resources/img/topRtw.jpg" style="float: right;">
    <div class="top1">
        <div class="menu">
            <ul>
                <li>
                    <a href="../home.html" class="topcolor">网站首页</a>
                </li>
                <li>
                    <a href="../about/aboutUs.html" class="topcolor">关于我们</a>
                    <ul>
                        <li>
                            <a href="#">本部介绍</a>
                        </li>
                        <li>
                            <a href="#">研究院介绍</a>
                        </li>
                        <li>
                            <a href="#">组织架构</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="../prodAndTech/prodAndTech.html" class="topcolor">技术产品</a>
                    <ul>
                        <li>
                            <a href="../prodAndTech/technology.html" onclick="this.href='prodAndTech.html?'+href">技术</a>
                            <ul>
                                <li>
                                    <a>技术源头</a>
                                </li>
                                <li>
                                    <a>xxxxx</a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="../prodAndTech/product.html" onclick="this.href='prodAndTech.html?'+href">产品</a>
                            <ul>
                                <li>
                                    <a href="#">无人机载荷系列</a>
                                </li>
                                <li>
                                    <a href="#">光谱系列</a>
                                </li>
                                <li>
                                    <a href="#">热红外系列</a>
                                </li>
                                <li>
                                    <a href="#">三维模型数据</a>
                                </li>
                                <li>
                                    <a href="#">xxxx系列</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#" class="topcolor">合作单位</a>
                    <ul>
                        <li>
                            <a href="#">富邦股份</a>
                        </li>
                        <li>
                            <a href="#">上海恒光</a>
                        </li>
                        <li>
                            <a href="#">杭州炽橙</a>
                        </li>
                        <li>
                            <a href="#">国家超算深圳中心</a>
                        </li>
                        <li>
                            <a href="#">奥谱天成</a>
                        </li>
                        <li>
                            <a href="#">成都东软</a>
                        </li>
                        <li>
                            <a href="#">西安航空</a>
                        </li>
                        <li>
                            <a href="#">沈阳农大</a>
                        </li>
                    </ul>

                </li>
                <li>
                    <a href="../OSdata/OSdata.html" class="topcolor">开源数据</a>
                    <ul>
                        <li>
                            <a href="#">光学</a>
                        </li>
                        <li>
                            <a href="#">电子学</a>
                        </li>
                        <li>
                            <a href="#">机械</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="../news/news.html" class="topcolor">新闻中心</a>

                </li>
                <li>
                    <a href="../PartyAndUnion/partyAndUnion.html" class="topcolor">党群工会</a>
                    <ul>
                        <li>
                            <a href="#">党建</a>
                        </li>
                        <li>
                            <a href="#">工会</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="../recruit/recruit.html" class="topcolor">招贤纳士</a>
                    <ul>
                        <li>
                            <a href="#">硬件</a>
                        </li>
                        <li>
                            <a href="#">软件</a>
                        </li>
                        <li>
                            <a href="#">工程</a>
                        </li>
                        <li>
                            <a href="#">其他</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="../GoInto/goInto.html" class="topcolor">走进大江东</a>
                    <ul>
                        <li>
                            <a href="#">人才引进政策</a>
                        </li>
                        <li>
                            <a href="#">区域规划</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="../contactUs/contactUs.html" class="topcolor">联系我们</a>
                </li>
            </ul>

            </ul>
        </div>
    </div>

</div>
<div class="top-img" id="top-img">
    <img src="../../../resources/img/img2.jpg">
</div>

<div class="technical-middle">
    <div class="pt-menu-left">
        <ul id="accordion" class="accordion">
            <li>
                <div class="link" style="background: #C9151E;">
                    <a>开源数据</a>
                </div>
            </li>
            <li>
                <div class="link">
                    <a href="../prodAndTech/technology.html" target="mainFrame">光学</a>
                </div>
                <ul class="submenu">
                </ul>
            </li>
            <li>
                <div class="link">
                    <a href="../prodAndTech/technology.html" target="mainFrame">电子学</a>
                </div>
                <ul class="submenu">
                </ul>
            </li>
            <li>
                <div class="link">
                    <a href="../prodAndTech/technology.html" target="mainFrame">机械</a>
                </div>
                <ul class="submenu">
                </ul>
            </li>
        </ul>
    </div>

    <iframe class="mainFrame" name="mainFrame" scrolling="no" src="../prodAndTech/technology.html" frameborder="0" id="external-frame" onload="this.height=mainFrame.document.body.scrollHeight"></iframe>
    <div style="clear:both;"> </div>
</div>

<iframe style="width: 100%;" name="" scrolling="no" src="../under.html" frameborder="0" id=""></iframe>

<script>
    var url = location.search.substring(1);
    if(url) document.getElementById('external-frame').src = url //如果存在iframe容器，这些代码放在iframe下，改为if(url)document.getElementById('iframe的id').src=url
</script>
</body>

</html>