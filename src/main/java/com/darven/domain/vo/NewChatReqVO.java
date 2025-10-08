package com.darven.domain.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Darven
 * @createTime: 2025-10-08  16:34
 * @description: 新建对话请求参数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewChatReqVO {

    @NotBlank(message = "用户消息不能为空")
    private String message;

}
