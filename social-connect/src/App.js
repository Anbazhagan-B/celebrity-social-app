import "./App.css";
import Login from "./pages/Login/Login";
import {
  BrowserRouter as Router,
  Route,
  Routes,
  useLocation,
} from "react-router-dom";
import UserRegistrationPage from "./pages/Register/Register";
import { Provider } from "react-redux";
import reduxStore from "./redux/reduxStore";
import HomePage from "./pages/Home/Home";
import Connections from "./pages/Connections/Connections";
import Chats from "./pages/Chats/Chats";
import AddPost from "./pages/AddPost/AddPost";
import Footer from "./controls/Footer/Footer";
import Header from "./controls/Header/Header";
import ErrorPopup from "./controls/ErrorPopup/ErrorPopup";

function App() {
  return (
    <Provider store={reduxStore}>
      <Router>
        <DynamicHeader />
        <Routes>
          <Route path="/" element={<Login />}></Route>
          <Route path="/register" element={<UserRegistrationPage />}></Route>
          <Route path="/home" element={<HomePage />}></Route>
          <Route path="/connections" element={<Connections />}></Route>
          <Route path="/chat" element={<Chats />} />
          <Route path="/add-post" element={<AddPost />}></Route>
        </Routes>
        <ErrorPopup />
        <DynamicFooter />
      </Router>
    </Provider>
  );
}

const DynamicHeader = () => {
  const location = useLocation();
  if (location.pathname === "/" || location.pathname === "/register") {
    return null;
  }
  return <Header />;
};

const DynamicFooter = () => {
  const location = useLocation();
  if (location.pathname === "/" || location.pathname === "/register") {
    return null;
  }
  return <Footer />;
};

export default App;
