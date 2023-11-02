import React, {useState, useEffect, useContext} from 'react';
import { Container, Table } from 'reactstrap';
import UserService from "../../services/UserService";
import { useNavigate } from "react-router-dom";
import MyAlert from "../../layouts/MyAlert";
import {UserContext} from "../../app/App";

function UserInfo() {
    const [user, setUser] = useContext(UserContext);

    const nav = useNavigate();


    return (
        <div>
            <Container fluid>
                <h3 className="App">Thông tin sinh viên</h3>
                <div className="row">
                    <Table className="mt-5">
                        <tbody>
                        <tr className="border-bottom" style={{ height: '50px' }}>
                            <th>Họ và tên</th>
                            <td>{user.fullName}</td>
                        </tr>
                        <tr className="border-bottom" style={{ height: '50px' }}>
                            <th>Ảnh đại diện</th>
                            <td>
                                {user.avatar !== '' ? (
                                    <img src={user.avatar} alt="Ảnh đại diện" width="200" height="200"/>
                                ) : (
                                    <span role="img" aria-label="Ảnh đại diện">&#x1F464;</span>
                                )}
                            </td>
                        </tr>
                        <tr className="border-bottom" style={{ height: '50px' }}>
                            <th>Email</th>
                            <td>{user.email}</td>
                        </tr>
                        <tr className="border-bottom" style={{ height: '50px' }}>
                            <th>Khoa</th>
                            <td>{user.department_name}</td>
                        </tr>
                        <tr className="border-bottom" style={{ height: '50px' }}>
                            <th>Ngành</th>
                            <td>{user.major_name}</td>
                        </tr>
                        </tbody>
                    </Table>
                </div>
                <div className="float-end row">
                    <button className="btn-primary btn" onClick={() => nav('/home')}>
                        Quay lại
                    </button>
                </div>
            </Container>
        </div>
    );
}

export default UserInfo;
