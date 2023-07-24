package com.algaworks.erp.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;

@Target({ ElementType.METHOD, ElementType.TYPE }) // @Target limita onde a anotação personalizada pode ser usada.
@Retention(RetentionPolicy.RUNTIME) // @Retention determina até quando a anotação estará disponível em tempo de execução.
@InterceptorBinding // @InterceptorBinding define um binding personalizado para interceptores específicos.
public @interface Transacional {

}