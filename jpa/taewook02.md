# JPA

## JPQL (Java Persistence Query Language)

JPQL은 Java Persistence Query Language의 약자로, 영구적인 엔티티에 대한 데이터베이스 작업을 수행하는 데 사용되는 객체 지향 쿼리 언어이다.

### JPQL의 특징

- **객체 지향 쿼리 언어**: JPQL은 데이터베이스 테이블 대신 엔티티 객체 모델을 사용하여 SQL 쿼리를 작동시킨다.

- **SQL과 유사한 구문**: JPQL의 구문은 SQL의 구문과 매우 유사하다.

- **데이터베이스에 직접적인 영향 없음**: JPQL은 데이터베이스에 직접 영향을 미치지 않는다.

### 문법

```sql
String jpql = "select m from Member as m where m.name = 'coco'";
```

#### 1. 대소문자 구분

- JPQL에서 엔티티와 속성은 대소문자를 구분한다.
- 단, SELECT, FROM, AS 등의 키워드들은 대소문자를 구분하지 않는다.

#### 2. 엔티티 이름

- JPQL에서 사용하는 멤버는 클래스명이 아닌 엔티티명이다.
- ```@Entity(name="이름")```으로 엔티티명을 설정할 수 있다.
- name 속성을 생략할 경우 클래스명을 기본값으로 사용한다.

#### 3. 별칭

- JPQL에서 엔티티의 별칭은 필수적으로 명시해야 한다.
- AS 키워드는 생략이 가능하다.

### TypedQuery, Query

JPQL을 실행하려면 쿼리 객체를 만들어야 한다. 쿼리 객체로는 **TypedQuery**와 **Query**가 있다.

반환할 타입을 명확하게 지정할 수 있으면 TypedQuery 객체를, 명확하게 지정할 수 없으면 Query 객체를 사용한다.

#### TypedQuery

```java
public static void typedQuery(EntityManager em) {
    String jpql = "select m from Member m";
	TypedQuery<Member> query = em.createQuery(jpql, Member.class);
	
	List<Member> list = query.getResultList();
	for( Member member : list) {
		System.out.println("Member : " + member);
	}
}
```

#### Query

Query 타입은 데이터 검색 결과의 타입을 명시하지 않는다.

```java
public static void Query(EntityManager em) {
    String jpql = "select m.name, m.age from Member m";
	Query query = em.createQuery(jpql);
	
	List<Object> list = query.getResultList();
	
    for( Object object : list ) {
	      Object[] results = (Object[]) object;
	      
	      for( Object result : results ) {
	          System.out.print ( result );
	     }
	     System.out.println();
	  }
}
```

## 영속성 컨텍스트

- 엔티티를 영구 저장하는 환경을 말한다.
- 애플리케이션과 데이터베이스 사이에서 객체를 보관하는 논리적 개념이다.
- EntityManager를 통해서 접근이 가능하다.

### 영속성 컨텍스트의 상태

JPA 영속성 컨텍스트에서 엔티티는 세 가지 상태를 가진다.

#### 1. 비영속 상태 (Transient)

- 새로 생성된 엔티티는 비영속 상태이다.
- 이 상태의 엔티티는 아직 영구 저장소에 저장되지 않았으며, 영속성 컨텍스트에도 포함되지 않는다.

```java
Author author = new Author();
author.setFirstName("Thorben");
author.setLastName("Janssen");
```

#### 2. 영속 상태 (Managed)

- 엔티티가 영속성 컨텍스트에 포함되면 영속 상태가 된다.
- 이 상태에서는 모든 변경이 추적되며 트랜잭션이 커밋될 때 데이터베이스에 반영된다.
- `persist` 메서드로 영속 상태로 만들 수 있다.

```java
entityManager.persist(author);
```

#### 3. 준영속 상태 (Detached)

- 준영속 상태는 엔티티가 영속성 컨텍스트에서 분리된 상태이다.
- 이 상태의 엔티티는 더 이상 영속성 컨텍스트에 의해 관리되지 않으며, 변경 사항이 데이터베이스에 반영되지 않는다.
- `detach` 메서드로 준영속 상태로 만들 수 있다.

```java
entityManager.detach(author);
```

## 엔티티 매핑

- JPA에서 `@Entity` 어노테이션이 붙은 클래스를 관리하는 것
- 테이블과 매핑해야 할 클래스는 반드시 `@Entity`를 선언해야 한다.
- 엔티티 클래스는 기본 생성자가 필요함

#### 어노테이션

- `@Table`: 엔티티와 매핑할 테이블을 지정
- `@Id`: 기본 키 (Primary Key)를 지정
- `@GeneratedValue`: 기본 키를 생성하는 방법을 지정
- `@Column`: Column을 매핑
- `@JoinColumn`: 조인을 하고 있는 컬럼을 매핑
- `@Lob`: 데이터베이스에서 VARCHAR보다 큰 데이터를 담고 싶을 때 사용

## 연관관계 매핑

### 연관관계 매핑 방법

1. **외래키로 간접 참조하는 방법**: 외래 키를 사용하여 다른 엔티티를 간접적으로 참조한다.

2. **객체를 직접 참조하는 방법**: 한 객체가 다른 객체를 직접 참조한다.

연관관계 매핑시 고려해야하는 사항은 3가지가 있다.

- **다중성**: 일대일(OneToOne), 일대다(OneToMany), 다대일(ManyToOne), 다대다(ManyToMany)

- **단방향, 양방향**: 객체의 참조는 항상 단방향이지만 두 개의 단방향 참조를 조합해서 양방향처럼 사용할 수 있다.

- **연관관계의 주인**: 양방향 연관관계에서는 연관관계의 주인만이 외래 키를 관리할 수 있다.

## Spring Data JPA

- 자바 진영에서 ORM(Object-Relational Mapping) 기술 표준으로 사용되는 인터페이스
- 실제적으로 구현된것이 아니라 구현된 클래스와 매핑을 해주기 위해 사용되는 프레임워크이다.
- Hibernate, OpenJPA 등이 있다.

### JPA를 사용하는 이유

- SQL을 객체지향적으로 개발할 수 있다.
- 생산성과 유지보수에 유리하다.
- 상속관계와 연관관계에 관한 문제를 해결할 수 있다.
