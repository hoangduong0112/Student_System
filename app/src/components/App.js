import React from 'react';
import '../styles/App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import UserEdit from '../services/UserEdit';
import UserList from "../services/UserList";

const App = () => {
    return (
        <Router><Routes>
            <Route exact path="/api/v1/home" element={<Home/>}/>
            <Route path="/api/v1/user" exact={true} element={<UserList/>}/>
            <Route path="/api/v1/user/:id" exact={true} element={<UserEdit/>}/>
        </Routes></Router>
    )
}

export default App;