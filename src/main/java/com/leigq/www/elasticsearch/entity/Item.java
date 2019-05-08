package com.leigq.www.elasticsearch.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 商品实体
 * <p>
 * 创建人：LeiGQ <br>
 * 创建时间：2019-05-08 11:32 <br>
 * <p>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注： <br>
 * </p>
 * <span>@Document</span> 作用在类，标记实体类为文档对象，一般只用indexName、type两个属性
 * <ul>
 *     <li>indexName：对应索引库名称</li>
 *     <li>type：对应在索引库中的类型</li>
 *     <li>shards：分片数量，默认5</li>
 *     <li>replicas：副本数量，默认1</li>
 * </ul>
 * <span>@Id</span> 作用在成员变量，标记一个字段作为id主键
 * <br/>
 * <span>@Field</span> 作用在成员变量，标记为文档的字段，并指定字段映射属性：
 * <ul>
 *     <li>type：字段类型，是枚举：FieldType，可以是text、long、short、date、integer、object等</li>
 *     <ul>
 *         <li>text：存储数据时候，会自动分词，并生成索引</li>
 *         <li>keyword：存储数据时候，不会分词建立索引</li>
 *         <li>Numerical：数值类型，分两类</li>
 *         <ul>
 *             <li>基本数据类型：long、interger、short、byte、double、float、half_float</li>
 *             <li>浮点数的高精度类型：scaled_float</li>
 *             <ul>
 *              <li>需要指定一个精度因子，比如10或100。elasticsearch会把真实值乘以这个因子后存储，取出时再还原。</li>
 *             </ul>
 *         </ul>
 *         <li>Date：日期类型</li>
 *         <ul>
 *             <li>elasticsearch可以对日期格式化为字符串存储，但是建议我们存储为毫秒值，存储为long，节省空间。</li>
 *         </ul>
 *     </ul>
 *     <li>index：是否索引，布尔类型，默认是true</li>
 *     <li>store：是否存储，布尔类型，默认是false</li>
 *     <li>analyzer：分词器名称，这里的`ik_max_word`即使用ik分词器</li>
 * </ul>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "item", type = "docs")
public class Item {

    /*
    * @Description: @Id注解必须是org.springframework.data.annotation.Id包下的
    * */
    /**主键 ID */
    @Id
    private Long id;

    /**标题*/
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String title;

    /**分类*/
    @Field(type = FieldType.Keyword)
    private String category;

    /**品牌*/
    @Field(type = FieldType.Keyword)
    private String brand;

    /**价格*/
    @Field(type = FieldType.Double)
    private Double price;

    /**图片地址*/
    @Field(index = false, type = FieldType.Keyword)
    private String images;
}
