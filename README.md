## 1차 요구사항 구현
- [x] 유저가 루트 url로 접속시에 게시글 리스트 페이지(http://주소:포트/article/list)가 나온다.
- [x] 리스트 페이지에서는 등록 버튼이 있고 버튼을 누르면 http://주소:포트/article/create 경로로 이동하고 등록 폼이 나온다.
- [x] 게시글 등록을 하면 http://주소:포트/article/create로 POST 요청을 보내어 DB에 해당 내용을 저장한다.
- [x] 게시글 등록이 되면 해당 게시글 리스트 페이지로 리다이렉트 된다. 페이지 URL 은 http://주소:포트/article/list 이다.
- [x] 리스트 페이지에서 해당 게시글을 클릭하면 상세페이지로 이동한다. 해당 경로는 http://주소:포트/article/detail/{id} 가 된다.
- [x] 게시글 상세 페이지에는 목록 버튼이 있다. 목록 버튼을 누르면 게시글 리스트 페이지로 이동하게 된다.

# 2차 요구사항
- [x] 모든 페이지 상단에 루트 디렉토리로 이동하는 버튼이 있다.(예: 로고)
- [x] 모든 페이지 상단에 로그인 상태 표시하는 버튼이 있다.(예: 로그인 / 로그아웃)
- [x] 게시글 상세페이지(http://주소:포트/article/detail/{id})에 수정 버튼이 있다. 수정 버튼을 누르면 게시글을 수정 할 수 있는 폼이나 오고 수정이 가능하다.
- [x] 게시글 상세페이지에 삭제 버튼이 있다. 삭제 버튼을 누르면 게시글이 삭제가 된다. 삭제 후 리스트 페이지로 리다이렉트 된다.
- [x] 모든 페이지 회원가입 버튼이 있다. 버튼을 누르면 회원가입 폼으로 이동한다.
  - [x] 회원가입 폼은 유저ID, 닉네임, 비빌번호, 비밀번호 확인으로 구성된다. 회원가입 버튼을 누르면 데이터 검증 후 회원가입이 된다.
- [x] 로그인 버튼을 누르면 로그인 폼으로 이동한다.
  - [x] 로그인 페이지는 사용자 유저ID과 비밀번호를 입력하는 폼으로 구성되고 로그인 버튼을 누르면 데이터 검증 후 로그인이 된다.
- [x] 로그아웃 버튼을 누르면 로그아웃이 된다.
- [x] 유저가 게시글 작성 및 수정  접근시 로그인 여부를 검사한다.
  본인 글에 대해서만 수정 / 삭제가 가능하다.

## 미비사항 or 막힌 부분
- 가장 큰 문제로는 스프링 시큐리티 라는 놈이 뭐하는 놈인지를 몰라서
- 요구사항에 있는 기능을 구현해야 할 때, 어느 요구사항을 시큐리티에 맡겨야 하는지, 내가 구현을 해야하는지 구분하는 것이 몹시 어려웠다.
- 다만 여러 번 반복을 하다보니 스프링 시큐리티는 회원가입에 대한 검증과, 로그인의 유효성을 알아서 해주는 애구나
- 나는 매핑되는 메서드에 알맞은 어노테이션(@PreAuthorize("isAuthenticated()") 등)과 매개변수(Principal 등)를 부여해주고 뷰의 템플릿을 조금 고쳐주면 되는거구나
- 이런 것들을 깨닫게 된 것 같다.
- 즉 로그인과 같은 기능은 내가 '만드는' 것이 아닌 스프링 시큐리티를 '쓰는' 것 이었다.