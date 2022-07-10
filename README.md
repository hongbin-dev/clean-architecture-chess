# '만들면서 배우는 클린 아키텍처' 스터디

![architecture-image](https://user-images.githubusercontent.com/33685054/178153353-854f08ed-74f6-44fc-a8f7-a9bfa379a6fc.jpg)

## chess-configuration
- 의존성 주입 모듈

## chess-web-adapter
- 웹 서비스의 핸들러

## chess-persist-adapter
- 체스 애플리케이션의 영속성계층

## chess-port
- 유스케이스 인터페이스(in), 영속성계층 인터페이스(out) 등
- POJO 지향

## chess-application
- 유스케이스의 구현체

## chess-domain
- 핵심 업무규칙
- POJO 지향
- 외부 라이브러리를 의존하지 않음