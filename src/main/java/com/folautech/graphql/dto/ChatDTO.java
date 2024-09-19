package com.folautech.graphql.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.folautech.graphql.entities.message.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ChatDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String uuid;

    private String title;

    private List<Message> messages;

    private boolean deleted;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
