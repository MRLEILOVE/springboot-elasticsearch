# Elasticsearch 入门教程

此项目是我看完下面几篇文章之后写的 Elasticsearch 测试案例。

## 全文搜索引擎 Elasticsearch 入门教程
`建议先看这篇文章`：[全文搜索引擎 Elasticsearch 入门教程 - 阮一峰](http://www.ruanyifeng.com/blog/2017/08/elasticsearch.html)

## Elasticsearch环境搭建和介绍（Windows）
[Elasticsearch环境搭建和介绍（Windows）](https://blog.csdn.net/chen_2890/article/details/83757022)
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