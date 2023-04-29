package com.sena.inventarioback.utils.response;
import com.sena.inventarioback.utils.response.DefaultResponse.MESSAGETYPES;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message{
	
    private String messageDesc;
    private MESSAGETYPES messageType;

	public String getMessageType() {
		return messageType.value();
	} 
}
