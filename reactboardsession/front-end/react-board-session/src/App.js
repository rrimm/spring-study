import { Route, Routes } from "react-router-dom";
import Main from "./Main";
import LoginForm from "./LoginForm";
import MemberForm from "./MemberForm";

function App() {
  return (
    <Routes>
      <Route path="/" element={<LoginForm />} />
      <Route path="/main" element={<Main />} />
      <Route path="/member" element={<MemberForm />} />
    </Routes>
  );
}

export default App;
