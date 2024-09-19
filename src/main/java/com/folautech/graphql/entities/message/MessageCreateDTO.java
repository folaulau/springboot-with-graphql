package com.folautech.graphql.entities.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class MessageCreateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String message;

    private Long userId;

    private Long chatId;


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

