package com.leigq.www.elasticsearch.dao;

import com.leigq.www.elasticsearch.entity.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * ItemRepository，用法类似 springJPA
 * <p>
 * 创建人：LeiGQ <br>
 * 创建时间：2019-05-08 14:32 <br>
 * <p>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注： <br>
 * </p>
 */
public interface ItemRepository extends ElasticsearchRepository<Item, Long> {

    /**
     * 查询价格区间商品
     * <br>创建人： leiGQ
     * <br>创建时间： 2019-05-08 15:19
     * <p>
     * 修改人： <br>
     * 修改时间： <br>
     * 修改备注： <br>
     * </p>
     * <br>
     *
     */
    List<Item> findByPriceBetween(Double startPrice, Double endPrice);

}
