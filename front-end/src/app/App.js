import React, {createContext, useEffect, useReducer} from 'react';
import '../styles/App.css';
import Home from './Home';
import {Navigate, Route, Routes} from 'react-router-dom';
import Signin from "./Signin";
import Reducer from "./Reducer";
import cookie from "react-cookies";
import Header from "../layouts/Header";
import Footer from "../layouts/Footer";
import * as Comp from '../components';
import MyUserReducer from "./Reducer";

export const UserContext = createContext(null);

const App = () => {
    const [user, setUser] = useReducer(MyUserReducer,cookie.load('user') || null);


    return (
        <UserContext.Provider value={[user, setUser]}>
            <Header/>
            <div className="container">
                <Routes>
                    <Route path="/" element={<Navigate to="/guest/auth/signin" />} />
                    <Route path="/guest/auth/signin" element={<Signin />} />
                    <Route path="/home" element={<Home />} />

                    <Route path="/user/info" element={<Comp.UserInfoList />} />
                    <Route path="/user/semester" element={<Comp.UserSemesterList />} />
                    <Route path="/user/semester/:id/course" element={<Comp.UserDetailsList />} />
                    <Route path="/user/payment/create" element={<Comp.CreatePayment />} />
                    <Route path="/user/payment/result/" element={<Comp.PaymentStatus />} />
                    <Route path="/user/payment/detail/:id" element={<Comp.PaymentDetails />} />

                    <Route path="/service/transcript/add" element={<Comp.AddTranscript />} />
                    <Route path="/service/diploma/add" element={<Comp.AddDiploma />} />
                    <Route path="/service/stud-cert/add" element={<Comp.AddStudCertificate />} />
                    <Route path="/service/unlock-stud/add" element={<Comp.AddUnlockStud />} />

                    <Route path="/service/transcript/update/:id" element={<Comp.UpdateTranscript />} />
                    <Route path="/service/diploma/update/:id" element={<Comp.UpdateDiploma />} />
                    <Route path="/service/stud-cert/update/:id" element={<Comp.UpdateStudCertificate />} />
                    <Route path="/service/unlock-stud/update/:id" element={<Comp.UpdateUnlockStud />} />

                    <Route path="/service/diploma/:id" element={<Comp.DiplomaDetail />} />
                    <Route path="/service/stud-cert/:id" element={<Comp.StudCertificateDetail/>} />
                    <Route path="/service/transcript/:id" element={<Comp.TranscriptDetail/>} />

                    <Route path="/admin/course-data/all" element={<Comp.CourseDataList />} />
                    <Route path="/admin/course-data/add" element={<Comp.AddCourseData />} />
                    <Route path="/admin/course-data/update/:id" element={<Comp.UpdateCourseData />} />

                    <Route path="/admin/course/all" element={<Comp.CourseList />} />
                    <Route path="/admin/course/add" element={<Comp.AddCourse />} />
                    <Route path="/admin/course/update/:id" element={<Comp.UpdateCourse />} />

                    <Route path="/admin/lecture/all" element={<Comp.LectureList />} />
                    <Route path="/admin/lecture/add" element={<Comp.AddLecture />} />
                    <Route path="/admin/lecture/update/:id" element={<Comp.UpdateLecture />} />

                    <Route path="/admin/semester/all" element={<Comp.SemesterList />} />
                    <Route path="/admin/semester/:id" element={<Comp.SemesterList />} />
                    <Route path="/admin/semester/add" element={<Comp.AddSemester />} />
                    <Route path="/admin/semester/update/:id" element={<Comp.UpdateSemester />} />

                    <Route path="/admin/student" element={<Comp.UserList />} />

                    <Route path="/moderator/service-cate" element={<Comp.CateList />} />
                    <Route path="/moderator/service-cate/update/:id" element={<Comp.UpdateCate />} />
                    <Route path="/moderator/get-request" element={<Comp.RequestList />} />
                </Routes>
            </div>
            <Footer />
        </UserContext.Provider>
    );
}

export default App;