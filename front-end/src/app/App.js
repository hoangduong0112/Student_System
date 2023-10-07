import "../styles/App.css";
import {useEffect} from "react";
import {useLocalState} from "../services/useLocalStorage";
import {Route, Routes} from "react-router-dom";
import Home from "./Home.js";
import CourseList from "../components/CourseList";
import SemesterList from "../components/SemesterList";
import DiplomaList from "../components/DiplomaList";
import TranscriptList from "../components/TranscriptList";
import CateList from "../components/CateList";
import UserEdit from "../components/UserEdit";
import UserList from "../components/UserList";
import UnlockStudList from "../components/UnlockStudList";
import StudCertificateList from "../components/StudCertificateList";
import Login from "./Login";

function App() {
    const [jwt, setJwt] = useLocalState("", "jwt");

    useEffect(() => {
        if (!jwt) {
            const request = {
                email: "sfhsfdxn",
                password: "6747846"
            };

            fetch('api/auth/signin', {
                headers: { "Content-Type": "application/json" },
                method:'post',
                body: JSON.stringify(request),
            })  .then(response => Promise.all([response.json(), response.headers]))
                .then(([body, headers]) => {
                    setJwt(headers.get("authorization"));
                });
        }
    }, []);

    return  (
            <div className="container">
                <Routes>
                    <Route path="/" element={<Login />} />
                    <Route path="/api/home" element={<Home />} />
                    <Route path="/api/user/info" element={<UserList />} />
                    <Route path="/api/user/:id" element={<UserEdit />} />
                    <Route path="/api/guest/service-cate" element={<CateList />} />
                    <Route path="/api/user/service/transcript/:id" element={<TranscriptList />} />
                    <Route path="/api/user/service/diploma/:id" element={<DiplomaList />} />
                    <Route path="/api/user/service/stud-certificate/:id" element={<StudCertificateList />} />
                    <Route path="/api/user/service/unlock-stud/:id" element={<UnlockStudList />} />
                    <Route path="/api/user/semester" element={<SemesterList />} />
                    <Route path="/api/v1/course" element={<CourseList />} />
                </Routes>
            </div>
    )
}

export default App;