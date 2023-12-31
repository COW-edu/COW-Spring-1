# WEEK5

### 스프링과 객체지향

- 스프링
    - Java 언어를 기반으로 하는 엔터프라이즈 애플리케이션을 개발하기 위한 프레임워크
    - 객체지향 프로그래밍의 원칙을 따르며, 애플리케이션의 개발과 관리를 더 효율적으로 할 수 있도록 다양한 기능과 도구를 제공
    - 의존성 주입(Dependency Injection)
        - 스프링은 객체 생성과 관리를 담당하는 컨테이너를 통해 의존성을 주입
        - 이를 통해 코드의 결합도를 낮추고 유연한 구조를 구현
    - AOP(Aspect-Oriented Programming)
        - 스프링은 `AOP`를 지원하여 핵심 로직과 부가적인 기능(로깅, 보안 등)을 분리하여 모듈화할 수 있음
    - 트랜잭션 관리
        - 스프링은 데이터베이스와 같은 리소스의 트랜잭션 관리를 간편하게 처리할 수 있는 방법을 제공
    - MVC 아키텍처
        - 스프링은 웹 애플리케이션 개발을 위한 MVC(Model-View-Controller) 아키텍처를 제공하여 UI, 비즈니스 로직, 데이터 처리를 분리하여 관리
    - 보안
        - 스프링은 인증(Authentication)과 권한 부여(Authorization)를 위한 보안 기능을 제공하여 안전한 애플리케이션을 개발할 수 있도록 도와줌
    - 스프링은 다양한 모듈과 라이브러리를 포함하고 있어 개발자가 원하는 기능을 선택하여 사용할 수 있음
    - 스프링 프레임워크는 엔터프라이즈급 애플리케이션 개발을 위한 강력한 도구로 폭넓게 사용되고 있음
- 객체지향 프로그래밍
    - 소프트웨어를 모듈화하고 관리하기 위한 프로그래밍 패러다임 중 하나
    - 이 패러다임은 현실 세계의 사물과 개념을 프로그램 안으로 추상화하여 모델링함
    - 클래스와 ****객체
        - 클래스는 객체를 생성하기 위한 템플릿이며, 객체는 클래스를 기반으로 실제로 생성된 인스턴스
        - 클래스는 속성(데이터)과 메서드(동작)를 정의하여 객체의 특징을 표현
    - 상속
        - 기존 클래스의 속성과 메서드를 다른 클래스가 재사용하는 것을 의미
        - 이를 통해 코드의 재사용성과 확장성을 높일 수 있음
    - 다형성
        - 같은 이름의 메서드나 속성을 다양한 방식으로 동작하도록 하는 개념
        - 이를 통해 코드의 유연성과 확장성을 높일 수 있음
    - 캡슐화
        - 객체의 내부 상태와 동작을 외부로부터 감추는 것을 의미
        - 클래스 내부의 세부 구현 사항은 외부에서 접근할 수 없도록 보호됨

### 스프링 컨테이너

- 스프링 프레임워크의 핵심 요소 중 하나로, 객체의 생성, 관리, 의존성 주입 등을 담당하는 환경을 제공하는 컴포넌트
- 애플리케이션의 구성 요소들을 관리하고 연결하여 개발자가 더 효율적으로 애플리케이션을 개발할 수 있도록 도움
- 스프링 컨테이너의 주요 기능
    - 객체 생성 및 관리
        - 스프링 컨테이너는 애플리케이션에서 사용되는 다양한 객체를 생성하고 관리함
        - 이를 통해 개발자는 객체의 생명 주기를 신경 쓰지 않고도 필요한 객체를 사용할 수 있음
    - 의존성 주입
        - 스프링 컨테이너는 객체들 사이의 의존성을 자동으로 주입해줌
        - 객체가 필요로 하는 다른 객체를 생성하거나 주입하는 작업을 컨테이너가 대신 처리
        - 코드의 결합도가 낮아지고 유연한 구조를 구현할 수 있음
    - 싱글톤 관리
        - 스프링 컨테이너는 기본적으로 싱글톤 객체를 관리
        - 하나의 클래스에 대해 하나의 인스턴스만 생성하고 관리하여 리소스를 효율적으로 사용할 수 있음
    - 라이프사이클 관리
        - 객체의 생성부터 소멸까지의 라이프사이클을 관리하며, 필요한 초기화나 정리 작업을 수행할 수 있도록 지원
    - AOP 지원
        - 스프링 컨테이너는 `AOP`를 활용하여 핵심 로직과 부가 기능(로깅, 트랜잭션 등)을 분리하여 모듈화할 수 있음
