## 개발 IDE : Intellij
## 개발 프레임워크 : Spring Boot 2.5.5, Gradle, Junit5
## 문제해결방법 :
    1. 2018년, 2019년 각 연도별 합계 금액이 가장 많은 고객을 추출하는 API 개발
     ->  연도별 계좌별 금액의 합 맵에 저장 -> List<Map<String, Long>>
     ->  모든 계좌정보 조회 -> Map<String, Account>
     ->  각 연도별 계좌의 Max값 구해서 계좌정보와 매칭 후 리턴 -> List<SumAmtAccountByYearResponse>
       
    2. 2018년 또는 2019년에 거래가 없는 고객을 추출하는 API 개발.
     ->  연도별 계좌별 금액의 합 맵에 저장 -> List<Map<String, Long>>
     ->  모든 계좌정보 조회 -> Map<String, Account>
     ->  모든계좌 정보에서 연도별 계좌별 금액의 합에 있는 계좌들 remove
     ->  리턴 -> List<NoTransactionAccountByYearResponse>

    3. 연도별 관리점별 거래금액 합계를 구하고 합계금액이 큰 순서로 출력
     -> 연도별 계좌별 금액의 합 맵에 저장 -> List<Map<String, Long>>
     -> 계좌데이터의 관리점 코드를 바탕으로 관리점별 거래금액의 합 추출
     -> 리턴 -> List<SumAmtBranchByYearResponse>

    4. 분당점과 판교점을 통폐합하여 판교점으로 관리점 이관을 하였습니다. 
        지점명을 입력하면 해당지점의 거래금액 합계를 출력하는 API 개발
     -> 인풋 데이터가 분당점으로 들어올 경우 BrCodeNotFoundException 생성하여 Exception 처리 후 리턴 -> BrCodeNotFoundResponse 
     -> 거래 데이터 전체의 계좌별 금액의 합 추출
     -> 계좌데이터의 관리점 코드를 바탕으로 관리점별 거래금액의 합 추출
     -> 관리점 이름이 인풋으로 들어오면 getBranch 메소드를 통해 데이터 추출
     -> 리턴 -> SumAmtBranchResponse

## 빌드 및 실행 방법
    - Gradle build
    - KakaopayApplication 파일 실행하시면 됩니다.
    - 서버 실행 후 http://localhost:8080/swagger-ui.html 로 접속하여 API 테스트 할 수 있습니다.