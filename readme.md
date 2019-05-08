# Elasticsearch 入门教程

**此项目是我看完下面几篇文章之后写的 Elasticsearch 测试案例。**

> 百度百科：https://baike.baidu.com/item/elasticsearch/3411206?fr=aladdin

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ElasticSearch 是一个基于 Lucene 的搜索服务器。它提供了一个分布式多用户能力的全文搜索引擎，基于 RESTful web 接口。Elasticsearch 是用Java开发的，并作为 Apache 许可条款下的开放源码发布，是当前流行的企业级搜索引擎。设计用于云计算中，能够达到实时搜索，稳定，可靠，快速，安装使用方便。

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;我们建立一个网站或应用程序，并要添加搜索功能，但是想要完成搜索工作的创建是非常困难的。我们希望搜索解决方案要运行速度快，我们希望能有一个零配置和一个完全免费的搜索模式，我们希望能够简单地使用 JSON 通过 HTTP 来索引数据，我们希望我们的搜索服务器始终可用，我们希望能够从一台开始并扩展到数百台，我们要实时搜索，我们要简单的多租户，我们希望建立一个云的解决方案。因此我们利用 Elasticsearch 来解决所有这些问题及可能出现的更多其它问题。

## 全文搜索引擎 Elasticsearch 入门教程
请前往：http://www.ruanyifeng.com/blog/2017/08/elasticsearch.html `建议先看这篇文章`

## Elasticsearch学习，请先看这一篇
请前往：https://blog.csdn.net/makang110/article/details/80596017

## Elasticsearch环境搭建和介绍（Windows）
请前往：https://blog.csdn.net/chen_2890/article/details/83757022
`Linux的搭建请自行百度`

上文中使用的版本：

- `Elasticsearch：6.2.4`
- `Ik分词器：6.2.4`
- `可视化客户端工具：Elasticsearch-head` 
    - GitHub：https://github.com/mobz/elasticsearch-head

我测试使用下面的版本，注意版本全部保持一致：
- `springBoot：2.0.9.RELEASE` 对应 `Elasticsearch：5.6.16`

    ![img](https://img-blog.csdnimg.cn/20190508162742990.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM0ODQ1Mzk0,size_16,color_FFFFFF,t_70)

- `Elasticsearch` 5.6.16

    ![img](https://img-blog.csdnimg.cn/20190508161528848.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM0ODQ1Mzk0,size_16,color_FFFFFF,t_70)

- `Ik分词器：5.6.16`
    - GitHub：https://github.com/medcl/elasticsearch-analysis-ik/releases

- `可视化客户端工具`  
	- `Elasticsearch-head` GitHub：https://github.com/mobz/elasticsearch-head 
	
	    效果如下：

        ![img](https://img-blog.csdnimg.cn/20190508162916671.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM0ODQ1Mzk0,size_16,color_FFFFFF,t_70)

    - `Kibana 5.6.16    `

		效果如下：

        ![img](https://img-blog.csdnimg.cn/20190508161611102.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM0ODQ1Mzk0,size_16,color_FFFFFF,t_70)

        Kibana 的使用看这里：[Kibana可视化Elasticsearch中的数据](https://blog.csdn.net/jinking01/article/details/79983003)

	关于可视化客户端工具还有其他的，可以看看这个：[一文上手 Elasticsearch常用可视化管理工具](https://www.jianshu.com/p/54e04b5b5ce2)

## Elasticsearch的使用场景深入详解

请前往：https://blog.csdn.net/laoyang360/article/details/52227541

## SpringBoot整合Elasticsearch

请前往：https://blog.csdn.net/chen_2890/article/details/83895646

测试案例源码：https://github.com/MRLEILOVE/springboot-elasticsearch.git

> ## 相关链接
> - Elasticsearch官网：https://www.elastic.co/cn/products/elasticsearch
> - elasticsearch中文社区：https://elasticsearch.cn
> - IK分词器GitHub：https://github.com/medcl/elasticsearch-analysis-ik/releases
> - head插件GitHub：https://github.com/mobz/elasticsearch-head