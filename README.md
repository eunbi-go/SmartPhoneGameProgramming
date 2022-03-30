# SmartPhoneGameProgramming
2022_4_1 SmartPhone Game Programming 텀프로젝트 <런런런> 기획 발표


# 게임 컨셉
- High Concept: 장애물을 피하고 아이템을 획득해 몬스터들을 공격해나가며 달리는 런게임
- 핵심 메카닉: 플레이어는 달리며 코인과 아이템을 획득할 수 있다. 아이템을 획득하면 공격이 활성화되며 몬스터들을 해치우며 앞으로 전진해나간다. 목표 지점까지 도달하면 승리. 플레이하며 얻은 코인과 처치한 몬스터 수로 최종 점수를 환산한다.  


# 개발 범위
- 맵: 곳곳에 장애물과 코인과 아이템이 나오는 랜덤박스 존재
- 캐릭터 컨트롤: walk 버튼으로 이동, walk 떼면 idle, jump/attack 누르면 점프/공격
- 캐릭터: 하나의 캐릭터
- 캐릭터 애니메이션: IDLE, WALK, JUMP, ATTACK

- 게임 주요 기능
플레이어와 맵 오브젝트/몬스터 간의 충돌체크/처리
획득한 코인(5점), 처치한 몬스터 수(5점, 10점) 만큼 점수 증가
아이템을 획득하면 일반 상태에서 공격할 수 있는 상태가 되어 attack 버튼으로 공격 가능
(획득하지 못했으면 attack버튼 눌러도 공격안됨)
공격 상태에서 몬스터와 충돌하면 일반 상태로 돌아감
바닥에 빠지면&일반 상태에서 몬스터와 충돌하면 패배.
아이템을 하나 얻을 때 마다 5회 공격 가능  

- 게임 난이도
초반: 코인, 장애물
중반: 이동만 하고 공격하지 않는 몬스터
후반: 공격하는 몬스터

- 사운드: 배경음(시작 전, 스테이지, 종료), 플레이어 점프/공격


# 예상 게임 흐름
시작 화면  

<img src="https://user-images.githubusercontent.com/55976889/160470191-783a8aab-26c6-4d6d-ae37-760e9b7ff50c.png" width="330" height="160">

초반: 코인  

<img src="https://user-images.githubusercontent.com/55976889/160467825-700f964e-d5a5-4a03-87a6-1dd312c4ce2e.png" width="330" height="160">

초반: 코인 + 장애물  

<img src="https://user-images.githubusercontent.com/55976889/160467857-a3ee8338-6925-4865-b91b-89ff7337a9cd.png" width="330" height="160">

패배 화면  

<img src="https://user-images.githubusercontent.com/55976889/160470959-65272f00-fddc-41a5-9cfd-1911137e6127.png" width="330" height="160">

중반: 랜덤박스 위에서 점프하면 아이템 획득 가능  

<img src="https://user-images.githubusercontent.com/55976889/160467908-b3130f08-9347-4916-bc2e-26e0bc2c10d5.png" width="330" height="160">

중후반: 몬스터, 랜덤박스에서 아이템을 얻으면 attack 버튼이 활성화됨  

<img src="https://user-images.githubusercontent.com/55976889/160467964-f938add8-dbb2-49f1-b203-b4effee5b2c9.png" width="330" height="160">

최종 점수 확인. 이전 점수와 비교. 다시 시작 가능  

<img src="https://user-images.githubusercontent.com/55976889/160468228-fa269775-1de9-4995-a56a-0009b848982d.png" width="330" height="160">



# 개발 일정
1주: 리소스 수집/편집  

2주: 스테이지 신 버튼 배치(walk, jump, attack), 맵 오브젝트 구상/배치  

3주: 플레이어 구현(이동, 점프, 공격)  

4주: 몬스터, 아이템  

5주: 충돌 체크/처리  

6주: UI(현재 점수), 로비/스코어 신 구현, 신 연결  

7주: 부족한 부분 구현, 가능하다면 추가 구현  

8주: 사운드  

9주: 최종 점검, 마무리 작업  



# 추가 구현
- 아이템
쉴드: 몬스터와 충돌 시 1회 무효  

- 장애물
천장에서 상하로 움직이는 장애물  

- 몬스터
플레이어 위에서 좌우로 움직이며 총알로 공격하는 몬스터

