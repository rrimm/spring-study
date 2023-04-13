import { useRef } from "react";
import axios from "axios";

const BoardWrite = ({ handlelist }) => {
  const titleRef = useRef();
  const writerRef = useRef();
  const contentRef = useRef();

  const handleInsert = () => {
    console.log("handleInsert =>", titleRef.current.value);
    if (titleRef.current.value === "" || titleRef.current.value === undefined) {
      alert("제목을 입력하세요!!!");
      titleRef.current.focus();
      return false;
    }
    if (
      writerRef.current.value === "" ||
      writerRef.current.value === undefined
    ) {
      alert("글쓴이를 입력하세요!!!");
      writerRef.current.focus();
      return false;
    }
    if (
      contentRef.current.value === "" ||
      contentRef.current.value === undefined
    ) {
      alert("내용을 입력하세요!!!");
      contentRef.current.focus();
      return false;
    }

    axios
      .post("/insert", {
        board_title: titleRef.current.value,
        board_writer: writerRef.current.value,
        board_content: contentRef.current.value,
      })
      .then((res) => {
        console.log("handleInsert =>", res);
        handlelist();
        titleRef.current.value = "";
        writerRef.current.value = "";
        contentRef.current.value = "";
      })
      .catch((e) => {
        console.error(e);
      });
  };

  return (
    <div>
      <form>
        <table border="1" width="700px" align="center">
          <tr>
            <td width="100px">제목</td>
            <td align="left" width="550px">
              <input
                type="text"
                name="title"
                size="68"
                ref={titleRef}
                placeholder="제목을 입력하세요"
              ></input>
            </td>
          </tr>
          <tr>
            <td width="100px">글쓴이</td>
            <td align="left" width="550px">
              <input
                type="text"
                name="writer"
                size="68"
                ref={writerRef}
                placeholder="글쓴이를 입력하세요"
              ></input>
            </td>
          </tr>
          <tr>
            <td>내용</td>
            <td align="left">
              <textarea
                rows="5"
                cols="70"
                name="content"
                ref={contentRef}
                placeholder="내용을 입력하세요"
              ></textarea>
            </td>
          </tr>
          <tr>
            <td colSpan="2" align="center">
              <input
                type="button"
                value="글쓰기"
                onClick={handleInsert}
              ></input>
              &nbsp;
              <input type="reset" value="취소"></input>
            </td>
          </tr>
        </table>
      </form>
    </div>
  );
};

export default BoardWrite;
