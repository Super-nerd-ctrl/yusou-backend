package com.yutong.springbootinit.datasource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author xiaoyu
 * @date 2023/3/22
 * @apiNote 数据源接口（新接入的数据必须实现）
 */
public interface DataSource<T> {

    Page<T> doSearch(String searchText, long pageNum, long pageSize);
}
