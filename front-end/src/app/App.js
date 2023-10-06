import React, { useState } from 'react';
import '../styles/App.css';
import Home from './Home';
import {BrowserRouter as Router, Navigate, Route, Routes} from 'react-router-dom';
import UserEdit from '../components/UserEdit';
import UserList from "../components/UserList";
import SemesterList from "../components/SemesterList";
import CateList from "../components/CateList";
import CourseList from "../components/CourseList";
import TranscriptList from "../components/TranscriptList";
import StudCertificate from "../components/StudCertificateList";
import UnlockStud from "../components/UnlockStudList";
import DiplomaList from "../components/DiplomaList";
import Login from "./Login";

const App = () => {
    const [isAuthenticated, setIsAuthenticated] = useState(false);

    const handleLogin = () => {
        // Dang nhap thanh cong thi isAuthenticated la true
        setIsAuthenticated(true);
    };

    const handleLogout = () => {
        // Dang xuat thanh cong thi isAuthenticated la false
        setIsAuthenticated(false);
    };

    return (
        <div>
            <Router>
                <div className="container">
                    <Routes>
                        <Route path="/" element={isAuthenticated ?
                            <Navigate to="/api/home" /> : <Navigate to="/api/auth/signin" />} />

                        <Route path="/api/auth/signin" element={<Login onLogin={handleLogin} />} />

                        {setIsAuthenticated ? ( <>
                                <Route path="/api/home" element={<Home onLogout={handleLogout} />} />
                                <Route path="/api/user/info" element={<UserList />} />
                                <Route path="/api/user/:id" element={<UserEdit />} />
                                <Route path="/api/guest/service-cate" element={<CateList />} />
                                <Route path="/api/user/service/transcript/:id" element={<TranscriptList />} />
                                <Route path="/api/user/service/diploma/:id" element={<DiplomaList />} />
                                <Route path="/api/user/service/stud-certificate/:id" element={<StudCertificate />} />
                                <Route path="/api/user/service/unlock-stud/:id" element={<UnlockStud />} />
                                <Route path="/api/user/semester" element={<SemesterList />} />
                                <Route path="/api/v1/course" element={<CourseList />} />
                            </> ) : null}
                    </Routes>
                </div>
            </Router>
        </div>
    );
}

export default App;
