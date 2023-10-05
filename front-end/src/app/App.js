import React from 'react';
import '../styles/App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import UserEdit from '../services/UserEdit';
import UserList from "../services/UserList";
import SemesterList from "../services/SemesterList";
import CateList from "../services/CateList";
import CourseList from "../services/CourseList";
import TranscriptList from "../services/TranscriptList";
import Diploma from "../services/DiplomaList";
import StudCertificate from "../services/StudCertificateList";
import UnlockStud from "../services/UnlockStudList";

const App = () => {
    return (
        <Router><Routes>
            <Route exact path="/" element={<Home/>}/>
            <Route path="/api/user/info" exact={true} element={<UserList/>}/>
            <Route path="/api/user/:id" exact={true} element={<UserEdit/>}/>
            <Route path="/api/guest/service-cate" exact={true} element={<CateList/>}/>
            <Route path="/api/user/service/transcript" exact={true} element={<TranscriptList/>}/>
            <Route path="/api/user/service/diploma" exact={true} element={<Diploma/>}/>
            <Route path="/api/user/service/stud-certificate" exact={true} element={<StudCertificate/>}/>
            <Route path="/api/user/service/unlock-stud" exact={true} element={<UnlockStud/>}/>
            <Route path="/api/user/semester" exact={true} element={<SemesterList/>}/>
            <Route path="/api/v1/course" exact={true} element={<CourseList/>}/>
        </Routes></Router>
    )
}

export default App;