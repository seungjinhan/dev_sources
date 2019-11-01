package com.chunlab.app.system.repository;

import javax.persistence.EntityManager;

import com.chunlab.app.system.exception.ExceptionBase;

@FunctionalInterface
public interface JPAFunction<T, R> {

	R run(EntityManager em, T t) throws ExceptionBase;
}
