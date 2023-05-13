package com.sena.inventarioback.controllers;

import java.util.List;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.sena.inventarioback.utils.response.DefaultResponse;

/*
 * @ControllerAdvice public class CustomResponseBodyAdvice {
 * 
 * @Override public boolean supports(MethodParameter returnType, Class<? extends
 * HttpMessageConverter<?>> converterType) { return
 * returnType.getContainingClass().equals(UserController.class) &&
 * returnType.getParameterType().equals(DefaultResponse.class); }
 * 
 * @Override public Object beforeBodyWrite(Object body, MethodParameter
 * returnType, MediaType selectedContentType, Class<? extends
 * HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
 * ServerHttpResponse response) { if (body instanceof DefaultResponse<T>) {
 * DefaultResponse<?> defaultResponse = (DefaultResponse<?>) body; List<?> data
 * = defaultResponse.getData(); if (data.size() == 1) {
 * defaultResponse.setData(data.get(0)); } } return body; }
 * 
 * }
 */