const BoardUpdateForm = ({ article, setarticle, handleupdate }) => {
  console.log("BoardUpdateForm =>", article);

  const onChange = (e) => {
    setarticle({
      ...article,
      [e.target.name]: e.target.value,
    });
  };
  return (
    <div>
      <form>
        <table border="1" width="700px" align="center">
          <tr>
            <td width="100px">제목</td>
            <td align="left" width="600px">
              <input
                type="text"
                name="board_title"
                defaultValue={article.board_title}
                onChange={onChange}
              ></input>
            </td>
          </tr>
          <tr>
            <td width="100px">글쓴이</td>
            <td align="left" width="600px">
              {article.board_writer}
            </td>
          </tr>
          <tr>
            <td width="100px">글내용</td>
            <td align="left" width="600px">
              <input
                type="text"
                name="board_content"
                defaultValue={article.board_content}
                onChange={onChange}
              ></input>
            </td>
          </tr>
          <tr>
            <td colspan="2" align="center">
              <input
                type="button"
                value="글수정"
                onClick={handleupdate}
              ></input>
            </td>
          </tr>
        </table>
      </form>
    </div>
  );
};

export default BoardUpdateForm;