- 스프링 컨테이너의 종류
    - BeanFactory
        - 스프링 컨테이너의 가장 기본적인 형태로, 객체 생성과 의존성 주입을 관리
        - 빈(Bean)은 스프링에서 관리되는 객체를 말하며, `BeanFactory`는 빈의 정의와 생성, 라이프사이클 관리를 담당
    - ApplicationContext
        - `BeanFactory`의 확장된 형태로, 더 많은 기능을 제공
        - `ApplicationContext`는 메시지 리소스, 이벤트 발행, 국제화 등 다양한 기능을 지원하며, 보다 풍부한 설정 옵션과 성능 향상을 제공
- 스프링 컨테이너를 사용하여 객체를 관리하고 의존성을 주입받으면, 코드의 유지보수성과 확장성을 향상시킬 수 있음
- 스프링 프레임워크는 여러 가지 설정 방법을 통해 스프링 컨테이너를 생성하고 구성
- 싱글톤 컨테이너
    - 싱글톤 컨테이너(Singleton Container)는 스프링 프레임워크에서 객체의 싱글톤(단일 인스턴스)을 관리하는 기능을 말함
    - 하나의 클래스에 대해 오직 하나의 인스턴스만 생성되고, 이 인스턴스를 여러 곳에서 공유해서 사용하는 디자인 패턴
    - 스프링은 싱글톤 컨테이너를 통해 애플리케이션에서 공통으로 사용되는 객체를 효율적으로 관리하고 재사용할 수 있도록 지원
    - 동작방식
        - 객체 생성
            - 싱글톤 컨테이너는 최초에 해당 빈(Bean)의 인스턴스를 생성
            - 이후에는 해당 빈을 요청하는 경우 이미 생성된 인스턴스를 반환
        - 객체관리
            - 스프링 컨테이너 내에서 싱글톤 빈은 싱글톤 패턴을 활용하여 단일 인스턴스를 유지하며, 컨테이너 내부에서 캐시하고 재사용함
        - 스레드 안전
            - 스프링 컨테이너는 기본적으로 싱글톤 빈을 스레드 안전하게 관리함
            - 다중 스레드 환경에서도 문제 없이 사용할 수 있도록 설계되어 있음
    - 장점
        - 메모리절약
            - 객체를 한 번만 생성하고 공유하기 때문에 메모리 사용량을 줄일 수 있음
        - 성능 향상
            - 객체 생성 비용을 절감하여 애플리케이션의 성능을 향상시킬 수 있음
        - 일관성 유지
            - 같은 객체 인스턴스를 여러 곳에서 사용하므로 데이터의 일관성을 유지하기 쉬움
        - 의존성 주입 효율화
            - 싱글톤 객체에 필요한 의존성 주입을 한 번만 수행하면 되므로, 의존성 주입 비용을 절감할 수 있음
    - 주의사항
        - 상태 유지
            - 싱글톤 객체는 여러 곳에서 공유되므로, 객체 내부의 상태 변경이 다른 부분에 영향을 줄 수 있음
            - 객체의 상태 관리에 주의해야함
        - 순환참조
            - 싱글톤 객체들 간의 순환 참조가 발생하면 메모리 누수가 발생할 수 있으므로 조심해야함
        - 스레드 세이프한 구현
            - 만약 싱글톤 객체 내부에서 상태를 변경하는 작업이 이루어진다면, 스레드 안전한 방식으로 구현해야함
- 스프링 컨테이너는 기본적으로 싱글톤 패턴을 사용하여 객체를 관리하지만, 필요에 따라 프로토타입(Prototype) 빈 등 다른 스코프의 빈도 지원함
- 이를 통해 다양한 상황에 맞게 객체의 생성과 관리를 조절할 수 있음

