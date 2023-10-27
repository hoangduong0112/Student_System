import React, { useState, useEffect } from 'react';
import { Container, Table } from 'reactstrap';
import UserService from "../../services/User/UserService";
import { useNavigate } from "react-router-dom";

function UserInfoList() {
    const [user, setUser] = useState({
        id: 0,
        email: '',
        fullName: '',
        avatar: '',
        role: '',
        major_name: '',
        department_name: ''
    });

    const nav = useNavigate();

    useEffect(() => {
        UserService.getUser().then(res => {
            setUser(res.data);
        });
    }, []);

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

export default UserInfoList;
