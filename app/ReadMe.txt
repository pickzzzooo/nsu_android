2023 / 10 / 30

to do list :
- 공공 데이터 가져오기
ㄴ 불러오기 (0) 0.231030완료.
ㄴ 파싱하기
- UI 만들기
- UI 에 데이터 연동하기
- 로그인 시스템 만들기
ㄴ 아이디 / 비번 DB 구축
ㄴ 아이디 정보 불러오기 / 보내기


log:
    // open api 설명서
    http://data.seoul.go.kr/dataList/OA-12601/A/1/datasetView.do

    // 실시간 지하철 json 구조
    {"errorMessage":{
        "status":200,
        "code":"INFO-000",
        "message":"정상 처리되었습니다.",
        "link":"",
        "developerMessage":"",
        "total":78              // 총 데이터 개수
        },
    "realtimePositionList":[
            {
            "beginRow":null,
            "endRow":null,
            "curPage":null,
            "pageRow":null,
            "totalCount":78,    //현재 운영중인 열차 수
            "rowNum":1,         //데이터 행 번호
            "selectedCount":1,  //출력할 행 개수
            "subwayId":"1001",  //호선 아이디
            "subwayNm":"1호선",  //호선명
            "statnId":"1001000133", //지하철역ID
            "statnNm":"서울",     //지하절역명 ?
            "trainNo":"0448",   //열차번호
            "lastRecptnDt":"20231031",  //최종수신날짜
            "recptnDt":"2023-10-31 12:40:38",   // 최종수신시간
            "updnLine":"0",
            "statnTid":"1001000119",    //종착지하철역ID
            "statnTnm":"광운대",         //종착지하철역명
            "trainSttus":"0",           //열차상태(0:진입 1:도착, 2출발, 3:전역출발)
            "directAt":"0",             //급행여부(1:급, 0:안, 7:특)
            "lstcarAt":"0"              //막차여부(1:true, 0:false)
            }
        ]
    }

    1.231030:
