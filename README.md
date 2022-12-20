# 엔티티별 기능 명세

**- User**
스크랩한 Restaruant 엔티티 목록
등록한 리뷰 목록
회원 등급(등록한 리뷰 개수에 따라 달라짐)

**- Restaurant**
상호명
주소
전화번호
학교까지의 거리
기타 특이사항(할인 행사 등)
카테고리
태그
메뉴 정보? -> 자세한 정보는 외부 웹사이트로 이동해서 확인하도록 링크 제공.
등록되어 있는 Review 엔티티 목록(Restaurant -> Review 단방향)

**- Review**
작성한 User 정보
작성 일시
리뷰 내용
주문한 메뉴와 가격 (선택 사항)
수정 여부(수정된 리뷰인 경우(수정됨) 표시)
사진
별점
추천/비추천한 User 목록(중복 추천/비추천 방지 위함, Review -> User 단방향)

**- Category**
카테고리명(한식 양식 중식 일식 분식 베트남음식 패스트푸드 카페디저트 등등 배민 카테고리 참조하기)
포함되어 있는 Restaurant 목록

**- Tag**
- Enum으로 큰 분류(분위기, 가까운 역, 대표 메뉴, 상황, 서비스 등) 미리 정의해두기
분류
태그명
포함되어 있는 Restaurant