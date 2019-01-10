<%--
  Created by IntelliJ IDEA.
  User: inkbox
  Date: 18-2-23
  Time: 下午10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8" />
    <title>首页</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="../../resources/css/home.css" />
    <link rel="stylesheet" href="../../resources/css/menu.css" />
    <link rel="stylesheet" href="../../resources/css/under.css" />
    <style type="text/css">

    </style>
</head>

<body>
<div class="top">
    <img src="../../resources/img/topLfb.jpg" style="float: left;">
    <img src="../../resources/img/logow.png" style="float: left;padding:20px 0px;margin-left:-16px ;">
    <img src="../../resources/img/topRtb.jpg" style="float: right;">
    <div class="top1">
        <div class="menu">
            <ul>
                <li>
                    <a href="home.html" class="topcolor">网站首页</a>
                </li>
                <li>
                    <a href="about/aboutUs.html" class="topcolor" onclick ="changeUrl("ssss")">关于我们</a>
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
                    <a href="prodAndTech/prodAndTech.html" class="topcolor">技术产品</a>
                    <ul>
                        <li>
                            <a href="prodAndTech/technology.html" onclick="this.href='prodAndTech/prodAndTech.html?'+href">技术</a>
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
                            <a href="prodAndTech/product.html" onclick="this.href='prodAndTech/prodAndTech.html?'+href">产品</a>
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
                    <a href="OSdata/OSdata.html" class="topcolor">开源数据</a>
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
                    <a href="news/news.html" class="topcolor">新闻中心</a>

                </li>
                <li>
                    <a href="PartyAndUnion/partyAndUnion.html" class="topcolor">党群工会</a>
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
                    <a href="recruit/recruit.html" class="topcolor">招贤纳士</a>
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
                    <a href="GoInto/goInto.html" class="topcolor">走进大江东</a>
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
                    <a href="contactUs/contactUs.html" class="topcolor">联系我们</a>
                </li>
            </ul>

            </ul>
        </div>
    </div>

</div>
<a href="http://www.baidu.com"><img src="../../resources/img/search1.png" style="height: 6%;z-index: 1;position:absolute;margin-left:90% ;margin-top:2%;float: right"></a>
<div id="myCarousel" class="carousel slide" style="margin-top: 6px;">

    <!-- 轮播（Carousel）指标 -->
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>
    <!-- 轮播（Carousel）项目 -->
    <div class="carousel-inner">
        <div class="item active">
            <img src="../../resources/img/img12.jpg" alt="First slide">
        </div>
        <div class="item">
            <img src="../../resources/img/img12.jpg" alt="Second slide">
        </div>
        <div class="item">
            <img src="../../resources/img/img12.jpg" alt="Third slide">
        </div>
    </div>

    <!-- 轮播（Carousel）导航 -->
    <a class="carousel-control left" href="#myCarousel" data-slide="prev">
        <img src="../../resources/img/turnLf.png" style="width: 30%; padding-top:80%">
    </a>

    <a class="carousel-control right" href="#myCarousel" data-slide="next">
        <img src="../../resources/img/turnRt.png" style="width: 30%; padding-top:80%">
    </a>
</div>

<div class="middle">
    <div class="middle-bg">
        <div class="middle-info">
            <div class="middle-info-top">
                <div class="middle-info-top1">
                    <img src="../../resources/img/logo1.jpg">
                    <a style="">综合新闻</a>
                </div>
                <a>更多</a>
            </div>
            <div class="middle-img">
                <img src="../../resources/img/img1-1.jpg">
            </div>

            <div class="middle-txt">
                <ul>
                    <li>
                        <a href="#">》12312312312312311111111111啊啊啊啊啊啊啊啊</a>
                    </li>
                    <li>
                        <a href="#">》12312312312312311111111111啊啊啊啊啊啊啊啊</a>
                    </li>
                    <li>
                        <a href="#">》12312312312312311111111111啊啊啊啊啊啊啊啊</a>
                    </li>
                    <li>
                        <a href="#">》12312312312312311111111111啊啊啊啊啊啊啊啊</a>
                    </li>
                    <li>
                        <a href="#">》12312312312312311111111111啊啊啊啊啊啊啊啊</a>
                    </li>
                    <li>
                        <a href="#">》12312312312312311111111111啊啊啊啊啊啊啊啊</a>
                    </li>
                </ul>
            </div>

        </div>
    </div>
    <div class="middle-bg">
        <div class="middle-info">
            <div class="middle-info-top">
                <div class="middle-info-top1">
                    <img src="../../resources/img/logo1.jpg">
                    <a>科研动态</a>
                </div>
                <a>更多</a>
            </div>
            <div class="middle-img">
                <img src="../../resources/img/img1-1.jpg">
            </div>

            <div class="middle-txt">
                <ul>
                    <li>
                        <a href="#">》12312312312312311111111111啊啊啊啊啊啊啊啊</a>
                    </li>
                    <li>
                        <a href="#">》12312312312312311111111111啊啊啊啊啊啊啊啊</a>
                    </li>
                    <li>
                        <a href="#">》12312312312312311111111111啊啊啊啊啊啊啊啊</a>
                    </li>
                    <li>
                        <a href="#">》12312312312312311111111111啊啊啊啊啊啊啊啊</a>
                    </li>
                    <li>
                        <a href="#">》12312312312312311111111111啊啊啊啊啊啊啊啊</a>
                    </li>
                    <li>
                        <a href="#">》12312312312312311111111111啊啊啊啊啊啊啊啊</a>
                    </li>
                </ul>
            </div>

        </div>

    </div>
    <div class="middle-bg">
        <div class="middle-info">
            <div class="middle-info-top">
                <div class="middle-info-top1">
                    <img src="../../resources/img/logo1.jpg">
                    <a>人才招聘</a>
                </div>
                <a>更多</a>
            </div>
            <div class="middle-img">
                <img src="../../resources/img/img1-1.jpg">
            </div>

            <div class="middle-txt" style="color:#C9C9C9;">
                <ul>
                    <li>
                        <a href="#">》12312312312312311111111111啊啊啊啊啊啊啊啊</a>
                    </li>
                    <li>
                        <a href="#">》12312312312312311111111111啊啊啊啊啊啊啊啊</a>
                    </li>
                    <li>
                        <a href="#">》12312312312312311111111111啊啊啊啊啊啊啊啊</a>
                    </li>
                    <li>
                        <a href="#">》12312312312312311111111111啊啊啊啊啊啊啊啊</a>
                    </li>
                    <li>
                        <a href="#">》12312312312312311111111111啊啊啊啊啊啊啊啊</a>
                    </li>
                    <li>
                        <a href="#">》12312312312312311111111111啊啊啊啊啊啊啊啊</a>
                    </li>
                </ul>
            </div>
            <div style="clear:both;"> </div>
        </div>
        <div style="clear:both;"> </div>
    </div>
    <div style="clear:both;"> </div>
</div>

<iframe width="100%" name="" scrolling="no" src="under.html" frameborder="0" id="" ></iframe>

<script>
    //轮播间隔
    $('.carousel').carousel({
        interval: 6000
    });
</script>

<script>
    function changeUrl(url) {
        $("external-frame").attr("src"."url");
    }
</script>

</body>

</html>
