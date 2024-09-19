package com.folautech.graphql.entities.chat;

import com.folautech.graphql.dto.ChatDTO;

public interface ChatService {
    ChatDTO getById(Long id);
}
