package br.edu.unirn.exemplofx.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Contato.class)
public abstract class Contato_ {

	public static volatile SingularAttribute<Contato, String> nome;
	public static volatile SingularAttribute<Contato, Long> id;
	public static volatile SingularAttribute<Contato, Long> version;
	public static volatile SingularAttribute<Contato, Date> dataCadastro;
	public static volatile SingularAttribute<Contato, String> email;

}