### 스프링 빈

- 스프링 프레임워크에서 객체를 생성, 관리, 의존성 주입 등을 담당하는 기본 단위
- 스프링은 빈을 사용하여 애플리케이션의 여러 구성 요소를 관리하고, 이를 통해 유연하고 모듈화된 애플리케이션을 개발할 수 있음
- 스프링 빈의 특징
    - 객체 생성과 라이프사이클 관리
        - 스프링 빈은 스프링 컨테이너에 의해 생성되고 관리됨
        - 빈의 라이프사이클은 초기화(init)와 소멸(destroy) 단계로 관리되며, 컨테이너가 이를 관리함
    - 의존성 주입(Dependency Injection)
        - 빈은 스프링 컨테이너에 의해 의존성을 주입받음
        - 이를 통해 객체 간의 결합도를 낮추고 유연한 구조를 구현할 수 있음
    - 스코프 관리
        - 스프링은 다양한 스코프(싱글톤, 프로토타입 등)를 제공하여 빈의 생명주기와 범위를 조절할 수 있음
    - AOP(Aspect-Oriented Programming) 지원
        - 스프링 빈은 AOP를 활용하여 부가 기능(로깅, 트랜잭션 등)을 분리하여 모듈화할 수 있음
- 스프링 빈의 등록 방법
    - 컴포넌트 스캔(Component Scan)
        - @Component 어노테이션을 이용하여 클래스를 빈으로 등록할 수 있음
        - @Controller, @Service, @Repository 등의 어노테이션도 컴포넌트 스캔의 일부로 사용됨
    - 자바 설정 파일(XML 또는 Java Config)
        - 스프링 설정 파일에서 빈을 직접 등록할 수 있음
        - XML 파일 또는 Java Config 클래스를 사용하여 빈의 정의와 의존성을 설정
- 스프링 빈의 주의사항
    - 의존성 주입(DI) 설정
        - 스프링 빈을 사용할 때 의존성 주입 설정을 정확히 해주어야함
        - 잘못된 의존성 주입 설정은 런타임 에러를 발생시킬 수 있음
    - 스코프 설정
        - 빈의 스코프 설정을 통해 객체의 생명주기와 범위를 조절할 수 있음
        - 적절한 스코프를 선택하여 사용해야함
    - 컴포넌트 네이밍
        - 빈의 이름은 고유해야 하며, 컴포넌트 스캔을 사용할 때 클래스 이름의 첫 글자를 소문자로 변경하여 자동으로 설정됨
- 스프링 빈은 스프링 애플리케이션의 핵심 구성 요소이며, 스프링의 다양한 기능과 함께 사용하여 애플리케이션을 개발하고 관리할 수 있음
- 이를 통해 코드의 재사용성과 유지보수성을 향상시킬 수 있음

### 컴포넌트 스캔

- 스프링 프레임워크에서 자동으로 빈(Bean)을 등록하고 관리하기 위한 기능
- 컴포넌트 스캔을 사용하면 개발자가 일일이 빈을 설정하거나 등록하지 않고도 자동으로 클래스를 스캔하여 스프링 빈으로 등록할 수 있음
- 이는 애플리케이션의 구성 요소를 간단하게 관리하고 개발 생산성을 높이는 데 도움을 줌
- 컴포넌트 스캔의 원리
    - 컴포넌트 스캔은 주로 어노테이션을 기반으로 동작
    - 스프링 컨테이너는 설정된 베이스 패키지부터 시작하여 해당 패키지 및 하위 패키지에 있는 클래스들을 스캔
    - 스캔된 클래스들 중에서 특정 어노테이션으로 마킹된 클래스들을 스프링 빈으로 등록
    - @Component
        - 일반적인 스프링 빈으로 등록하기 위해 사용되는 어노테이션
    - @Controller
        - 웹 애플리케이션의 컨트롤러로 사용될 클래스를 나타내는 어노테이션
    - @Service
        - 비즈니스 로직을 처리하는 서비스 클래스를 나타내는 어노테이션
    - @Repository
        - 데이터베이스와 연동하여 데이터를 처리하는 DAO(Data Access Object) 클래스를 나타내는 어노테이션
    - 컴포넌트 스캔은 빈을 자동으로 등록해주므로 개발자는 빈 설정 파일에서 일일이 빈을 등록하는 번거로움을 줄일 수 있음
