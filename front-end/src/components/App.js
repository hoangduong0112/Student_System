import React from 'react';
import '../styles/App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import UserEdit from '../services/UserEdit';
import UserList from "../services/UserList";
import SemesterList from "../services/SemesterList";
import CateList from "../services/CateList";
import CourseList from "../services/CourseList";

const App = () => {
    return (
        <Router><Routes>
            <Route exact path="/api/v1/home" element={<Home/>}/>
            <Route path="/api/v1/user" exact={true} element={<UserList/>}/>
            <Route path="/api/v1/user/:id" exact={true} element={<UserEdit/>}/>
            <Route path="/api/v1/service-cate" exact={true} element={<CateList/>}/>
            <Route path="/api/v1/user/semester" exact={true} element={<SemesterList/>}/>
            <Route path="/api/v1/course" exact={true} element={<CourseList/>}/>
        </Routes></Router>
    )
}

export default App;