package com.darven.service;

import com.darven.domain.dto.SearchResultDTO;

import java.util.List;

/**
 * @author: Darven
 * @createTime: 2025-10-08  18:01
 * @description: TODO
 */
public interface SearXNGService {

    /**
     * 调用 SearXNG Api, 获取搜索列表
     * @param query 搜索关键词
     * @return
     */
    List<SearchResultDTO> search(String query);
}
