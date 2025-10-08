package com.darven.domain.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Darven
 * @createTime: 2025-10-08  16:49
 * @description: TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AiChatReqVO {

    @NotBlank(message = "用户消息不能为空")
    private String message;

    /**
     * 对话 ID
     */
    private String chatId;

    /**
     * 联网搜索
     */
    private Boolean networkSearch = false;

    @NotBlank(message = "调用的 AI 大模型名称不能为空")
    private String modelName;

    /**
     * 温度值，默认为 0.7
     */
    private Double temperature = 0.7;
}
