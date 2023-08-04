package com.sena.inventarioback.utils.response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonSetter;

import lombok.Data;
import lombok.NoArgsConstructor;

//version 0.0.3 12/07/2023
@Data
@NoArgsConstructor
public class DefaultResponse<T> {

  protected List<T> data = Collections.emptyList();
  protected HttpStatus status;
  protected List<Message> message;
  protected List<Error> error;
  protected MESSAGETYPES messageType = MESSAGETYPES.INFO;
  protected DATATYPE dataType = DATATYPE.LIST;
  protected Page<T> pageableInformation;

  public DefaultResponse(List<T> data, HttpStatus status, List<Message> message, List<Error> error,
 		 MESSAGETYPES messageType) {
 	 super();
 	 this.data = data;
 	 this.status = status;
 	 this.message = message;
 	 this.error = error;
 	 this.messageType = messageType;
  }

  public enum MESSAGETYPES {
 	 INFO("info"), SUCCESS("success"), WARN("warn"), ERROR("error");

 	 private String value;
 	 MESSAGETYPES(String value) {
 		 this.value = value;
 	 }

 	 public String value() {
 		 return this.value;
 	 }

  }

  public enum DATATYPE {
 	 LIST("List"), OBJECT("Object");

 	 private String value;

 	 DATATYPE(String value) {
 		 this.value = value;

 	 }

 	 public String value() {
 		 return this.value;
 	 }

  }

  public  enum DEFAULTMESSAGES {
 	 SUCCESS_MESSAGE("Operación terminada correctamente"),
 	 NOT_INFO_FOUND_MESSAGE("No se encontro información relacionada"),
 	 DATA_SAVED_MESSAGE("Información Guardada con éxito!"),
 	 NOT_DATA_SAVED_MESSAGE("Información no almacenada"),
 	 ENTITY_EXIST("La entidad ya existe en la base de datos"),
 	 INTERNAL_SERVER_ERROR("Error interno del sistema"),
 	 INFO_UPDATED_MESSAGE("Información actualizada con éxito!");


 	 private String value;

 	 DEFAULTMESSAGES(String value) {
 		 this.value = value;

 	 }

 	 public String value() {
 		 return this.value;
 	 }

  }

  public static<T> ResponseEntity<DefaultResponse<T>> onThrow500ErrorResponse(List<String> errorMessage) {
 	 var messageResult = new DefaultResponse<T>();
 	 messageResult.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
 	 messageResult.setMessageType(MESSAGETYPES.ERROR);
 	messageResult.setMessage(DEFAULTMESSAGES.INTERNAL_SERVER_ERROR.value());
 	 messageResult.setErrorStringList(errorMessage);
 	 return new ResponseEntity<>(messageResult, messageResult.catchHttpStatus());

  }


  public static <T>ResponseEntity<DefaultResponse<T>> onThrow500ErrorListResponse(List<Error> errorMessage) {
 	 var messageResult = new DefaultResponse <T>();
 	 messageResult.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
 	 messageResult.setMessageType(MESSAGETYPES.ERROR);
 	messageResult.setMessage(DEFAULTMESSAGES.INTERNAL_SERVER_ERROR.value());
 	 messageResult.setError(errorMessage);
 	 return new ResponseEntity<>(messageResult, messageResult.catchHttpStatus());

  }

  public static<T> ResponseEntity<DefaultResponse<T>> onThrow500ErrorResponse(String errorMessage) {
 	 var messageResult = new DefaultResponse<T>();
 	 messageResult.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
 	 messageResult.setMessageType(MESSAGETYPES.ERROR);
 	messageResult.setMessage(DEFAULTMESSAGES.INTERNAL_SERVER_ERROR.value());
 	 messageResult.setError(errorMessage);
 	 return new ResponseEntity<>(messageResult, messageResult.catchHttpStatus());
  }

