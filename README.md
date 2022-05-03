# SmartPhoneGameProgramming
2022_4_1 SmartPhone Game Programming 텀프로젝트 <런런런> 중간 발표


# 게임 컨셉
- High Concept: 장애물을 피하고 아이템을 획득해 몬스터들을 공격해나가며 달리는 런게임
- 핵심 메카닉: 플레이어는 달리며 코인과 아이템을 획득할 수 있다. 아이템을 획득하면 공격이 활성화되며 몬스터들을 해치우며 앞으로 전진해나간다. 목표 지점까지 도달하면 승리. 플레이하며 얻은 코인과 처치한 몬스터 수로 최종 점수를 환산한다.  


# 현재까지의 진행상황  

<img src="https://user-images.githubusercontent.com/55976889/166564734-d84010f0-df97-4c15-b9a0-54e6973957b6.png" width="450" height="220">  

- 플레이어 점프 & NormalEnemy
<img src="https://user-images.githubusercontent.com/55976889/166569118-af0804e3-762d-4684-99d2-2c8f6918f595.png" width="450" height="220">  

- 플레이어 이동 & AttackEnemy
<img src="https://user-images.githubusercontent.com/55976889/166569152-690d0143-4fec-4ed3-8398-d560b487eddb.png" width="450" height="220">  

- 플레이어 공격
<img src="https://user-images.githubusercontent.com/55976889/166569205-d0d5388a-162f-48cf-b0fd-d6ec963d6e04.png" width="450" height="220">  


# Git Commit  

<img src="https://user-images.githubusercontent.com/55976889/166570550-fd60d94c-e529-42eb-9fed-17ed11a9e5a5.png" width="500" height="220">  


# GameObject 정보  

<img src="https://user-images.githubusercontent.com/55976889/166569899-4908050c-910e-4b46-b3de-3f03aa42273e.png" width="450" height="220">  

- Button: MainGame에서 해당 버튼이 눌렸는지 확인하고 눌린 버튼에 따라 Player 멤버함수 호출
(move(), attack(), jump())  

- Player: 현재 상태에 따라 이동량을 계산, walk/attack 상태라면 애니메이션 렌더링, jump 상태라면 다른 텍스처 렌더링, 총알 발사.  

- Bullet: 2초 뒤 사라짐. (객체와 충돌하면 사라지도록 구현할예정)  

- Item/Ground Block: 플레이어와 충돌체크하여 아이템 블럭이라면 아이템 생성되게 함.  

- NormalEnemy: 플레이어와 상관없이 좌우로만 반복해서 이동.  

- AttackEnemy: 플레이어를 따라다니며 일정 거리 이하가 되면 멈춰서 플레이어를 향해 총알을 발사함.  


# 구현하면서 어려웠던 점  
- 플레이어의 걷는 동작 애니메이션 걷는 동작 싱크로율과 실제 이동 속도를 자연스럽게 맞추는 것  
     -> 여러 번 테스트
- 플레이어의 이동 방향과 동작에 따라 다른 애니메이션과 텍스처가 렌더링돼야 함  
     -> bitmap을 추가로 생성해서 상태에 따라 다른 비트맵 렌더링  


# 개발 일정
1주: 리소스 수집/편집  

2주: 프레임워크 작업 시작(MainGame, GameObject). 스테이지 신 버튼 배치(walk, jump, attack), 맵 오브젝트 구상/배치  

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

