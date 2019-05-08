package com.leigq.www.elasticsearch;

import com.leigq.www.elasticsearch.dao.ItemRepository;
import com.leigq.www.elasticsearch.entity.Item;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.metrics.avg.InternalAvg;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Elasticsearch增删改查测试，其中查询为重点
 * <br/>
 * 参考：<a href='https://blog.csdn.net/chen_2890/article/details/83895646'>SpringBoot整合Elasticsearch</a>
 * <p>
 * 创建人：LeiGQ <br>
 * 创建时间：2019-05-08 16:12 <br>
 * <p>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注： <br>
 * </p>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ElasticsearchApplicationTests {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /*↓↓↓↓↓↓↓↓↓↓↓↓↓↓索引 [类似 MySQL 数据库] ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓*/

    /**
     * 创建并映射索引
     * <br>创建人： leiGQ
     * <br>创建时间： 2019-05-08 14:06
     * <p>
     * 修改人： <br>
     * 修改时间： <br>
     * 修改备注： <br>
     * </p>
     * <br>
     */
    @Test
    public void createIndex() {
        // 创建索引
        elasticsearchTemplate.createIndex(Item.class);
        // 映射索引，如果只执行创建的话，索引的mappings里面会为空
        elasticsearchTemplate.putMapping(Item.class);
    }

    /**
     * 删除索引
     * <br>创建人： leiGQ
     * <br>创建时间： 2019-05-08 14:21
     * <p>
     * 修改人： <br>
     * 修改时间： <br>
     * 修改备注： <br>
     * </p>
     * <br>
     */
    @Test
    public void deleteIndex() {
        elasticsearchTemplate.deleteIndex(Item.class);
    }

    /*↓↓↓↓↓↓↓↓↓↓↓↓↓↓文档 [类似 MySQL 一行数据] ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓*/

    @Autowired
    private ItemRepository itemRepository;

    /*↓↓↓↓↓↓↓↓新增↓↓↓↓↓↓↓↓*/
    @Test
    public void insertDocument() {
        final Item item = new Item(1L, "乐事原味薯片", "零食", "乐事",
                3.0, "https://www.baidu.com");
        itemRepository.save(item);
    }

    @Test
    public void insertDocuments() {
        final ArrayList<Item> items = Lists.newArrayList();
        items.add(new Item(2L, "乐事黄瓜味薯片", "零食", "乐事",
                4.0, "https://www.baidu.com"));
        items.add(new Item(3L, "喜之郎果冻", "零食", "喜之郎",
                6.0, "https://www.baidu.com"));
        // 接收对象集合，实现批量新增
        itemRepository.saveAll(items);
    }

    /*↓↓↓↓↓↓↓↓修改↓↓↓↓↓↓↓↓*/
    @Test
    public void updateDocument() {
        /*
         * elasticsearch中本没有修改，它的修改原理是该是先删除在新增
         * 修改和新增是同一个接口，区分的依据就是id。
         * */
        final Item item = new Item(1L, "乐事新原味薯片", "零食", "乐事",
                3.5, "https://www.baidu.com");
        itemRepository.save(item);
    }

    /*↓↓↓↓↓↓↓↓查询↓↓↓↓↓↓↓↓*/

    /*↓↓自定义方法↓↓*/
    @Test
    public void selectDocument() {
        // byId
        final Optional<Item> optionalItem = itemRepository.findById(1L);
        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            System.out.println(item);
        }

        // 自定义方法， 类似 JPA ，spring会根据方法名自动生成查询方法
        final List<Item> items = itemRepository.findByPriceBetween(5.0, 7.0);
        System.out.println(items);
    }

    @Test
    public void selectDocuments() {
        // 对某字段排序查找所有 Sort.by("price").descending() 降序
        final Iterable<Item> items = itemRepository.findAll(Sort.by("price").descending());
        items.forEach(System.out::println);
    }

    /*↓↓自定义条件查询↓↓*/
    @Test
    public void testMatchQuery() {
        // 自定义查询 matchQuery底层采用的是词条匹配查询
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
        queryBuilder.withQuery(QueryBuilders.matchQuery("title", "乐事"));
        // 搜索获取结果
        final Page<Item> itemPage = itemRepository.search(queryBuilder.build());
        // 总条数
        itemPage.getTotalElements();
        // 总页数
        itemPage.getTotalPages();
    }

    @Test
    public void testTermQuery() {
        // 自定义查询 termQuery:功能更强大，除了匹配字符串以外，还可以匹配 int/long/double/float/....
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
        queryBuilder.withQuery(QueryBuilders.termQuery("price", 6.0));
        // 搜索获取结果
        final Page<Item> itemPage = itemRepository.search(queryBuilder.build());
        for (Item item : itemPage) {
            System.out.println(item);
        }
    }

    @Test
    public void testBooleanQuery() {
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
        queryBuilder.withQuery(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("price", 6.0)));
        // 搜索获取结果
        final Page<Item> itemPage = itemRepository.search(queryBuilder.build());
        for (Item item : itemPage) {
            System.out.println(item);
        }
    }

    @Test
    public void testFuzzyQuery() {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        // 模糊查询
        builder.withQuery(QueryBuilders.fuzzyQuery("title", "faceoooo"));
        Page<Item> page = this.itemRepository.search(builder.build());
        for (Item item : page) {
            System.out.println(item);
        }

    }

    /**
     * 分页查询
     */
    @Test
    public void searchByPage() {
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
        queryBuilder.withQuery(QueryBuilders.termQuery("category", "零食"));
        // 分页：
        int page = 0;
        int size = 2;
        queryBuilder.withPageable(PageRequest.of(page, size));

        // 搜索，获取结果
        Page<Item> items = this.itemRepository.search(queryBuilder.build());
        // 总条数
        long total = items.getTotalElements();
        System.out.println("总条数 = " + total);
        // 总页数
        System.out.println("总页数 = " + items.getTotalPages());
        // 当前页
        System.out.println("当前页：" + items.getNumber());
        // 每页大小
        System.out.println("每页大小：" + items.getSize());

        for (Item item : items) {
            System.out.println(item);
        }
    }

    /**
     * 排序查询
     */
    @Test
    public void searchAndSort() {
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
        queryBuilder.withQuery(QueryBuilders.termQuery("category", "零食"));

        // 排序
        queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.ASC));

        // 搜索，获取结果
        Page<Item> items = this.itemRepository.search(queryBuilder.build());
        // 总条数
        long total = items.getTotalElements();
        System.out.println("总条数 = " + total);

        for (Item item : items) {
            System.out.println(item);
        }
    }

    /**
     * 聚合
     * （1）统计某个字段的数量
     *   ValueCountBuilder vcb=  AggregationBuilders.count("count_uid").field("uid");
     * （2）去重统计某个字段的数量（有少量误差）
     *  CardinalityBuilder cb= AggregationBuilders.cardinality("distinct_count_uid").field("uid");
     * （3）聚合过滤
     * FilterAggregationBuilder fab= AggregationBuilders.filter("uid_filter").filter(QueryBuilders.queryStringQuery("uid:001"));
     * （4）按某个字段分组
     * TermsBuilder tb=  AggregationBuilders.terms("group_name").field("name");
     * （5）求和
     * SumBuilder  sumBuilder=	AggregationBuilders.sum("sum_price").field("price");
     * （6）求平均
     * AvgBuilder ab= AggregationBuilders.avg("avg_price").field("price");
     * （7）求最大值
     * MaxBuilder mb= AggregationBuilders.max("max_price").field("price");
     * （8）求最小值
     * MinBuilder min=	AggregationBuilders.min("min_price").field("price");
     * （9）按日期间隔分组
     * DateHistogramBuilder dhb= AggregationBuilders.dateHistogram("dh").field("date");
     * （10）获取聚合里面的结果
     * TopHitsBuilder thb=  AggregationBuilders.topHits("top_result");
     * （11）嵌套的聚合
     * NestedBuilder nb= AggregationBuilders.nested("negsted_path").path("quests");
     * （12）反转嵌套
     * AggregationBuilders.reverseNested("res_negsted").path("kps ");
     */
    @Test
    public void testAgg(){
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 不查询任何结果
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{""}, null));
        // 1、添加一个新的聚合，聚合类型为terms，聚合名称为brands，聚合字段为brand
        queryBuilder.addAggregation(
                AggregationBuilders.terms("brands").field("brand"));
        // 2、查询,需要把结果强转为AggregatedPage类型
        AggregatedPage<Item> aggPage = (AggregatedPage<Item>) this.itemRepository.search(queryBuilder.build());
        // 3、解析
        // 3.1、从结果中取出名为brands的那个聚合，
        // 因为是利用String类型字段来进行的term聚合，所以结果要强转为StringTerm类型
        StringTerms agg = (StringTerms) aggPage.getAggregation("brands");
        // 3.2、获取桶
        List<StringTerms.Bucket> buckets = agg.getBuckets();
        // 3.3、遍历
        for (StringTerms.Bucket bucket : buckets) {
            // 3.4、获取桶中的key，即品牌名称
            System.out.println(bucket.getKeyAsString());
            // 3.5、获取桶中的文档数量
            System.out.println(bucket.getDocCount());
        }

    }

    /**
     * 嵌套聚合，求平均值
     */
    @Test
    public void testSubAgg(){
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 不查询任何结果
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{""}, null));
        // 1、添加一个新的聚合，聚合类型为terms，聚合名称为brands，聚合字段为brand
        queryBuilder.addAggregation(
                AggregationBuilders.terms("brands").field("brand")
                        .subAggregation(AggregationBuilders.avg("priceAvg").field("price")) // 在品牌聚合桶内进行嵌套聚合，求平均值
        );
        // 2、查询,需要把结果强转为AggregatedPage类型
        AggregatedPage<Item> aggPage = (AggregatedPage<Item>) this.itemRepository.search(queryBuilder.build());
        // 3、解析
        // 3.1、从结果中取出名为brands的那个聚合，
        // 因为是利用String类型字段来进行的term聚合，所以结果要强转为StringTerm类型
        StringTerms agg = (StringTerms) aggPage.getAggregation("brands");
        // 3.2、获取桶
        List<StringTerms.Bucket> buckets = agg.getBuckets();
        // 3.3、遍历
        for (StringTerms.Bucket bucket : buckets) {
            // 3.4、获取桶中的key，即品牌名称  3.5、获取桶中的文档数量
            System.out.println(bucket.getKeyAsString() + "，共" + bucket.getDocCount() + "个");

            // 3.6.获取子聚合结果：
            InternalAvg avg = (InternalAvg) bucket.getAggregations().asMap().get("priceAvg");
            System.out.println("平均售价：" + avg.getValue());
        }

    }

    /*↓↓↓↓↓↓↓↓删除↓↓↓↓↓↓↓↓*/
    @Test
    public void deleteDocument() {
        itemRepository.deleteById(1L);
    }

}