  public static<T> ResponseEntity<DefaultResponse<T>> onThrow400ResponseTypeInfo(String message) {
 	 var messageResult = new DefaultResponse<T>();
 	 messageResult.setStatus(HttpStatus.BAD_REQUEST);
 	 messageResult.setMessageType(MESSAGETYPES.INFO);
 	 messageResult.setMessage(message);
 	 return new ResponseEntity<>(messageResult, messageResult.catchHttpStatus());

  }

  public static <T>ResponseEntity<DefaultResponse<T>> onThrow400ResponseTypeError(String message) {
 	 var messageResult = new DefaultResponse<T>();
 	 messageResult.setStatus(HttpStatus.BAD_REQUEST);
 	 messageResult.setMessageType(MESSAGETYPES.ERROR);
 	 messageResult.setMessage(message);
 	 return new ResponseEntity<>(messageResult, messageResult.catchHttpStatus());

  }

  public static<T> ResponseEntity<DefaultResponse<T>> onThrow400ResponseTypeError(List<Message> message) {
 	 var messageResult = new DefaultResponse<T>();
 	 messageResult.setStatus(HttpStatus.BAD_REQUEST);
 	 messageResult.setMessageType(MESSAGETYPES.ERROR);
 	 messageResult.setMessage(message);
 	 return new ResponseEntity<>(messageResult, messageResult.catchHttpStatus());

  }

  public static <T>ResponseEntity<DefaultResponse<T>> onThrow404Response(String message) {
 	 var messageResult = new DefaultResponse<T>();
 	 messageResult.setStatus(HttpStatus.NOT_FOUND);
 	 messageResult.setMessageType(MESSAGETYPES.INFO);
 	 messageResult.setMessage(message);
 	 return new ResponseEntity<>(messageResult, messageResult.catchHttpStatus());
  }

  public static <T> ResponseEntity<DefaultResponse<T>> onThrow404ResponseWithData(String message, List<T> data) {
 	 var messageResult = new DefaultResponse<T>();
 	 messageResult.setStatus(HttpStatus.NOT_FOUND);
 	 messageResult.setData(data);
 	 messageResult.setMessageType(MESSAGETYPES.INFO);
 	 messageResult.setMessage(message);
 	 return new ResponseEntity<>(messageResult, messageResult.catchHttpStatus());
  }

  public static <T>ResponseEntity<DefaultResponse<T>> onThrow400ResponseBindingResult (BindingResult bindingResult){
 	 var messageResult = new DefaultResponse<T>();
	  List<Message> listErrors = new ArrayList<>();
	  for (FieldError error : bindingResult.getFieldErrors()) {
		  listErrors.add(new Message(error.getDefaultMessage(), MESSAGETYPES.INFO));
	  }
 	 messageResult.setStatus(HttpStatus.BAD_REQUEST);
 	 messageResult.setMessageType(MESSAGETYPES.INFO);
 	 messageResult.setMessage(listErrors);
 	 return new ResponseEntity<>(messageResult, messageResult.catchHttpStatus());

  }

  public static <T> ResponseEntity<DefaultResponse<T>> onThrow200Response(List<T> data) {
 	 var messageResult = new DefaultResponse<T>();
 	 messageResult.setData(data);
 	 messageResult.setStatus(HttpStatus.OK);
 	 messageResult.setMessageType(MESSAGETYPES.SUCCESS);
 	 messageResult.setMessage(DEFAULTMESSAGES.SUCCESS_MESSAGE.value());
 	 return new ResponseEntity<>(messageResult, messageResult.catchHttpStatus());

  }
  public static <T> ResponseEntity<DefaultResponse<T>> onThrow200Response(List<T> data, String message) {
	 	 var messageResult = new DefaultResponse<T>();
	 	 messageResult.setData(data);
	 	 messageResult.setStatus(HttpStatus.OK);
	 	 messageResult.setMessageType(MESSAGETYPES.SUCCESS);
	 	 messageResult.setMessage(message);
	 	 return new ResponseEntity<>(messageResult, messageResult.catchHttpStatus());

	  }
	public static <T> ResponseEntity<DefaultResponse<T>> onThrow200Response(List<T> data, Page<T> page) {
		var messageResult = new DefaultResponse<T>();
		messageResult.setData(data);
		messageResult.setStatus(HttpStatus.OK);
		messageResult.setMessageType(MESSAGETYPES.SUCCESS);
		messageResult.setMessage(DEFAULTMESSAGES.SUCCESS_MESSAGE.value());
		messageResult.setPageableInformation(page);
		return new ResponseEntity<>(messageResult, messageResult.catchHttpStatus());

	}