- 컴포넌트 스캔의 사용 방법
    - 기본 설정: 스프링 설정 파일(XML 또는 Java Config)에 컴포넌트 스캔을 활성화시키는 설정을 추가
        - XML 설정 파일 예시
        
        ```xml
        <context:component-scan base-package="com.example.package"/>
        ```
        
        - Java Config 클래스 예시
        
        ```java
        @Configuration
        @ComponentScan("com.example.package")
        public class AppConfig {
            //...
        }
        ```
        
    - 클래스에 어노테이션 추가: 빈으로 등록하려는 클래스에 적절한 어노테이션을 추가함. 예를 들어, **`@Component`**, **`@Controller`**, **`@Service`**, **`@Repository`** 중 하나를 사용하여 클래스를 마킹
    - 의존성 주입(DI): 컴포넌트 스캔으로 등록된 빈들은 스프링 컨테이너에 의해 자동으로 관리되므로, 의존성 주입을 통해 필요한 객체를 주입받을 수 있음
- 컴포넌트 스캔을 사용하면 애플리케이션의 구성 요소를 보다 간단하게 관리할 수 있고, 새로운 클래스를 추가하거나 수정할 때도 일일이 빈 설정을 변경할 필요가 없어서 개발 생산성을 향상시킬 수 있음

### 의존관계 자동 주입

- 스프링 프레임워크에서 빈(Bean) 간의 의존성을 자동으로 설정하는 기능
- 이를 통해 개발자는 빈들 간의 의존성을 명시적으로 코드로 작성하지 않아도 되며, 스프링 컨테이너가 이를 자동으로 처리함
- 이는 애플리케이션의 유지보수성을 높이고 객체 간의 결합도를 낮추는 데 도움을 줌
- 의존관계 자동 주입의 원리
    - 컴포넌트 스캔과 어노테이션: 컴포넌트 스캔을 통해 빈들을 자동으로 등록하고, 어노테이션을 사용하여 의존관계를 자동으로 설정
    - 타입(Type) 기반 자동 주입: 의존성을 주입할 빈의 타입을 기준으로 자동으로 의존관계를 주입
    - 이름(Name) 기반 자동 주입: 의존성을 주입할 빈의 이름을 기준으로 자동으로 의존관계를 주입
- 의존관계 자동 주입의 사용방법
    - 생성자 주입(Constructor Injection)
        - 생성자를 이용하여 의존성을 주입하는 방식
        - 생성자에 **`@Autowired`** 어노테이션을 사용하여 자동 주입하려는 빈을 명시
        
        ```java
        @Service
        public class UserServiceImpl implements UserService {
            private final UserRepository userRepository;
        
            @Autowired
            public UserServiceImpl(UserRepository userRepository) {
                this.userRepository = userRepository;
            }
        
            // ...
        }
        ```
        
    - Setter 주입(Setter Injection)
        - Setter 메서드를 이용하여 의존성을 주입하는 방식
        - Setter 메서드에 **`@Autowired`** 어노테이션을 사용하여 자동 주입하려는 빈을 명시
        
        ```java
        @Controller
        public class UserController {
            private UserService userService;
        
            @Autowired
            public void setUserService(UserService userService) {
                this.userService = userService;
            }
        
            // ...
        }
        ```
        
    - 필드 주입(Field Injection)
        - 필드에 **`@Autowired`** 어노테이션을 사용하여 의존성을 주입하는 방식
        - 주로 개발자들 사이에서 논쟁이 있을 수 있으며, 일부 상황에서는 비추천되기도 함
        
        ```java
        @Repository
        public class UserRepositoryImpl implements UserRepository {
            @Autowired
            private JdbcTemplate jdbcTemplate;
        
            // ...
        }
        ```
        
- 의존관계 자동 주입을 사용하면 객체 간의 의존성을 명시적으로 작성할 필요 없이 스프링 컨테이너가 자동으로 의존성을 주입함
- 이를 통해 코드의 재사용성과 유지보수성을 향상시킬 수 있음