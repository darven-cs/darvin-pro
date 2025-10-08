package com.darven.service;

import com.darven.domain.vo.NewChatReqVO;
import com.darven.domain.vo.NewChatRspVO;
import com.darven.utils.Response;

/**
 * @author: Darven
 * @createTime: 2025-10-08  16:36
 * @description: TODO
 */
public interface ChatService {

    /**
     * 新建对话
     * @param newChatReqVO
     * @return
     */
    Response<NewChatRspVO> newChat(NewChatReqVO newChatReqVO);
}