  @SafeVarargs
  public static <T> ResponseEntity<DefaultResponse<T>> onThrow200Response(T ... objects) {
 	 var messageResult = new DefaultResponse<T>();
 	List<T> data = objects != null && objects.length > 0 ? Arrays.asList(objects) : Collections.emptyList();

 	 messageResult.setData(data);
 	 messageResult.setStatus(HttpStatus.OK);
 	 messageResult.setMessageType(MESSAGETYPES.SUCCESS);
 	 messageResult.setMessage(DEFAULTMESSAGES.SUCCESS_MESSAGE.value());
 	 return new ResponseEntity<>(messageResult, messageResult.catchHttpStatus());

  }
  public static <T> ResponseEntity<DefaultResponse<T>> onThrow200ResponseListMessageAndErrors(List<T> data,
 		 List<Message> messages, List<Error> errors) {
 	 var messageResult = new DefaultResponse<T>();
 	 messageResult.setData(data);
 	 messageResult.setStatus(HttpStatus.OK);
 	 messageResult.setMessageType(MESSAGETYPES.SUCCESS);
 	 messageResult.setMessage(messages);
 	 messageResult.setError(errors);
 	 return new ResponseEntity<>(messageResult, messageResult.catchHttpStatus());
  }
  public static <T> ResponseEntity<DefaultResponse<T>> onHandlerFindJpa(List<T> data) {
 	 if (data.iterator().hasNext()) {
 		 return onThrow200Response(data);
 	 }
 	 return onThrow404Response(DEFAULTMESSAGES.NOT_INFO_FOUND_MESSAGE.value());
  }
  @SafeVarargs
  public static <T> ResponseEntity<DefaultResponse<T>> onHandlerFindJpa(T ...data) {
 	 if (data !=null &&data.length> 0) {
 		 return onThrow200Response(data);
 	 }
 	 return onThrow404Response(DEFAULTMESSAGES.NOT_INFO_FOUND_MESSAGE.value());
  }


  public static <T>ResponseEntity<DefaultResponse<T>> onThrow200ResponseListMessage(List<T> data, List<Message> messages) {
 	 var messageResult = new DefaultResponse<T>();
 	 messageResult.setData(data);
 	 messageResult.setStatus(HttpStatus.OK);
 	 messageResult.setMessageType(MESSAGETYPES.SUCCESS);
 	 messageResult.setMessage(messages);
 	 return new ResponseEntity<>(messageResult, messageResult.catchHttpStatus());
  }

  public static<T> ResponseEntity<DefaultResponse<T>> onThrow201Response(List<T> data) {
 	 var messageResult = new DefaultResponse<T>();
 	 messageResult.setData(data);
 	 messageResult.setStatus(HttpStatus.CREATED);
 	 messageResult.setMessageType(MESSAGETYPES.SUCCESS);
 	 messageResult.setMessage(DEFAULTMESSAGES.INFO_UPDATED_MESSAGE.value());
 	 return new ResponseEntity<>(messageResult, messageResult.catchHttpStatus());
  }

  public static <T> ResponseEntity<DefaultResponse<T>> onThrow201CustomMessage(String string) {
 	 var messageResult = new DefaultResponse<T>();
 	 messageResult.setStatus(HttpStatus.CREATED);
 	 messageResult.setMessageType(MESSAGETYPES.SUCCESS);
 	 messageResult.setMessage(string);
 	 return new ResponseEntity<>(messageResult, messageResult.catchHttpStatus());
  }

