import { useEffect } from "react";
import BoardArticle from "./BoardArticle";
import { useNavigate } from "react-router-dom";
import PageLink from "./PageLink";

const BoardList = ({
  boardlist,
  handlelist,
  handledetail,
  handleupdateform,
  handlepage,
  pagelink,
}) => {
  const navigate = useNavigate();

  useEffect(() => {
    handlelist();
  }, []);

  const handleLogout = () => {
    console.log("handleLogout");
    window.sessionStorage.clear(); // 세션스토리지에 저장된 속성값 모두 삭제
    console.log(
      "handleLogout:window.sessionStorage(login_id) =>",
      window.sessionStorage.getItem("id")
    );
    navigate("/"); // 로그인페이지로 이동
  };

  if (boardlist.length === 0) {
    return (
      <div>
        <table width="700px" border="1" align="center">
          <thead>
            <tr>
              <th width="60">번호</th>
              <th width="240">제목</th>
              <th width="100">작성자</th>
              <th width="100">작성일</th>
              <th width="200">
                수정/삭제
                <input
                  type="button"
                  value="로그아웃"
                  onClick={handleLogout}
                ></input>
              </th>
            </tr>
          </thead>
        </table>
      </div>
    );
  } else {
    return (
      <div>
        <table width="700px" border="1" align="center">
          <thead>
            <tr>
              <th width="60">번호</th>
              <th width="240">제목</th>
              <th width="100">작성자</th>
              <th width="100">작성일</th>
              <th width="200">
                수정/삭제
                <input
                  type="button"
                  value="로그아웃"
                  onClick={handleLogout}
                ></input>
              </th>
            </tr>
          </thead>
          <tbody>
            {boardlist.map((article) => {
              return (
                <BoardArticle
                  article={article}
                  key={article.board_num}
                  handlelist={handlelist}
                  handledetail={handledetail}
                  handleupdateform={handleupdateform}
                />
              );
            })}
          </tbody>
        </table>
        <table align="center">
          <tr>
            <td align="center">
              {pagelink.map((page) => {
                return (
                  <PageLink page={page} key={page} handlepage={handlepage} />
                );
              })}
            </td>
          </tr>
        </table>
      </div>
    );
  }
};

export default BoardList;
