import React from 'react';
import './App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import UserEdit from './UserEdit';
import UserList from "./UserList";

const App = () => {
    return (
        <Router><Routes>
            <Route exact path="/" element={<Home/>}/>
            <Route path="/users" exact={true} element={<UserList/>}/>
            <Route path="/users/:id" exact={true} element={<UserEdit/>}/>
        </Routes></Router>
    )
}

export default App;