## 1주차

- JPQL
- 영속성 컨텍스트
- 엔티티 매핑

## 2주차

- 연관관계 매핑
    - 다양한 매핑
    - 단방향, 양방향
- Spring Data JPA

## JPQL

### JPQL이란?

JPA가 제공하는 SQL을 추상화한 객체 지향 쿼리 언어

엔티티 객체를 대상으로 쿼리가 작성됨

SQL을 추상화했기 때문에 특정 데이터베이스 SQL에 의존하지 않는다

### 예시

```java
String jpql = "select m From Member m where m.name like ‘%hello%'";
List<Member> result = em.createQuery(jpql, Member.class).getResultList();

for (Member member : result) {
    System.out.println("member = " + member);
}
```

Member 객체를 대상으로 이름에 “hello”가 포함된 모든 회원을 뽑아준다.

### 단점

기본 문자열(String)로 작성되기 때문에 쿼리에 문제가 있어도 컴파일 시 에러가 발생하지 않는다.

동적으로 쿼리를 작성하기 비효율적이다. (ex: 특정 조건에서 서로 다른 쿼리를 실행하고 싶은 경우)

### 특징

- 엔티티와 속성은 대소문자를 구분함.
    - 엔티티는 첫 글자를 대문자, 속성은 다 소문자
    - @Entity의 name을 지정하지 않으면 클래스 이름이 엔티티 이름이 된다.
- JPQL 키워드는 대소문자를 구분하지 않음
- 테이블 이름을 사용하지 않음
- 별칭은 필수적으로 사용함
- as 생략 가능
- 반환 타입이 명확할 경우 두 번째 인자로 타입 정보를 작성할 수 있음 (TypeQuery)
    - 예시
    
    ```java
    TypedQuery<Member> query1 = em.createQuery("select m from Member m", Member.class);
    TypedQuery<String> query2 = em.createQuery("select m.username from Member m", String.class);
    ```
    
- 반환 타입이 명확하지 않을 때 (Query)
    - 예시
    
    ```java
    Query query3 = em.createQuery("select m.username, m.age from Member m");
    ```
    

## 영속성 컨텍스트

엔티티를 영구 저장하는 환경

- 비영속 상태(new/transient): 영속성 컨텍스트와 관계 없는 상태

```java
//객체를 생성한 상태(비영속)
Member member = new Member();
member.setId("member1");
member.setUsername("회원1");
```

- 영속 상태(managed): 영속성 컨텍스트에 저장된 상태

```java
//객체를 생성한 상태(비영속)
Member member = new Member();
member.setId("member1");
member.setUsername(“회원1”);
EntityManager em = emf.createEntityManager();
em.getTransaction().begin();
//객체를 저장한 상태(영속)
em.persist(member);
```

- 준영속 상태(detached): 영속성 컨텍스트에 저장되었다가 분리된 상태

```java
//회원 엔티티를 영속성 컨텍스트에서 분리, 준영속 상태
em.detach(member);
```