  public static <T> ResponseEntity<DefaultResponse<T>> onThrow200ResponseObjectData(List<T> object) {
 	 var messageResult = new DefaultResponse<T>();
 	 messageResult.setData(object);
 	 messageResult.setStatus(HttpStatus.OK);
 	 messageResult.setDataType(DATATYPE.OBJECT);
 	 messageResult.setMessageType(MESSAGETYPES.SUCCESS);
 	 messageResult.setMessage(DEFAULTMESSAGES.SUCCESS_MESSAGE.value());
 	 return new ResponseEntity<>(messageResult, messageResult.catchHttpStatus());
  }
  @SafeVarargs
  public static<T> ResponseEntity<DefaultResponse<T>> onThrow201Response(T... object) {
 	 var messageResult = new DefaultResponse<T>();
 	List<T> data = object != null && object.length > 0 ? Arrays.asList(object) : Collections.emptyList();

 	 messageResult.setData(data);
 	 messageResult.setStatus(HttpStatus.CREATED);
 	 messageResult.setMessageType(MESSAGETYPES.SUCCESS);
 	 messageResult.setMessage(DEFAULTMESSAGES.INFO_UPDATED_MESSAGE.value());
 	 return new ResponseEntity<>(messageResult, messageResult.catchHttpStatus());
  }
  @SafeVarargs
  public static <T> ResponseEntity<DefaultResponse<T>> onThrow200ResponseObjectData(T ... object) {
 	 var messageResult = new DefaultResponse<T>();
 	List<T> data = object != null && object.length > 0 ? Arrays.asList(object) : Collections.emptyList();

 	 messageResult.setData(data);
 	 messageResult.setStatus(HttpStatus.OK);
 	 messageResult.setDataType(DATATYPE.OBJECT);
 	 messageResult.setMessageType(MESSAGETYPES.SUCCESS);
 	 messageResult.setMessage(DEFAULTMESSAGES.SUCCESS_MESSAGE.value());
 	 return new ResponseEntity<>(messageResult, messageResult.catchHttpStatus());
  }

  public DefaultResponse(List<T> data, HttpStatus status, List<Message> message) {
 	 super();
 	 this.data = data;
 	 this.status = status;
 	 this.message = message;
  }

  public String getMessageType() {
 	 return messageType.value();
  }

  public void setMessageType(MESSAGETYPES messageType) {
 	 this.messageType = messageType;
  }


  public void setDataType(DATATYPE dataType) {
 	 this.dataType = dataType;
  }

  public DATATYPE getDataType() {
 	 return dataType;
  }


  public List<T> fecthDataTest() {
 	 return this.data;
  }

  public List<Error> getError() {
 	 return this.error;
  }

  public List<Message> getMessage() {
 	 return this.message;
  }

  public void setData(List<T> data) {
 	 this.data = data;
  }
  public void setErrorStringList(List<String> errorsList) {
 	 var errors = new ArrayList<Error>();
 	 for (String error : errorsList) {
 		 errors.add(new Error(error, this.messageType));
 	 }
 	 this.error = errors;
  }
  public void setError(Error error) {
 	 this.error = List.of(error);
  }

  public void setError(String error) {
 	 this.error = List.of(new Error(error, (this.messageType != null) ? this.messageType : MESSAGETYPES.ERROR));
  }

  @JsonSetter
  public void setError(List<Error> error) {
 	 this.error = error;
  }

  @JsonSetter
  public void setMessage(List<Message> message) {
 	 this.message = message;
  }
	public Object getData() {
  	if (this.data == null || DATATYPE.LIST.equals(this.dataType) || this.data.isEmpty()) {
      	return data;
  	} else {
      	return data.get(0);
  	}
	}
	public List<T> fetchDataAsList() {
      	return data;
	}

  public void setMessage(Message message) {
 	 this.message = List.of(message);
  }

  public void setMessage(String message) {

 	 this.message = List.of(new Message(message, (this.messageType != null) ? this.messageType : MESSAGETYPES.INFO));
  }

  public int getStatus() {
 	 return status.value();
  }

  public void setStatus(HttpStatus status) {
 	 this.status = status;
  }

  public HttpStatus catchHttpStatus() {
 	 return this.status;
  }

}


