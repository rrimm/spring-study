import BoardList from "./BoardList";
import BoardWrite from "./BoardWrite";
import BoardDetail from "./BoardDetail";
import BoardUpdateForm from "./BoardUpdateForm";
import { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function Main() {
  const [boardlist, setBoardlist] = useState([]);

  const navigate = useNavigate();

  const [article, setArticle] = useState({
    board_num: 0,
    board_writer: "",
    board_title: "",
    board_content: "",
    board_date: "",
  });

  // 0:글쓰기, 1:상세보기, 2:글수정
  const [actionMode, setActionMode] = useState(0);
  const [pageLink, setPageLink] = useState([]); // 페이지 링크 저장

  var page_num = 1; // 페이지 번호
  const page_size = 3; // 한페이지에 나타낼 글 수
  var page_count = 1; // 페이지 갯수
  var article_count = 0; // 총글의 갯수

  useEffect(() => {
    // 로그인 상태인지 체크
    const login_id = window.sessionStorage.getItem("id"); // 세션스토리지에 저장되어 있는 id값 읽음
    console.log("window.sessionStorage(login_id) =>", login_id);
    if (login_id === null) {
      alert("로그인후 사용가능합니다!!");
      navigate("/");
    }
  }, []);

  const handlePage = (e) => {
    // 페이지 링크를 클릭할 때 실행
    console.log("handlePage(e.target.id) =>", e.target.id);
    page_num = e.target.id;
    getList();
  };

  // 글목록
  const getList = () => {
    // alert("getList(actionMode) =>" + actionMode);

    axios
      .get("/count", {}) // 총글의 갯수를 구함
      .then((res) => {
        console.log("data =>", res.data);
        const { data } = res;
        article_count = data; // 총글의 객수 저장
        page_count = Math.ceil(article_count / page_size); // 총페이지 수 저장
        var page_link = [];
        for (let i = 1; i <= page_count; i++) page_link.push(i); // 총페이지 갯수만큼 페이지번호를 배열에 저장
        console.log("getArticleCount(page_link) =>", page_link);
        setPageLink(page_link);
      })
      .catch((e) => {
        console.error(e);
      });

    // var start = (Math.ceil(page_num / page_size) - 1) * page_size + 1; // 전체 목록에서 해당 페이지의 시작 위치 설정
    // console.log("start=" + start);
    console.log("page_num=" + page_num);
    axios
      .post("/list", {
        page_num: page_num,
        limit: page_size,
      })
      .then((res) => {
        // res : 서버의 응답 결과 저장
        console.log("res ==>", res);
        const { data } = res; // data = res.data
        console.log("data ==>", data);
        setBoardlist(data);
        setActionMode(0);
      })
      .catch((e) => {
        console.error(e);
      });
  };

  // 상세보기
  const handleDetail = (e) => {
    // alert("handleDetail(actionMode) =>" + actionMode);
    axios
      .post("/detail", { board_num: e.target.id })
      .then((res) => {
        // console.log("detail(res) =>", res);
        const { data } = res;
        // server.js : server.js 사용시 data가 배열로 반환
        // 스프링부트 : data가 배열이 아님
        setArticle({
          //...article, // 생략 가능 :  article 전체 객체 내용을 다 수정하기 때문에.
          board_num: data.board_num,
          board_writer: data.board_writer,
          board_title: data.board_title,
          board_content: data.board_content,
          board_date: data.board_date,
        });
        setActionMode(1);
      })
      .catch((e) => {
        console.error(e);
      });
  };

  // 수정폼 보기
  const handleUpdateForm = (e) => {
    // alert(
    //   "handleUpdateForm(actionMode) =>" + actionMode + ", " + e.target.id
    // );
    axios
      .post("/detail", { board_num: e.target.id })
      .then((res) => {
        const { data } = res;
        console.log("handleUpdateForm =>", data);

        // server.js : server.js 사용시 data가 배열로 반환
        // 스프링부트 : data가 배열이 아님
        setArticle({
          //...article, // 생략 가능 :  article 전체 객체 내용을 다 수정하기 때문에.
          board_num: data.board_num,
          board_writer: data.board_writer,
          board_title: data.board_title,
          board_content: data.board_content,
          board_date: data.board_date,
        });
        setActionMode(2);
      })
      .catch((e) => {
        console.error(e);
      });
  };

  const handleUpdate = () => {
    console.log("handleUpdate =>", article);
    axios
      .post("/update", {
        board_num: article.board_num,
        board_title: article.board_title,
        board_content: article.board_content,
      })
      .then((res) => {
        console.log("handleUpdate( changedRows) =>", res.data.changedRows);
        getList();
      })
      .catch((e) => {
        console.error(e);
      });
  };

  console.log("actionMode : " + actionMode);

  if (actionMode === 0) {
    // alert("글쓰기");
    return (
      <div>
        <BoardWrite handlelist={getList}></BoardWrite>
        <br />
        <BoardList
          boardlist={boardlist}
          actionmode={actionMode}
          handlelist={getList}
          handledetail={handleDetail}
          handleupdateform={handleUpdateForm}
          handlepage={handlePage}
          pagelink={pageLink}
        ></BoardList>
      </div>
    );
  } else if (actionMode === 1) {
    // alert("상세정보");
    return (
      <div>
        <BoardDetail article={article} handlelist={getList}></BoardDetail>
        <br />
        <BoardList
          boardlist={boardlist}
          handlelist={getList}
          handledetail={handleDetail}
          handleupdateform={handleUpdateForm}
          handlepage={handlePage}
          pagelink={pageLink}
        ></BoardList>
      </div>
    );
  } else if (actionMode === 2) {
    // alert("글수정");
    return (
      <div>
        <BoardUpdateForm
          article={article}
          setarticle={setArticle}
          handleupdate={handleUpdate}
        ></BoardUpdateForm>
        <br />
        <BoardList
          boardlist={boardlist}
          handlelist={getList}
          handledetail={handleDetail}
          handleupdateform={handleUpdateForm}
          handlepage={handlePage}
          pagelink={pageLink}
        ></BoardList>
      </div>
    );
  }
}

export default Main;