- 삭제된 상태(removed

```java
//객체를 삭제한 상태(삭제)
em.remove(member);
```

### 장점

- 1차 캐시

영속 상태의 엔티티를 1차 캐시에 저장하기 때문에 엔티티 조회 시 1차 캐시에 데이터가 존재한다면 DB를 찾아보지 않아도 된다.

- 영속 엔티티의 동일성 보장

Repetable Read 등급의 트랜잭션 격리 수준을 데이터베이스가 아닌 애플리케이션 차원에서 제공한다.

- 트랜잭션을 지원하는 쓰기 지연

```java
EntityManager em = emf.createEntityManager();
EntityTransaction transaction = em.getTransaction();
//엔티티 매니저는 데이터 변경시 트랜잭션을 시작해야 한다.
transaction.begin(); // [트랜잭션] 시작

em.persist(memberA);
em.persist(memberB);
//여기까지 INSERT SQL을 데이터베이스에 보내지 않는다.

//커밋하는 순간 데이터베이스에 INSERT SQL을 보낸다.
transaction.commit(); // [트랜잭션] 커밋
```

em.persist()로 영속성 컨텍스트에 객체를 저장해도, DB에 INSERT 쿼리를 날리지 않는다. SQL 쿼리들을 모아두어 flush될 때, 모아둔 쿼리를 모두 날린다. (쓰기 지연)

- 변경 감지(Dirty Checking)

영속성 컨텍스트에서 엔티티를 조회해 해당 엔티티를 수정한다고 할 때, 업데이트를 위한 코드 없이 알아서 변경된 데이터를 비교해 존재한다면 UPDATE 쿼리를 자동으로 생성한다.

- 지연 로딩

연관 관계 매핑되어 있는 엔티티 조회 시 우선 프록시 객체를 반환하고, 실제로 필요할 때 쿼리를 날려 가져온다. 즉, 필요한 시점에 연관된 데이터를 불러오는 것

```java
@ManyToOne(fetch = FetchType.LAZY) //Team을 조회할 때 지연로딩을 사용하곘다!
@JoinColumn(name = "team_id")
Team team;
```

```sql
//Team을 조회하는 쿼리가 나가지 않음!
select
    member0_.id as id1_0_,
    member0_.team_id as team_id3_0_,
    member0_.username as username2_0_ 
from
    Member member0_
```

만약 Member 1명 조회 시 연관된 Team이 100개라면, 즉시 로딩(Eager) 시 100개의 Team을 조회하는 쿼리가 생성되었을 것이다. 이러한 비효율성을 극복하기 위해 지연 로딩을 사용한다.

## 엔티티 매핑

실제 데이터베이스의 테이블과 엔티티를 정확하게 매핑하는 것은 중요하다.

이를 위해 다양한 매핑 어노테이션을 알아두어야 하는데, 대표적으로 4가지로 분류할 수 있다.

- 객체 - 테이블 매핑: @Entity, @Table
- 기본 키 매핑: @Id
- 필드 - 컬럼 매핑: @Column
- 연관관계 매핑: @ManyToOne, @JoinColumn, @OneToMany

### @Entity

테이블과 매핑할 클래스에 붙이는 어노테이션

- 기본 생성자 필수
- final, enum, interface, inner 클래스에서는 사용 X
- 필드에 final 사용 X

### @Table

엔티티와 매핑할 테이블을 지정함

```java
@Entity
@Table(name = "MEMBER") // Member 엔티티를 테이블 명 MEMBER로 매핑
public class Member {}
```

### 기본 키 매핑

JPA의 기본 키 생성 전략엔 두 가지가 있다

- 직접 할당: 기본 키를 어플리케이션에서 직접 할당함
- 자동 생성: 대리 키를 사용함

IDENTITY: 기본 키 생성을 DB에 위임함

SEQUENCE: DB 시퀀스를 사용해 기본 키를 할당함

TABLE: 키 생성 테이블을 사용함

### 필드 - 컬럼 매핑

- @Enumerated: enum 타입 매핑 시 사용
    - EnumType.ORIGINAL: enum 순서를 데이터베이스에 저장
    - EnumType.STRING: enum 이름을 데이터베이스에 저장
    
    ```java
    @Enumerated(EnumType.STRING)
    private RoleType roleType;
    
    member.setRoleType(RoleType.ADMIN); //-> DB에 문자 ADMIN으로 저장됩니다.
    ```
    

- @Transient: 선언된 필드는 매핑되지 않음. 객체에 임시로 값을 보관하고 싶을 때 사용
- @Access: JPA가 엔터티 테이블에 접근하는 방식을 지정함

## 연관관계 매핑

객체를 테이블 중심으로 모델링하면, 협력 관계를 만들 수 없음

- 테이블은 외래 키 조인을 사용해 연관 테이블을 찾음
- 객체 참조를 사용해 연관된 객체를 찾음

⇒ 객체 지향 프로그래밍과 데이터베이스 사이의 패러다임 불일치 발생

### 단방향, 양방향 관계

데이터베이스 테이블은 FK로 양 쪽 테이블 조인이 가능하다. 따라서 단방향, 양방향을 나눌 필요가 없다.

하지만 객체는 참조용 필드가 있는 객체만 다른 객체를 참조할 수 있으므로, 두 객체 사이에 한 객체만 참조용 필드가 있는 경우 단방향 관계, 두 객체 모두 서로 참조하는 필드가 있다면 양방향 관계라고 한다.

단방향, 양방향 관계를 고민할 때 중요하게 생각해야 할 것은 두 객체의 참조가 필요한지의 여부이다. 무조건 양방향을 택했을 때, 필요 이상의 엔티티와 연관 관계를 가짐으로써 객체의 클래스가 매우 복잡해지게 된다. 그래서 기본적으로 단방향 매핑 관계를 가지고, 추후 역방향의 객체 탐색이 필요하다고 느껴지면 양방향 관계를 맺으면 된다.

### 연관 관계의 주인

두 객체가 단방향 관계일 때, 제어의 권한을 가진 객체를 연관 관계의 주인이라고 한다.

연관 관계의 주인은 두 객체 사이에서 CRUD가 가능하지만, 주인이 아닌 객체는 조회만 가능하다.

주인이 아닌 객체에서는 mappedBy 속성으로 주인을 지정해주어야 한다.

### JPA  vs Hibernate vs Spring Data JPA

- JPA

Java Persistence API의 약자로, 자바에서 관계형 데이터베이스를 사용하는 방식을 정의하는 인터페이스이다. 자바 클래스와 DB를 매핑해 객체를 통해서 간접적으로 DB data를 다룬다.

- Hibernate

JPA 인터페이스의 구현체로, JPA의 핵심 인터페이스인  `EntityManagerFactory`, `EntityManager`, `EntityTransaction` 을 **Hinbernate** 에서 각각 `SessionFactory`, `Session`, `Transaction` 으로 상속받고 각각 Impl 로 구현된다.

곧, 우리가 JPA를 사용하는 데 있어 Hibernate를 무조건 사용해야 되는 것은 아니다. 필요에 따라 직접 구현하여 사용할 수 있다.

- Spring Data JPA

Spring에서 제공하는 모듈 중 하나로, JPA를 더 쉽고 편하게 사용할 수 있도록 도와줌

JPARepository를 사용한다는 점에서 차이가 있다.

사용자가 Repository 인터페이스에 정해진 규칙대로 메소드를 입력하면 Spring은 알아서 해당 메소드 이름에 적합한 쿼리를 날리는 구현체를 만들어서 Bean으로 등록해준다.

<img width="576" alt="스크린샷 2023-09-13 오전 2 11 45" src="https://github.com/Youngcircle-kim/CowSubway-BackEnd/assets/104254012/c8bcc6b6-e7fa-426b-aa30-3d55a8b64812">

### Reference

https://ittrue.tistory.com/270

https://developer-hm.tistory.com/37

[https://velog.io/@sa1341/JPA-Entity-매핑](https://velog.io/@sa1341/JPA-Entity-%EB%A7%A4%ED%95%91)

https://velog.io/@heoseungyeon/JPA-vs-Spring-Data-JPA
