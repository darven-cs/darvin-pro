package com.darven.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Darven
 * @createTime: 2025-10-08  16:35
 * @description: TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewChatRspVO {
    /**
     * 摘要
     */
    private String summary;

    /**
     * 对话 UUID
     */
    private String uuid;
}
