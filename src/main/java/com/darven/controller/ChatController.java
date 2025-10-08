package com.darven.controller;

import com.darven.aspect.ApiOperationLog;
import com.darven.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Darven
 * @createTime: 2025-10-08  15:49
 * @description: 对话
 */
@Slf4j
@RestController
@RequestMapping("/chat")
public class ChatController {

    @PostMapping("/new")
    @ApiOperationLog(description = "新建对话")
    public Response<?> newChat() {
        return Response.success();
    }

}
